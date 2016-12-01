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

import java.util.ArrayList;
import java.util.List;
public class Activity extends Resource{
    private Audience audience;
    private Course course;
    private Membership participant;
    private String initiative;

    public String getInitiative() {
        return initiative;
    }

    public void setInitiative(String initiative) {
        this.initiative = initiative;
    }
    
    
  
    
}
