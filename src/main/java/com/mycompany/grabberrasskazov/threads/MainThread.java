package com.mycompany.grabberrasskazov.threads;

import com.mycompany.grabberrasskazov.GlobalVars;
import com.mycompany.grabberrasskazov.beans.MainBean;
import com.mycompany.grabberrasskazov.controllers.AppContextManager;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;

public class MainThread extends Thread {

    ApplicationContext ctx = AppContextManager.getAppContext();
    MainBean mainBean = (MainBean) ctx.getBean("mainBean");
    ExecutorService executor = Executors.newFixedThreadPool(5);

    @Override
    public void run() {
        try {
            indexCategories();
            indexPages();
            executor.shutdown();
            while (!executor.isTerminated()) {

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void indexCategories() {
        try {
            Document doc = Jsoup.connect(GlobalVars.mainSite + "main.shtml?ras")
                    .userAgent("Opera/9.80 (X11; Linux x86_64) Presto/2.12.388 Version/12.16")
                    .get();
            Elements select = doc.select("a[href*=ras.shtml?kat]");

            mainBean.saveCats(select);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void indexPages() {
        try {
            String href = GlobalVars.mainSite + "ras.shtml?main1_1";
            Document doc = Jsoup.connect(href)
                    .userAgent("Opera/9.80 (X11; Linux x86_64) Presto/2.12.388 Version/12.16")
                    .get();
            Elements numberLinks = doc.select("a[href*=ras.shtml?main1_]");
            getStoriesFromCatPage(href);
            for (Element numberLink : numberLinks) {
                href = GlobalVars.mainSite + numberLink.attr("href");
                getStoriesFromCatPage(href);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void getStoriesFromCatPage(String pageUrl) {
        try {
            Document doc = Jsoup.connect(pageUrl)
                    .userAgent("Opera/9.80 (X11; Linux x86_64) Presto/2.12.388 Version/12.16")
                    .get();
            Elements numberLinks = doc.select("a[href*=ras.shtml?ras]");
            for (Element numberLink : numberLinks) {
                String href = GlobalVars.mainSite + numberLink.attr("href");

                Runnable worker = new ThreadForPageSave(href);
                executor.execute(worker);

//                this.stop();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
