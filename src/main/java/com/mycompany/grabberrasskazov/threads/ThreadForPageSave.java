/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grabberrasskazov.threads;

import com.mycompany.grabberrasskazov.GlobalVars;
import com.mycompany.grabberrasskazov.beans.MainBean;
import com.mycompany.grabberrasskazov.controllers.AppContextManager;
import com.mycompany.grabberrasskazov.models.Stories;
import com.mycompany.grabberrasskazov.models.StoriesToCategoriessRelations;
import com.mycompany.grabberrasskazov.models.StoriesToWritersRelations;
import com.mycompany.grabberrasskazov.models.StoryCategories;
import com.mycompany.grabberrasskazov.models.StoryWriters;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author aaa
 */
public class ThreadForPageSave implements Runnable {

    String pageUrlLocal;
    ApplicationContext ctx = AppContextManager.getAppContext();
    MainBean mainBean = (MainBean) ctx.getBean("mainBean");

    public ThreadForPageSave(String pageUrl) {
        pageUrlLocal = pageUrl;
    }

    @Override
    public void run() {
        indexStory(pageUrlLocal);
    }

    public void indexStory(String pageUrl) {
        try {
            String oldId = pageUrl.replace(GlobalVars.mainSite, "");
            if (!mainBean.storyExists(oldId)) {
                Stories r = new Stories();

                Document doc = Jsoup.connect(pageUrl)
                        .userAgent("Opera/9.80 (X11; Linux x86_64) "
                                + "Presto/2.12.388 Version/12.16")
                        .get();

                Elements nameBlockElements = doc.select("b:containsOwn(Название)");
                Element nameBlock = nameBlockElements.get(0);
                nameBlock = nameBlock.parent().parent();
                nameBlockElements = nameBlock.select("td:eq(1)");
                nameBlock = nameBlockElements.get(0);
                String storyName = nameBlock.text();
                r.setStoryName(storyName);

                // Start of processing writer
                Elements writerBlockElements = doc.select("b:containsOwn(Автор:)");
                Element writerBlock = writerBlockElements.get(0);
                writerBlock = writerBlock.parent().parent();
                writerBlockElements = writerBlock.select("td:eq(1)");
                writerBlock = writerBlockElements.get(0);

                String writersUrl = writerBlock.select("a:eq(0)").attr("href");
                String writersName = writerBlock.select("a:eq(0)").text();
                String writersContacts = writerBlock.select("a:eq(1)").attr("href");

                StoryWriters storyWriter = new StoryWriters();
                storyWriter.setOldId(writersUrl);
                storyWriter.setWriterEmail(writersContacts);
                storyWriter.setWriterName(writersName);
                storyWriter = mainBean.saveWriter(storyWriter);

                Set<StoriesToWritersRelations> storiesToWritersRelationses = new HashSet<StoriesToWritersRelations>();
                StoriesToWritersRelations storiesToWritersRelations = new StoriesToWritersRelations();
                storiesToWritersRelations.setStories(r);
                storiesToWritersRelations.setStoryWriters(storyWriter);
                r.setStoriesToWritersRelationses(storiesToWritersRelationses);

                // End of processing writer
                Set<StoriesToCategoriessRelations> catsRelationses = new HashSet<>();
                Elements katsInfo = doc.select("a[href*=ras.shtml?kat]");
                for (Element kat : katsInfo) {
                    String katId = kat.attr("href");
                    StoryCategories cat = mainBean.getCat(katId);

                    StoriesToCategoriessRelations catsRelations = new StoriesToCategoriessRelations();
                    catsRelations.setStoryCategories(cat);
                    catsRelations.setStories(r);

                    catsRelationses.add(catsRelations);

                }
                r.setStoriesToCategoriessRelationses(catsRelationses);

                Elements textBlocks = doc.select("p[align=justify]");
                Element textBlock = textBlocks.get(0);
                String textStr = textBlock.html();
                r.setStoryText(textStr.replace("\"", "'"));

                r.setOldId(oldId);

                mainBean.saveStory(r);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
