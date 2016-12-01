/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawkbyte.model;

/**
 *
 * @author Reknek
 */
public class Analysis extends User{
    private String type;
    private String category;
     private String clasification;
      private boolean graduates;
       private String majorfield;
        private String department;
         private int timetodegree;
          private boolean advancement;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getClasification() {
        return clasification;
    }

    public void setClasification(String clasification) {
        this.clasification = clasification;
    }

    public boolean isGraduates() {
        return graduates;
    }

    public void setGraduates(boolean graduates) {
        this.graduates = graduates;
    }

    public String getMajorfield() {
        return majorfield;
    }

    public void setMajorfield(String majorfield) {
        this.majorfield = majorfield;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getTimetodegree() {
        return timetodegree;
    }

    public void setTimetodegree(int timetodegree) {
        this.timetodegree = timetodegree;
    }

    public boolean isAdvancement() {
        return advancement;
    }

    public void setAdvancement(boolean advancement) {
        this.advancement = advancement;
    }
          
          
    
}
