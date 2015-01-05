package com.mycompany.grabberrasskazov.beans;

import com.mycompany.grabberrasskazov.models.Stories;
import com.mycompany.grabberrasskazov.models.StoryCategories;
import com.mycompany.grabberrasskazov.models.StoryWriters;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

@ComponentScan
public class MainBean {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public StoryWriters saveWriter(StoryWriters storyWriter) {
        Session session = sessionFactory.getCurrentSession();

        List<StoryWriters> list = session
                .createQuery("from StoryWriters a "
                        + "where a.oldId=? ")
                .setParameter(0, storyWriter.getOldId())
                .list();

        if (list.size() < 1) {
            session.saveOrUpdate(storyWriter);
        } else {
            storyWriter = list.get(0);
        }

        return storyWriter;

    }

    @Transactional
    public void saveCats(Elements selects) {
        Session session = sessionFactory.getCurrentSession();

        for (Element select : selects) {
            String catName = select.text();
            String catOldId = select.attr("href");

            StoryCategories category = new StoryCategories();
            category.setCategoryName(catName);
            category.setOldId(catOldId);

            List<StoryCategories> list = session
                    .createQuery("from StoryCategories a "
                            + "where a.oldId=? ")
                    .setParameter(0, catOldId)
                    .list();

            if (list.size() < 1) {
                session.getTransaction().begin();
                session.saveOrUpdate(category);
                session.getTransaction().commit();
            }

        }
    }

    @Transactional
    public void saveStory(Stories r) {

        try {

            Session session = sessionFactory.getCurrentSession();

            List<Stories> list = session
                    .createQuery("from Stories a "
                            + "where a.oldId=? ")
                    .setParameter(0, r.getOldId())
                    .list();

            if (list.size() > 0) {
//                category = list.get(0);
            } else {
                session.save(r);
            }
        } catch (Exception ex) {
            System.out.println(r.getStoryText());
            ex.printStackTrace();
        }
    }

    @Transactional
    public boolean storyExists(String oldId) {
        try {
            Session session = sessionFactory.getCurrentSession();

            List<Stories> list = session
                    .createQuery("from Stories a "
                            + "where a.oldId=? ")
                    .setParameter(0, oldId)
                    .list();

            if (list.size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Transactional
    public StoryCategories getCat(String catId) {
        Session session = sessionFactory.getCurrentSession();

        List<StoryCategories> list = session
                .createQuery("from StoryCategories a "
                        + "where a.oldId=? ")
                .setParameter(0, catId)
                .list();

        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    private String categoryToString(StoryCategories category) {
        StringBuilder sb = new StringBuilder();

        sb.append("ID: ");
        sb.append(category.getCategoryId());
        sb.append("\r\n");

        sb.append("Cat Name: ");
        sb.append(category.getCategoryName());
        sb.append("\r\n");

        sb.append("Cat Old Id: ");
        sb.append(category.getOldId());
        sb.append("\r\n");

        return sb.toString();
    }

}
