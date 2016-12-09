/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawkbyte.managers;

import com.hawkbyte.model.Activity;
import com.hawkbyte.model.Initiative;
import com.hawkbyte.model.Project;
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
    
    public InitiativeManager() throws SQLException, ClassNotFoundException{
        super();
    }
    
    
    public Initiative getInitiative(int initiativeId) throws SQLException{
        
        
        String query = "Select * from initiative where Id = "+initiativeId;
        Statement stm = _CONNECTION.createStatement();
        ResultSet result = stm.executeQuery(query);
        if(result.next()){
        Initiative initiative = new Initiative();
            initiative.setId(initiativeId);
            initiative.setTitle(result.getString("Title"));
            initiative.setDescription(result.getString("Description"));
            initiative.setStartdate(result.getString("Start_Date"));
            initiative.setEnddate(result.getString("End_Date"));
            
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
    
           public boolean addInitiativePurpose(int initiativeId,String purpose) throws SQLException{
       
           String query = "insert into purpose (Initiative_Id,Purpose) values("+initiativeId+",'"+purpose+"')";
           Statement stm = _CONNECTION.createStatement();
          
           
           if(stm.executeUpdate(query) > 0){
               return true;
           }
            stm.close();
           return false;
       }
       public List<Initiative> getInitiatives(int userId) throws SQLException{
    
        String query = "Select Id,Title,Creation_Date from initiative where userId = "+userId;
        Statement stm = _CONNECTION.createStatement();
        ResultSet owner_result = stm.executeQuery(query);
        List<Initiative> initiatives = new ArrayList<Initiative>();
        if(owner_result != null){
         while(owner_result.next()){
            Initiative initiative = new Initiative();
            initiative.setId(owner_result.getInt("Id"));
            initiative.setTitle(owner_result.getString("Title"));
            initiative.setCreationdate(owner_result.getString("Creation_Date"));
            initiative.setType("Initiative");
            initiative.setRole("owner");
            initiatives.add(initiative);
         }
        }
        
        query = "Select Initiative_Id from ia where userId = "+userId;
         ResultSet id_result = stm.executeQuery(query);
         if(id_result!=null){
         while(id_result.next()){
             query = "Select Id,Title,Creation_Date from initiative where Id = "+id_result.getInt("Initiative_Id");
             ResultSet ia_result = stm.executeQuery(query);
             if(ia_result != null){
             
                  Initiative initiative = new Initiative();
                  initiative.setId(ia_result.getInt("Id"));
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
             if(ia_result.next()){
             Initiative initiative = new Initiative();
             initiative.setId(ia_result.getInt("Id"));
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
             
         public List<Initiative> viewByCollege(int userId,String college) throws SQLException{
    
        String query = "Select Resource_Id from college where Name='"+college+"'";
        Statement stm = _CONNECTION.createStatement();
         ResultSet id_result = stm.executeQuery(query);
          List<Initiative> initiatives = new ArrayList<Initiative>();
         if(id_result!=null){
               
         while(id_result.next()){
             query = "Select Title,Description,Category,Creation_Date from initiative where Id = "+id_result.getInt("Resource_Id");
             ResultSet initiative_result = stm.executeQuery(query);
             if( initiative_result.next()){
             Initiative initiative = new Initiative();
             initiative.setId( initiative_result.getInt("Id"));
             initiative.setTitle( initiative_result.getString("Title"));
              initiative.setCategory(initiative_result.getString("Category"));
             initiative.setCreationdate( initiative_result.getString("Creation Date"));
            
              query = "Select firstname,lastname from users where Id="+userId;
              ResultSet user_result = stm.executeQuery(query);
            if(user_result != null){
             initiative .setOwner(user_result.getString("firstname")+" "+user_result.getString("lastname"));
            }
             
             initiatives.add(initiative);
             }
             
            }
         }
         return initiatives;
             }
         
          public List<Initiative> viewByDepartment(int userId,String department) throws SQLException{
    
        String query = "Select Resource_Id from department where Name='"+department+"'";
        Statement stm = _CONNECTION.createStatement();
         ResultSet id_result = stm.executeQuery(query);
          List<Initiative> initiatives = new ArrayList<Initiative>();
         if(id_result!=null){
               
         while(id_result.next()){
             query = "Select Title,Description,Category,Creation_Date from initiative where Id = "+id_result.getInt("Resource_Id");
             ResultSet initiative_result = stm.executeQuery(query);
             if( initiative_result.next()){
             Initiative initiative = new Initiative();
             initiative.setId( initiative_result.getInt("Id"));
             initiative.setTitle( initiative_result.getString("Title"));
              initiative.setCategory(initiative_result.getString("Category"));
             initiative.setCreationdate( initiative_result.getString("Creation_Date"));
            
              query = "Select firstname,lastname from users where Id="+userId;
              ResultSet user_result = stm.executeQuery(query);
            if(user_result != null){
             initiative .setOwner(user_result.getString("firstname")+" "+user_result.getString("lastname"));
            }
             
             initiatives.add(initiative);
             }
             
            }
         }
         return initiatives;
             }
         
       public List<Initiative> viewByCategory(int userId,String category) throws SQLException{
        String query = "Select Title,Description,Category,Creation_Date from initiative where Category ='"+category+"'";
        Statement stm = _CONNECTION.createStatement();
         ResultSet result = stm.executeQuery(query);
          List<Initiative> initiatives = new ArrayList<Initiative>();
         if(result!=null){
               
         while(result.next()){
           
             Initiative initiative = new Initiative();
             initiative.setId(result.getInt("Id"));
             initiative.setTitle(result.getString("Title"));
              initiative.setCategory(result.getString("Category"));
             initiative.setCreationdate(result.getString("Creation_Date"));
            
              query = "Select firstname,lastname from users where Id="+userId;
              ResultSet user_result = stm.executeQuery(query);
            if(user_result != null){
             initiative .setOwner(user_result.getString("firstname")+" "+user_result.getString("lastname"));
            }
             
             initiatives.add(initiative);
             
             
            }
         }
         return initiatives;
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
             if(ia_result.next()){
             Initiative initiative = new Initiative();
             initiative.setId(ia_result.getInt("Id"));
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
             if(ia_result.next()){
             Initiative initiative = new Initiative();
             initiative.setId(ia_result.getInt("Id"));
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
              
          String query = "Insert into initiative (Title,Description,Start_Date,End_Date,Project_Id,Creation_Date,userId) values ('"
                          +initiative.getTitle()+"','"+initiative.getDescription()+"','"+initiative.getStartdate()+
                           "','"+initiative.getEnddate()+"',-1,'"+format_date+"',"+userId+")";
            
          Statement stm = _CONNECTION.createStatement();
          stm.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
          ResultSet result = stm.getGeneratedKeys();
          if(result.next()){
              return result.getInt(1);
              
          }
         
          
          return -1;
      }   
     
            public boolean updateInitiative(int initiativeId,Initiative initiative) throws SQLException{
          String query = "update initiative set Title='"+initiative.getTitle()+"',Description='"+initiative.getDescription()+
                  "',Start_Date='"+initiative.getStartdate()+"',End_Date='"+initiative.getEnddate()+"' where Id="+initiativeId;
            
          Statement stm = _CONNECTION.createStatement();
          if(stm.executeUpdate(query) > 0){
              return true;
          }
          stm.close();
          return false;
      }  
            
            public boolean deleteInitiative(int userId,int initativeId) throws SQLException{
          String initiative_query = "delete from initiative where Id="+initativeId+" and userId="+userId;
         // String ia_query="delete * from ia where Initiative_Id="+initativeId;  
          
          Statement stm = _CONNECTION.createStatement();
          if(stm.executeUpdate(initiative_query) > 0){
              return true;
          }
          
          
          stm.close();
          return false;
      }       
         
       public List<Activity> getInitiativeActivities(int initiativeId) throws SQLException{
        
         String query = "Select * from activity where Initiative_Id = "+initiativeId;
        Statement stm = _CONNECTION.createStatement();
        ResultSet activity_result = stm.executeQuery(query);
         List<Activity> activities = new ArrayList<Activity>();
  
        if(activity_result != null){
        
        while(activity_result.next()){

        int userId = activity_result.getInt("userId");
        
         
            Activity activity = new Activity();
            activity.setId(activity_result.getInt("Id"));
            activity.setTitle(activity_result.getString("Title"));
            activity.setDescription(activity_result.getString("Description"));
            activity.setCreationdate(activity_result.getString("Creation_Date"));
         
             int parent_initiative = activity_result.getInt("Initiative_Id");
           if(initiativeId == parent_initiative){
            activity.setInitiative("YES");
               
           }else{
            activity.setInitiative("NO");
           }
  
           
         query = "Select firstname,lastname from users where Id="+userId;
         stm = _CONNECTION.createStatement();
         ResultSet user_result = stm.executeQuery(query);
         if(user_result.next()){
             activity.setOwner(user_result.getString("firstname")+" "+user_result.getString("lastname"));
         }
            activities.add(activity);
        }   
             
           
        }
             stm.close();
            return activities;
    }
       
           public List<Project> getInitiativeProjects(int initiativeId) throws SQLException{
        
         String query = "Select * from projects where Id in (select Project_Id from initiative where Id = "+initiativeId+");";
        Statement stm = _CONNECTION.createStatement();
        ResultSet result = stm.executeQuery(query);
        
  
        if(result.next()){
         List<Project> projects = new ArrayList<Project>();
        while(result.next()){
               Project project = new Project();
               project.setId(result.getInt("Id"));
               project.setTitle(result.getString("Title"));
               project.setDescription(result.getString("Description"));
               project.setStartdate(result.getString("Start_Date"));
                project.setEnddate(result.getString("End_Date"));
                if(result.getBoolean("Funded") == true){
                    project.setPiname(result.getString("PI_Name"));
                }
                
              
       
           }
             
            return projects;
        }
             stm.close();
            return null;
    }   
     public List<Activity> iaActivities(int userId,int initiativeId) throws SQLException{
               String ia_query = "Select Initiative_Id from ia where userId="+userId;
           String user_query = "Select firstname,lastname from users where Id="+userId+";";
           Statement stm = _CONNECTION.createStatement();
         
          ResultSet id_result = stm.executeQuery(ia_query);
         List<Activity> activities = new ArrayList<Activity>();
        
          if(id_result != null){
        while(id_result.next()){
            String activity_query = "Select Id,Initiative_Id,Title,Description,Creation_Date from activity where Initiative_Id="+id_result.getInt("Initiative_Id");
             ResultSet activity_result = stm.executeQuery(activity_query);
              Activity activity = new Activity();
            activity.setId(activity_result.getInt("Id"));
            activity.setTitle(activity_result.getString("Title"));
            activity.setDescription(activity_result.getString("Description"));
            activity.setCreationdate(activity_result.getString("Creation_Date"));
        
            int parent_initiative = activity_result.getInt("Initiative_Id");
            if(initiativeId == parent_initiative){
               activity.setInitiative("YES");
                
            }else{
                 activity.setInitiative("NO");
            }
             stm = _CONNECTION.createStatement();
         ResultSet user_result = stm.executeQuery(user_query);
           if(user_result.next()){
             activity.setOwner(user_result.getString("firstname")+" "+user_result.getString("lastname"));     
           }
             
        }
    }
         stm.close();
       return activities;
     
     }
     
     
         public List<Activity> paActivities(int userId, int initiativeId) throws SQLException{
         String pa_query = "Select Project_Id from pa where userId="+userId;
         String user_query = "Select firstname,lastname from users where Id="+userId+";";
         Statement stm = _CONNECTION.createStatement();
         ResultSet id_result = stm.executeQuery(pa_query);
         List<Activity> activities = new ArrayList<Activity>();
        if(id_result != null){
        while(id_result.next()){
            String initiative_query = "Select Id from initiative where Project_Id="+id_result.getInt("Project_Id");
             ResultSet initiative_result = stm.executeQuery(initiative_query);
             
              if(initiative_result != null){
             while(initiative_result.next()){
                 
             String activity_query = "Select Id,Initiative_Id,Title,Description,Creation_Date from activity where Initiative_Id="+initiative_result.getInt("Id")
                                        +" and not Initiative_Id="+initiativeId; 
             ResultSet activity_result = stm.executeQuery(activity_query);
             while(activity_result.next()){
             Activity activity = new Activity();
           activity.setId(activity_result.getInt("Id"));
            activity.setTitle(activity_result.getString("Title"));
            activity.setDescription(activity_result.getString("Description"));
            activity.setCreationdate(activity_result.getString("Creation_Date"));
        
            int parent_initiative = initiative_result.getInt("Initiative_Id");
            if(initiativeId == parent_initiative){
               activity.setInitiative("YES");
                
            }else{
                 activity.setInitiative("NO");
            }
            stm = _CONNECTION.createStatement();
         ResultSet user_result = stm.executeQuery(user_query);
            if(user_result.next()){
             activity.setOwner(user_result.getString("firstname")+" "+user_result.getString("lastname"));   
            }
            activities.add(activity);
             }
             
        }
        }  
        }
    }
         stm.close();
       return activities;
         
    
    }
     
      public List<Activity> linkActivities(int userId,int initiativeId) throws SQLException{
          List<Activity> activities = new ArrayList<Activity>();
            activities.addAll(iaActivities(userId,initiativeId));
            activities.addAll(paActivities(userId, initiativeId));
            activities.addAll(ownerActivities(userId, initiativeId));
          return activities;
          
          
        
      }    
         
    public List<Activity> ownerActivities(int userId,int initiativeId) throws SQLException{
        
        String query = "Select * from activity where userId = "+userId+" and not Initiative_Id="+initiativeId;
         String user_query = "Select firstname,lastname from users where Id="+userId;
        Statement stm = _CONNECTION.createStatement();
         ResultSet result = stm.executeQuery(query);
       
         List<Activity> activities = new ArrayList<Activity>();
         if(result!=null){
                
         while(result.next()){
            
           
               Activity activity = new Activity();
           activity.setId(result.getInt("Id"));
            activity.setTitle(result.getString("Title"));
            activity.setDescription(result.getString("Description"));
            activity.setCreationdate(result.getString("Creation_Date"));
            activity.setActive(false);
        
            int parent_initiative = result.getInt("Initiative_Id");
            if(initiativeId == parent_initiative){
               activity.setInitiative("YES");
                
            }else{
                 activity.setInitiative("NO");
            }
            
            stm = _CONNECTION.createStatement();
         ResultSet user_result = stm.executeQuery(user_query);
              if(user_result.next()){
             activity.setOwner(user_result.getString("firstname")+" "+user_result.getString("lastname"));   
            }
            activities.add(activity);
             
             
            }
         }
            return activities;
    }  
    
    
           public String getOwner(int initiativeId) throws SQLException{
                String query = "select firstname,lastname from users where Id in (select userId from initiative where initiativeId="+initiativeId+");";
                Statement stm = _CONNECTION.createStatement();
                 ResultSet result = stm.executeQuery(query);
                 if(result.next()){
                     String owner = result.getString("firstname")+" "+result.getString("lastName");
                 return owner;
                 
                 }
                return null;
            }
           
                public void setActivities(List<Activity>activities,int initiativeId) throws SQLException{
           Statement stm = _CONNECTION.createStatement();
           for(int i=0;i<activities.size();i++){
               System.out.println(activities.get(i).getId());
            if(activities.get(i).getActive() == true){
           String query = "update activity set Initiative_Id=1 where Id="+activities.get(i).getId();
           stm.executeUpdate(query);
           }
           }
       
       }
}
