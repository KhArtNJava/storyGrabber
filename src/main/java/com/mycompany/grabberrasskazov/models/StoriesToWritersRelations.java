package com.mycompany.grabberrasskazov.models;
// Generated Jan 4, 2015 12:47:44 AM by Hibernate Tools 4.3.1



/**
 * StoriesToWritersRelations generated by hbm2java
 */
public class StoriesToWritersRelations  implements java.io.Serializable {


     private Integer id;
     private Stories stories;
     private StoryWriters storyWriters;

    public StoriesToWritersRelations() {
    }

    public StoriesToWritersRelations(Stories stories, StoryWriters storyWriters) {
       this.stories = stories;
       this.storyWriters = storyWriters;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Stories getStories() {
        return this.stories;
    }
    
    public void setStories(Stories stories) {
        this.stories = stories;
    }
    public StoryWriters getStoryWriters() {
        return this.storyWriters;
    }
    
    public void setStoryWriters(StoryWriters storyWriters) {
        this.storyWriters = storyWriters;
    }




}

