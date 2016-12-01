/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawkbyte.managers;

import com.hawkbyte.model.Activity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Reknek
 */
public class ActivityManager extends ResourceManager{
    
    public ActivityManager() throws SQLException{
        super();
    }
    
        public Activity getActivity(int activityId) throws SQLException{
    
        String query = "Select * from activity where Id = "+activityId;
        Statement stm = _CONNECTION.createStatement();
        ResultSet result = stm.executeQuery(query);
        if(result != null){
        Activity activity = new Activity();
            
            activity.setTitle(result.getString("Title"));
            activity.setDescription(result.getString("Description"));
            activity.setStartdate(result.getString("Start Date"));
            activity.setEnddate(result.getString("End Date"));
            
            return activity;
        }
         stm.close();
        return null;
    }
    
             public Map<String,String> getActivityAudience(int activityId) throws SQLException{
        
         String query = "Select Classification,Description from audience where Activity_Id = "+activityId;
        Statement stm = _CONNECTION.createStatement();
        ResultSet result = stm.executeQuery(query);
        if(result != null){
         Map<String, String> audience = new HashMap<String, String>();
        while(result.next()){
            audience.put(result.getString("Classification"),result.getString("Description"));
            
        }
           
            return audience;
        }
         stm.close();
        return null;
    }  
             
        public boolean addAudience(int activityId,String classification,String description) throws SQLException{
       
           String query = "insert into audience (Activity_Id,Classification,Description) values("+activityId+","+classification+","+description+")";
           Statement stm = _CONNECTION.createStatement();
          
           if(stm.executeUpdate(query) > 0){
               return true;
           }
            stm.close();
           return false;
       }       
    
      public Map<String,String> getActivityCourses(int activityId) throws SQLException{
        
         String query = "Select Name,Semester from course where Activity_Id = "+activityId;
        Statement stm = _CONNECTION.createStatement();
        ResultSet result = stm.executeQuery(query);
        if(result != null){
         Map<String, String> courses = new HashMap<String, String>();
        while(result.next()){
            courses.put(result.getString("Semester"),result.getString("Name"));
            
        }
           
            return courses;
        }
         stm.close();
        return null;
    }
    
     public boolean addCourse(int activityId,String semester,String crn) throws SQLException{
       
           String query = "insert into course (crn,Semester) values("+crn+","+semester+")";
           Statement stm = _CONNECTION.createStatement();
          
           if(stm.executeUpdate(query) > 0){
               return true;
           }
            stm.close();
           return false;
       }
     
           public int createActivity(int userId,int initiativeId,Activity activity) throws SQLException{
           LocalDate date = LocalDate.now();
          DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
         String format_date = date.format(format);
          
          String query = "Insert into activity (Title,Description,Start Date,End Date,Initiative_Id,UserId,Creation Date) values ("
                          +activity.getTitle()+","+activity.getDescription()+","+activity.getStartdate()+","+activity.getEnddate()+
                          ","+initiativeId+","+userId+","+format_date+")";
            
          Statement stm = _CONNECTION.createStatement();
          stm.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
          ResultSet result = stm.getGeneratedKeys();
          if(result!= null){
              return result.getInt(1);
              
          }
        
          return -1;
      }
           
       public boolean updateActivity(int activityId,Activity activity) throws SQLException{
          String query = "update activity set Title="+activity.getTitle()+",Description="+activity.getDescription()+
                  ",Start Date="+activity.getStartdate()+",End Date="+activity.getEnddate()+" where Id="+activityId;
            
          Statement stm = _CONNECTION.createStatement();
          if(stm.executeUpdate(query) > 0){
              return true;
          }
          stm.close();
          return false;
      } 
       
       
      public boolean deleteActivity(int activityId) throws SQLException{
          String initiative_query = "delete * from activity where Id="+activityId;
          String ia_query="delete * from ae where Initiative_Id="+activityId;  
          
          Statement stm = _CONNECTION.createStatement();
          if(stm.executeUpdate(initiative_query) > 0 && stm.executeUpdate(ia_query) > 0){
              return true;
          }
          
          
          stm.close();
          return false;
      }
      
             public List<Activity> getActivities(int userId) throws SQLException{
    
       
        String query = "Select Initiative_Id from ia where userId = "+userId;
        Statement stm = _CONNECTION.createStatement();
         ResultSet id_result = stm.executeQuery(query);
         if(id_result!=null){
                List<Activity> activities = new ArrayList<Activity>();
         while(id_result.next()){
             query = "Select Title,Creation Date from initiative where Id = "+id_result.getInt("Initiative_Id");
             ResultSet ia_result = stm.executeQuery(query);
             if(ia_result != null){
             Activity activity = new Activity();
             activity.setTitle(ia_result.getString("Title"));
             activity.setCreationdate(ia_result.getString("Creation Date"));
             activity.setType("Activity");
             activity.setRole("AE");
             activities.add(activity);
             }
             
            }
         }
         
         return null;
             }
             
}
