/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawkbyte.managers;

import com.hawkbyte.model.Activity;
import com.hawkbyte.model.Initiative;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Reknek
 */
public class InitiativeManager extends ResourceManager{
    
    public InitiativeManager() throws SQLException{
        super();
    }
    
    
    public Initiative getInitiative(int initiativeId) throws SQLException{
        
        
        String query = "Select * from initiative where Id = "+initiativeId;
        Statement stm = _CONNECTION.createStatement();
        ResultSet result = stm.executeQuery(query);
        if(result != null){
        Initiative initiative = new Initiative();
            
            initiative.setTitle(result.getString("Title"));
            initiative.setDescription(result.getString("Description"));
            initiative.setStartdate(result.getString("Start Date"));
            initiative.setEnddate(result.getString("End Date"));
            
            return initiative;
        }
         stm.close();
        return null;
    }
    
    
     public List<String> getInitiativePurposes(int initiativeId) throws SQLException{
        
         String query = "Select Purpose from purpose where Initiative_Id = "+initiativeId;
        Statement stm = _CONNECTION.createStatement();
        ResultSet result = stm.executeQuery(query);
        if(result != null){
         List<String> purposes = new ArrayList<String>();
        while(result.next()){
            purposes.add(result.getString("Purpose"));
            
        }
           
            return purposes;
        }
         stm.close();
        return null;
    }
    
       public List<Initiative> getInitiatives(int userId) throws SQLException{
    
        String query = "Select Title,Creation Date from initiative where userId = "+userId;
        Statement stm = _CONNECTION.createStatement();
        ResultSet owner_result = stm.executeQuery(query);
        List<Initiative> initiatives = new ArrayList<Initiative>();
        if(owner_result != null){
         while(owner_result.next()){
            Initiative initiative = new Initiative();
            initiative.setTitle(owner_result.getString("Title"));
            initiative.setCreationdate(owner_result.getString("Creation Date"));
            initiative.setType("Initiative");
            initiative.setRole("owner");
            initiatives.add(initiative);
         }
        }
        
        query = "Select Initiative_Id from ia where userId = "+userId;
         ResultSet id_result = stm.executeQuery(query);
         if(id_result!=null){
         while(id_result.next()){
             query = "Select Title,Creation Date from initiative where Id = "+id_result.getInt("Initiative_Id");
             ResultSet ia_result = stm.executeQuery(query);
             if(ia_result != null){
             
                  Initiative initiative = new Initiative();
                  initiative.setTitle(ia_result.getString("Title"));
                   initiative.setCreationdate(ia_result.getString("Creation Date"));
                    initiative.setType("Initiative");
                   initiative.setRole("IA");
                   initiatives.add(initiative);
             }
             
            }
         }
         stm.close();
        return initiatives;
    }
       
         public List<Initiative> getDepartmentInitiatives() throws SQLException{
    
        String query = "Select Resource_Id from department";
        Statement stm = _CONNECTION.createStatement();
         ResultSet id_result = stm.executeQuery(query);
         if(id_result!=null){
                List<Initiative> initiatives = new ArrayList<Initiative>();
         while(id_result.next()){
             query = "Select Title,Creation Date from initiative where Id = "+id_result.getInt("Resource_Id");
             ResultSet ia_result = stm.executeQuery(query);
             if(ia_result != null){
             Initiative initiative = new Initiative();
             initiative.setTitle(ia_result.getString("Title"));
             initiative.setCreationdate(ia_result.getString("Creation Date"));
             initiative.setType("Initiative");
             initiative.setRole("");
             initiatives.add(initiative);
             }
             
            }
         }
         return null;
             }
             
         public List<Initiative> getCollegeInitiatives() throws SQLException{
    
        String query = "Select Resource_Id from college";
        Statement stm = _CONNECTION.createStatement();
         ResultSet id_result = stm.executeQuery(query);
         if(id_result!=null){
                List<Initiative> initiatives = new ArrayList<Initiative>();
         while(id_result.next()){
             query = "Select Title,Creation Date from initiative where Id = "+id_result.getInt("Resource_Id");
             ResultSet ia_result = stm.executeQuery(query);
             if(ia_result != null){
             Initiative initiative = new Initiative();
             initiative.setTitle(ia_result.getString("Title"));
             initiative.setCreationdate(ia_result.getString("Creation Date"));
             initiative.setType("Initiative");
             initiative.setRole("");
             initiatives.add(initiative);
             }
             
            }
         }
         return null;
             }
             
