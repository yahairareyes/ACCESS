/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawkbyte.managers;

/**
 *
 * @author Reknek
 */

import com.hawkbyte.database.DatabaseConnection;
import com.hawkbyte.database.Properties;
import com.hawkbyte.model.Initiative;
import com.hawkbyte.model.Membership;
import com.hawkbyte.model.Project;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class ProjectManager extends ResourceManager{
 
    
    public ProjectManager() throws SQLException, ClassNotFoundException{ 
       super();
    }
    
    public Project getProject(int projectId) throws SQLException{
    
        String query = "Select * from project where Id = "+projectId;
        Statement stm = _CONNECTION.createStatement();
        ResultSet result = stm.executeQuery(query);
        if(result.next()){
        Project project = new Project();
            
            project.setTitle(result.getString("Title"));
            project.setDescription(result.getString("Description"));
            project.setStartdate(result.getString("Start Date"));
            project.setEnddate(result.getString("End Date"));
            
            return project;
        }
         stm.close();
        return null;
    }
    
    
        public List<String> getProjectGoals(int projectId) throws SQLException{
        
         String query = "Select * from goal where Project_Id = "+projectId;
        Statement stm = _CONNECTION.createStatement();
        ResultSet result = stm.executeQuery(query);
        if(result.next()){
         List<String> goals = new ArrayList<String>();
        while(result.next()){
            goals.add(result.getString("Goal"));
            
        }
           
            return goals;
        }
         stm.close();
        return null;
    }
        
 
                 public boolean addGoal(int projectId,String goal) throws SQLException{
       
           String query = "insert into goal (Project_Id,Goal) values("+projectId+","+goal+")";
           Statement stm = _CONNECTION.createStatement();
          
           
           if(stm.executeUpdate(query) > 0){
               return true;
           }
            stm.close();
           return false;
       }
          
        public List<Initiative> getProjectInitiatives(int projectId) throws SQLException{
        
         String query = "Select * from initiative where Project_Id = "+projectId;
        Statement stm = _CONNECTION.createStatement();
        ResultSet initiative_result = stm.executeQuery(query);
        
        
        
        if(initiative_result.next()){
         List<Initiative> initiatives = new ArrayList<Initiative>();
        while(initiative_result.next()){

        int initiative_id = initiative_result.getInt("Id");
        int userId = initiative_result.getInt("userId");
        
      
            Initiative initiative = new Initiative();
            initiative.setTitle(initiative_result.getString("Title"));
            initiative.setDescription(initiative_result.getString("Description"));
            initiative.setCreationdate(initiative_result.getString("Creation Date"));
        
            int parent_project = initiative_result.getInt("Project_Id");
            if(projectId == parent_project){
                initiative.setActivity("YES");
                
            }else{
                 initiative.setActivity("NO");
            }
            
         query = "Select username from users where Id="+userId;
         ResultSet user_result = stm.executeQuery(query);
         if(user_result != null){
             initiative.setOwner(user_result.getString("username"));
         }
            
        }   
             
            return initiatives;
        }
             stm.close();
            return null;
    }
     
    public List<Initiative> linkInitiative(int userId, int projectId) throws SQLException{
        
         String query = "Select Title,Description,Creation Date from initiative inner join pa on userId="+userId
                        +" inner join ia on userId="+userId+" where userId"+userId;
 
        Statement stm = _CONNECTION.createStatement();
        ResultSet initiative_result = stm.executeQuery(query);
     
        if(initiative_result != null){
         List<Initiative> initiatives = new ArrayList<Initiative>();
        while(initiative_result.next()){
      
            Initiative initiative = new Initiative();
            initiative.setTitle(initiative_result.getString("Title"));
            initiative.setDescription(initiative_result.getString("Description"));
            initiative.setCreationdate(initiative_result.getString("Creation Date"));
        
            int parent_project = initiative_result.getInt("Project_Id");
            if(projectId == parent_project){
                initiative.setActivity("YES");
                
            }else{
                 initiative.setActivity("NO");
            }
           
         query = "Select username from users where Id="+userId;
         ResultSet user_result = stm.executeQuery(query);
         if(user_result != null){
             initiative.setOwner(user_result.getString("username"));
         }
            
        }   
             
            return initiatives;
        }
             stm.close();
            return null;
    }    
        
      public List<Project> getFundedProjects(int userId) throws SQLException{
    
        String query = "Select * from project where userId = "+userId+" and Funded = 1";
        Statement stm = _CONNECTION.createStatement();
        ResultSet result = stm.executeQuery(query);
        if(result != null){
        List<Project> projects = new ArrayList<Project>();
        while(result.next()){
            Project project = new Project();
            project.setTitle(result.getString("Title"));
            project.setDescription(result.getString("Description"));
                
        }
            return projects;
        }
        stm.close();
        return null;
    }
        
      public List<Project> getProjects(int userId) throws SQLException{
    
        String query = "Select Title,Creation Date from project where userId = "+userId;
        Statement stm = _CONNECTION.createStatement();
        ResultSet owner_result = stm.executeQuery(query);
        List<Project> projects = new ArrayList<Project>();
        if(owner_result != null){
         while(owner_result.next()){
            Project project = new Project();
            project.setTitle(owner_result.getString("Title"));
            project.setCreationdate(owner_result.getString("Creation Date"));
            project.setType("Project");
            project.setRole("owner");
            projects.add(project);
         }
        }
         
        query = "Select Project_Id from pa where userId = "+userId;
         ResultSet id_result = stm.executeQuery(query);
         if(id_result!=null){
         while(id_result.next()){
             query = "Select Title,Creation Date from project where Id = "+id_result.getInt("Project_Id");
             ResultSet pa_result = stm.executeQuery(query);
             if(pa_result != null){
             
                  Project project = new Project();
                  project.setTitle(pa_result.getString("Title"));
                   project.setCreationdate(pa_result.getString("Creation Date"));
                    project.setType("Project");
                    project.setRole("PA");
                    projects.add(project);
             }
             
            }
         }
         stm.close();
        return projects;
    }
     
     public List<Project> getDepartmentProjects() throws SQLException{
    
        String query = "Select Resource_Id from department";
        Statement stm = _CONNECTION.createStatement();
         ResultSet id_result = stm.executeQuery(query);
         if(id_result!=null){
                List<Project> projects = new ArrayList<Project>();
         while(id_result.next()){
             query = "Select Title,Creation Date from project where Id = "+id_result.getInt("Resource_Id");
             ResultSet ia_result = stm.executeQuery(query);
             if(ia_result != null){
             Project project = new Project();
             project.setTitle(ia_result.getString("Title"));
             project.setCreationdate(ia_result.getString("Creation Date"));
             project.setType("Project");
             project.setRole("");
             projects.add(project);
             }
             
            }
         }
         return null;
             }
             

          public List<Project> getCollegeProjects() throws SQLException{
    
        String query = "Select Resource_Id from college";
        Statement stm = _CONNECTION.createStatement();
         ResultSet id_result = stm.executeQuery(query);
         if(id_result!=null){
                List<Project> projects = new ArrayList<Project>();
         while(id_result.next()){
             query = "Select Title,Creation Date from project where Id = "+id_result.getInt("Resource_Id");
             ResultSet ia_result = stm.executeQuery(query);
             if(ia_result != null){
             Project project = new Project();
             project.setTitle(ia_result.getString("Title"));
             project.setCreationdate(ia_result.getString("Creation Date"));
             project.setType("Project");
             project.setRole("");
             projects.add(project);
             }
             
            }
         }
         return null;
             }
      
                public List<Project> getUniversityProjects() throws SQLException{
    
        String query = "Select Resource_Id from university";
        Statement stm = _CONNECTION.createStatement();
         ResultSet id_result = stm.executeQuery(query);
         if(id_result.next()){
                List<Project> projects = new ArrayList<Project>();
         while(id_result.next()){
             query = "Select Title,Creation Date from project where Id = "+id_result.getInt("Resource_Id");
             ResultSet ia_result = stm.executeQuery(query);
             if(ia_result != null){
             Project project = new Project();
             project.setTitle(ia_result.getString("Title"));
             project.setCreationdate(ia_result.getString("Creation Date"));
             project.setType("Project");
             project.setRole("");
             projects.add(project);
             }
             
            }
         }
         return null;
             }
          
      public int createProject(int userId,Project project) throws SQLException{
           LocalDate date = LocalDate.now();
          DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
         String format_date = date.format(format);
          
          String query = "Insert into project (Description,Start Date,End Date,Title,UserId,Creation Date) values ("
                          +project.getDescription()+","+project.getStartdate()+","+project.getEnddate()+","+project.getTitle()+
                          ","+userId+","+format_date+")";
            
          Statement stm = _CONNECTION.createStatement();
          stm.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
          ResultSet result = stm.getGeneratedKeys();
          if(result!= null){
              return result.getInt(1);
              
          }
         
          
          return -1;
      }
      
        public boolean updateProject(int projectId,Project project) throws SQLException{
          String query = "update project set Description="+project.getDescription()+",Start Date="+project.getStartdate()+",End Date="+
                          project.getEnddate()+",Title"+project.getTitle()+" where Id="+projectId;
            
          Statement stm = _CONNECTION.createStatement();
          if(stm.executeUpdate(query) > 0){
              return true;
          }
          stm.close();
          return false;
      }
        
       public boolean deleteProject(int projectId) throws SQLException{
          String project_query = "delete * from project where Id="+projectId;
          String pa_query="delete * from pa where Project_Id="+projectId;  
          
          Statement stm = _CONNECTION.createStatement();
          if(stm.executeUpdate(project_query) > 0 && stm.executeUpdate(pa_query) > 0){
              return true;
          }
          
          
          stm.close();
          return false;
      }
        
}
