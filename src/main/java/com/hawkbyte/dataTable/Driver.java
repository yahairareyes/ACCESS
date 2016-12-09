/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawkbyte.dataTable;

import com.hawkbyte.model.Activity;
import com.hawkbyte.model.Membership;
import com.hawkbyte.model.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raizo
 */
public class Driver {
    
    public List<Resource> getResources(){
    
        List<Resource> activities = new ArrayList<Resource>();
        
        for(int i=0;i<5;i++){
             Activity activity = new Activity();
             activity.setId(i);
             activity.setTitle("Title"+i);
             activity.setCreationdate("00/00/00");
             activity.setType("Activity");
             activity.setRole("AE");
             activities.add(activity);
        }
    return activities;
    } 
    
        public List<Membership> getMembership(){
    
        List<Membership> membership = new ArrayList<Membership>();
        
        for(int i=0;i<5;i++){
             Membership mem = new Membership();
             mem.setName("name"+i);
               mem.setEmail("email@domain"+i);
             mem.setRole("role"+i);
             membership.add(mem);
        }
    return membership;
    } 
    
    
}
