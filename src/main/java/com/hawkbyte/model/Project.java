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
import java.util.List;
public class Project extends Resource{
   // private String sender;
    //private String action;
    
    private String goal;
    private String objetive;
    private String website;
    private Document document;
    private Membership membership;
    private String piname;
    private String Copi;
  
    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getObjetive() {
        return objetive;
    }

    public void setObjetive(String objetive) {
        this.objetive = objetive;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }
   
    public String getPiname() {
        return piname;
    }

    public void setPiname(String piname) {
        this.piname = piname;
    }

    public String getCopi() {
        return Copi;
    }

    public void setCopi(String Copi) {
        this.Copi = Copi;
    }

    
    

     
}
