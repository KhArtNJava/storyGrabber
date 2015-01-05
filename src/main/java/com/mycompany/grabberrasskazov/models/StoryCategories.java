package com.mycompany.grabberrasskazov.models;
// Generated Jan 4, 2015 12:47:44 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * StoryCategories generated by hbm2java
 */
public class StoryCategories  implements java.io.Serializable {


     private Integer categoryId;
     private String categoryName;
     private String oldId;
     private Set<StoriesToCategoriessRelations> storiesToCategoriessRelationses = new HashSet<StoriesToCategoriessRelations>(0);

    public StoryCategories() {
    }

    public StoryCategories(String categoryName, String oldId, Set<StoriesToCategoriessRelations> storiesToCategoriessRelationses) {
       this.categoryName = categoryName;
       this.oldId = oldId;
       this.storiesToCategoriessRelationses = storiesToCategoriessRelationses;
    }
   
    public Integer getCategoryId() {
        return this.categoryId;
    }
    
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public String getCategoryName() {
        return this.categoryName;
    }
    
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getOldId() {
        return this.oldId;
    }
    
    public void setOldId(String oldId) {
        this.oldId = oldId;
    }
    public Set<StoriesToCategoriessRelations> getStoriesToCategoriessRelationses() {
        return this.storiesToCategoriessRelationses;
    }
    
    public void setStoriesToCategoriessRelationses(Set<StoriesToCategoriessRelations> storiesToCategoriessRelationses) {
        this.storiesToCategoriessRelationses = storiesToCategoriessRelationses;
    }




}


