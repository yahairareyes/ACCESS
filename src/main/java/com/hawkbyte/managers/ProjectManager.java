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
            project.setId(projectId);
            project.setFunded(result.getBoolean("Funded"));
            project.setTitle(result.getString("Title"));
            project.setDescription(result.getString("Description"));
            project.setStartdate(result.getString("Start_Date"));
            project.setEnddate(result.getString("End_Date"));
            
            return project;
        }
         stm.close();
        return null;
    }
    
    
        public List<String> getProjectGoals(int projectId) throws SQLException{
        
         String query = "Select * from goal where Project_Id = "+projectId+";";
        Statement stm = _CONNECTION.createStatement();
        ResultSet result = stm.executeQuery(query);
         List<String> goals = new ArrayList<String>();
        if(result.next()){
        
        while(result.next()){
            goals.add(result.getString("Goal"));
            
        }
           
            
        }
         stm.close();
        return goals;
    }
        
 
                 public boolean addProjectGoal(int projectId,String goal) throws SQLException{
       
                     if(!goal.equals("")){
           String query = "insert into goal (Project_Id,Goal) values("+projectId+",'"+goal+"');";
           Statement stm = _CONNECTION.createStatement();
          
           
           if(stm.executeUpdate(query) > 0){
                stm.close();
               return true;
           }
            stm.close();
                     }
           return false;
       }
          
        public List<Initiative> getProjectInitiatives(int projectId) throws SQLException{
        
         String query = "Select * from initiative where Project_Id = "+projectId;
        Statement stm = _CONNECTION.createStatement();
        ResultSet initiative_result = stm.executeQuery(query);
         List<Initiative> initiatives = new ArrayList<Initiative>();
        
        
        if(initiative_result!=null){
        
        while(initiative_result.next()){

        int initiative_id = initiative_result.getInt("Id");
        int userId = initiative_result.getInt("userId");
        
      
            Initiative initiative = new Initiative();
            initiative.setId(initiative_result.getInt("Id"));
            initiative.setTitle(initiative_result.getString("Title"));
            initiative.setDescription(initiative_result.getString("Description"));
            initiative.setCreationdate(initiative_result.getString("Creation_Date"));
        
            int parent_project = initiative_result.getInt("Project_Id");
            if(projectId == parent_project){
                initiative.setActivity("YES");
                
            }else{
                 initiative.setActivity("NO");
            }
            stm = _CONNECTION.createStatement();
         query = "Select firstname,lastname from users where Id="+userId+";";
         ResultSet user_result = stm.executeQuery(query);
         if(user_result.next()){
             initiative.setOwner(user_result.getString("firstname")+" "+user_result.getString("lastname"));
         }
            initiatives.add(initiative);
        }   
             
           
        }
             stm.close();
             return initiatives;
    }
     
    public List<Initiative> iaInitiatives(int userId, int projectId) throws SQLException{
         String ia_query = "Select Initiative_Id from ia where userId="+userId;
          String user_query = "Select firstname,lastname from users where Id="+userId;
           Statement stm = _CONNECTION.createStatement();
       
          ResultSet id_result = stm.executeQuery(ia_query);
         List<Initiative> initiatives = new ArrayList<Initiative>();
        if(id_result.next()){
        while(id_result.next()){
            String initiative_query = "Select Id,Project_Id,Title,Description,Creation_Date from initiative where Initiative_Id="+id_result.getInt("Initiative_Id");
             ResultSet initiative_result = stm.executeQuery(initiative_query);
             
              if(initiative_result != null){
             while(initiative_result.next()){
             Initiative initiative = new Initiative();
            initiative.setId(initiative_result.getInt("Id"));
            initiative.setTitle(initiative_result.getString("Title"));
            initiative.setDescription(initiative_result.getString("Description"));
            initiative.setCreationdate(initiative_result.getString("Creation_Date"));
        
            int parent_project = initiative_result.getInt("Project_Id");
            if(projectId == parent_project){
                initiative.setActivity("YES");
                
            }else{
                 initiative.setActivity("NO");
            }
            stm = _CONNECTION.createStatement();
         ResultSet user_result = stm.executeQuery(user_query);
            if(user_result != null){
             initiative.setOwner(user_result.getString("firstname")+" "+user_result.getString("lastname"));   
            }
            initiatives.add(initiative);
        }
        }  
        }
    }
         stm.close();
       return initiatives;
         
    
    }    
   
        
    public List<Initiative> ownerInitiatives(int userId, int projectId) throws SQLException{
        
         String owner_query = "Select Id,Project_Id,Title,Description,Creation_Date from initiative where userId="+userId+" and not Project_Id="+projectId;
         String user_query = "Select firstname,lastname from users where Id="+userId+";";
        Statement stm = _CONNECTION.createStatement();
     
        ResultSet initiative_result = stm.executeQuery(owner_query);
         List<Initiative> initiatives = new ArrayList<Initiative>();
     
       if(initiative_result != null){
        while(initiative_result.next()){
      
            Initiative initiative = new Initiative();
            initiative.setId(initiative_result.getInt("Id"));
            initiative.setTitle(initiative_result.getString("Title"));
            initiative.setDescription(initiative_result.getString("Description"));
            initiative.setCreationdate(initiative_result.getString("Creation_Date"));
        
            int parent_project = initiative_result.getInt("Project_Id");
            if(projectId == parent_project){
                initiative.setActivity("YES");
                
            }else{
                 initiative.setActivity("NO");
            }
            stm = _CONNECTION.createStatement();
         ResultSet user_result = stm.executeQuery(user_query);
           if(user_result.next()){
             initiative.setOwner(user_result.getString("firstname")+" "+user_result.getString("lastname"));    
           }
           initiatives.add(initiative);
        }   

       }
            
        
             stm.close();
           return initiatives;
    }    
        
    public List<Initiative> linkInitiative(int userId, int projectId) throws SQLException{
        List<Initiative> initiatives = new ArrayList<Initiative>();
        
        initiatives.addAll(ownerInitiatives(userId,projectId));
        initiatives.addAll(iaInitiatives(userId,projectId));
        
        return initiatives;
    
    }
      public List<Project> getFundedProjects(int userId) throws SQLException{
    
        String query = "Select Id,Title,Description from project where userId = "+userId+" and Funded=true";
        Statement stm = _CONNECTION.createStatement();
        ResultSet result = stm.executeQuery(query);
        List<Project> projects = new ArrayList<Project>();
        if(result!=null){
        
        while(result.next()){
            Project project = new Project();
            project.setId(result.getInt("Id"));
            project.setTitle(result.getString("Title"));
            project.setDescription(result.getString("Description"));
               projects.add(project); 
        }
            
        }
        stm.close();
       return projects;
    }
        
      public List<Project> getProjects(int userId) throws SQLException{
    
        String query = "Select Id,Title,Creation_Date from project where userId = "+userId;
        Statement stm = _CONNECTION.createStatement();
        ResultSet owner_result = stm.executeQuery(query);
        List<Project> projects = new ArrayList<Project>();
        if(owner_result != null){
         while(owner_result.next()){
            Project project = new Project();
            project.setId(owner_result.getInt("Id"));
            project.setTitle(owner_result.getString("Title"));
            project.setCreationdate(owner_result.getString("Creation_Date"));
            project.setType("Project");
            project.setRole("owner");
            projects.add(project);
         }
        }
         
        query = "Select Project_Id from pa where userId = "+userId;
        stm = _CONNECTION.createStatement();
         ResultSet id_result = stm.executeQuery(query);
         if(id_result!=null){
         while(id_result.next()){
             query = "Select Id,Title,Creation_Date from project where Id = "+id_result.getInt("Project_Id");
             stm = _CONNECTION.createStatement();
             ResultSet pa_result = stm.executeQuery(query);
             if(pa_result.next()){
             
                  Project project = new Project();
                  project.setId(pa_result.getInt("Id"));
                  project.setTitle(pa_result.getString("Title"));
                   project.setCreationdate(pa_result.getString("Creation_Date"));
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
             query = "Select Title,Creation_Date from project where Id = "+id_result.getInt("Resource_Id");
             ResultSet ia_result = stm.executeQuery(query);
             if(ia_result != null){
             Project project = new Project();
             project.setId(ia_result.getInt("Id"));
             project.setTitle(ia_result.getString("Title"));
             project.setCreationdate(ia_result.getString("Creation_Date"));
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
             query = "Select Title,Creation_Date from project where Id = "+id_result.getInt("Resource_Id");
             ResultSet ia_result = stm.executeQuery(query);
             if(ia_result != null){
             Project project = new Project();
             project.setId(ia_result.getInt("Id"));
             project.setTitle(ia_result.getString("Title"));
             project.setCreationdate(ia_result.getString("Creation_Date"));
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
             query = "Select Title,Creation_Date from project where Id = "+id_result.getInt("Resource_Id");
             ResultSet ia_result = stm.executeQuery(query);
             if(ia_result != null){
             Project project = new Project();
             project.setId(ia_result.getInt("Id"));
             project.setTitle(ia_result.getString("Title"));
             project.setCreationdate(ia_result.getString("Creation_Date"));
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
          
         String query = "insert into project (Funded,Description,Start_Date,End_Date,PI_Name,Title,userId,Creation_Date) values (0,'"
                          +project.getDescription()+"','"+project.getStartdate()+"','"+project.getEnddate()+"','tmp','"+project.getTitle()+
                          "',"+userId+",'"+format_date+"');";
            
                        
                          
          Statement stm = _CONNECTION.createStatement();
          stm.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
          ResultSet result = stm.getGeneratedKeys();
          if(result.next()){
              return result.getInt(1);
              
          }
         
          
          return -1;
      }
      
        public boolean updateProject(int projectId,Project project) throws SQLException{
          String query = "update project set Description='"+project.getDescription()+"',Start_Date='"+project.getStartdate()+"',End_Date='"+
                          project.getEnddate()+"',Title='"+project.getTitle()+"' where Id="+projectId;
            
          Statement stm = _CONNECTION.createStatement();
          if(stm.executeUpdate(query) > 0){
              return true;
          }
          stm.close();
          return false;
      }
        
       public boolean deleteProject(int userId, int projectId) throws SQLException{
          String project_query = "delete from project where Id="+projectId+" and userId="+userId;
         // String pa_query="delete from pa where Project_Id="+projectId;  
          
          Statement stm = _CONNECTION.createStatement();
          if(stm.executeUpdate(project_query) > 0){
              return true;
          }
          
          
          stm.close();
          return false;
      }
       
       public void setInitiatives(List<Initiative>initiatives,int projectId) throws SQLException{
           Statement stm = _CONNECTION.createStatement();
           for(int i=0;i<initiatives.size();i++){
           if(initiatives.get(i).getActive()){
           String query = "update initiative set Project_Id="+projectId+" where Id="+initiatives.get(i).getId();
           stm.executeUpdate(query);
           }
           }
       
       }
        
}