     public List<Initiative> getUniversityInitiatives() throws SQLException{
    
        String query = "Select Resource_Id from university";
        Statement stm = _CONNECTION.createStatement();
         ResultSet id_result = stm.executeQuery(query);
         if(id_result!=null){
                List<Initiative> initiatives = new ArrayList<Initiative>();
         while(id_result.next()){
             query = "Select Title,Creation Date from initiative where Id = "+id_result.getInt("Resource_Id");
             ResultSet ia_result = stm.executeQuery(query);
             if(ia_result != null){
             Initiative initiative = new Initiative();
             initiative.setTitle(ia_result.getString("Title"));
             initiative.setCreationdate(ia_result.getString("Creation Date"));
             initiative.setType("Initiative");
             initiative.setRole("");
             initiatives.add(initiative);
             }
             
            }
         }
         return null;
             }    
          public int createInitiative(int userId,Initiative initiative) throws SQLException{
                 LocalDate date = LocalDate.now();
          DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
         String format_date = date.format(format);
              
          String query = "Insert into initiative (Title,Description,Start Date,End Date,Project Id,Creation Date,UserId) values ("
                          +initiative.getTitle()+","+initiative.getDescription()+","+initiative.getStartdate()+
                           ","+initiative.getEnddate()+",-1,"+format_date+","+userId+")";
            
          Statement stm = _CONNECTION.createStatement();
          stm.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
          ResultSet result = stm.getGeneratedKeys();
          if(result!= null){
              return result.getInt(1);
              
          }
         
          
          return -1;
      }   
     
            public boolean updateInitiative(int initiativeId,Initiative initiative) throws SQLException{
          String query = "update initiative set Title="+initiative.getTitle()+",Description="+initiative.getDescription()+
                  ",Start Date="+initiative.getStartdate()+",End Date="+initiative.getEnddate()+" where Id="+initiativeId;
            
          Statement stm = _CONNECTION.createStatement();
          if(stm.executeUpdate(query) > 0){
              return true;
          }
          stm.close();
          return false;
      }  
            
            public boolean deleteInitiative(int initativeId) throws SQLException{
          String initiative_query = "delete * from initative where Id="+initativeId;
          String ia_query="delete * from ia where Initiative_Id="+initativeId;  
          
          Statement stm = _CONNECTION.createStatement();
          if(stm.executeUpdate(initiative_query) > 0 && stm.executeUpdate(ia_query) > 0){
              return true;
          }
          
          
          stm.close();
          return false;
      }       
         
       public List<Activity> getInitiativeActivities(int initiativeId) throws SQLException{
        
         String query = "Select * from activities where Initiative_Id = "+initiativeId;
        Statement stm = _CONNECTION.createStatement();
        ResultSet activity_result = stm.executeQuery(query);
        
  
        if(activity_result != null){
         List<Activity> activities = new ArrayList<Activity>();
        while(activity_result.next()){

        int userId = activity_result.getInt("userId");
        
         
            Activity activity = new Activity();
            activity.setTitle(activity_result.getString("Title"));
            activity.setDescription(activity_result.getString("Description"));
            activity.setCreationdate(activity_result.getString("Creation Date"));
         
             int parent_initiative = activity_result.getInt("Initiative_Id");
           if(initiativeId == parent_initiative){
            activity.setInitiative("YES");
               
           }else{
            activity.setInitiative("NO");
           }
  
           
         query = "Select username from users where Id="+userId;
         ResultSet user_result = stm.executeQuery(query);
         if(user_result != null){
             activity.setOwner(user_result.getString("username"));
         }
            
        }   
             
            return activities;
        }
             stm.close();
            return null;
    }
       
    public List<Activity> linkActivities(int userId,int initiativeId) throws SQLException{
        
         String query = "Select * from activities inner join pa on userId="+userId
                        +" inner join ia on userId="+userId+" where userId="+userId;
        Statement stm = _CONNECTION.createStatement();
        ResultSet activity_result = stm.executeQuery(query);
        
  
        if(activity_result != null){
         List<Activity> activities = new ArrayList<Activity>();
        while(activity_result.next()){

         
            Activity activity = new Activity();
            activity.setTitle(activity_result.getString("Title"));
            activity.setDescription(activity_result.getString("Description"));
            activity.setCreationdate(activity_result.getString("Creation Date"));
            
           int parent_initiative = activity_result.getInt("Initiative_Id");
           if(initiativeId == parent_initiative){
            activity.setInitiative("YES");
               
           }else{
            activity.setInitiative("NO");
           }
           
         query = "Select username from users where Id="+userId;
         ResultSet user_result = stm.executeQuery(query);
         if(user_result != null){
             activity.setOwner(user_result.getString("username"));
         }
            
        }   
             
            return activities;
        }
             stm.close();
            return null;
    }  
       
}
