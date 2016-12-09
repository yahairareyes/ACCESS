/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawkbyte.report;

import com.hawkbyte.managers.ActivityManager;
import com.hawkbyte.managers.InitiativeManager;
import com.hawkbyte.managers.ProjectManager;
import com.hawkbyte.model.Activity;
import com.hawkbyte.model.Initiative;
import com.hawkbyte.model.Membership;
import com.hawkbyte.model.Project;
import com.hawkbyte.model.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author raizo
 */
public class DetailedReport {
    
     private InitiativeManager _INITIATIVE_MANAGER;
      private ProjectManager _PROJECT_MANAGER;
      private ActivityManager _ACTIVITY_MANAGER;
      
      public DetailedReport() throws SQLException, ClassNotFoundException{
      
       _INITIATIVE_MANAGER = new InitiativeManager();
       _PROJECT_MANAGER = new ProjectManager();
        _ACTIVITY_MANAGER = new ActivityManager();
      }
        
    public int detailedReportPurpose(int initiativeId,int row_index,HSSFSheet sheet) throws SQLException{
    
            List<String> purposes = _INITIATIVE_MANAGER.getInitiativePurposes(initiativeId);
            HSSFRow row = sheet.createRow((short)row_index);
            row.createCell(0).setCellValue("Purpose");
            row_index+=1;
            for(int i=0;i<purposes.size();i++){
                 row = sheet.createRow((short)row_index+i);
                row.createCell(0).setCellValue(purposes.get(i));
                row_index+=i;
            }
        return row_index;
    
    }
    
        
    public int detailedReportWebsite(int resourceId,int row_index,HSSFSheet sheet) throws SQLException{
    
            List<String> websites = _INITIATIVE_MANAGER.getWebsites(resourceId);
            HSSFRow row = sheet.createRow((short)row_index);
            row.createCell(0).setCellValue("Website");
            row_index+=1;
            for(int i=0;i<websites.size();i++){
                 row = sheet.createRow((short)row_index+i);
                row.createCell(0).setCellValue(websites.get(i));
                row_index+=i;
            }
        return row_index;
    
    }
    
        public int detailedReportMembership(int resourceId,int row_index,HSSFSheet sheet) throws SQLException{
    
            List<Membership> membership = _INITIATIVE_MANAGER.getMembership(resourceId);
            HSSFRow row = sheet.createRow((short)row_index);
            row.createCell(0).setCellValue("Membership");
            row_index+=1;
            row = sheet.createRow((short)row_index);
             row.createCell(0).setCellValue("Name");
             row.createCell(0).setCellValue("Email");
             row.createCell(0).setCellValue("Role");
             row_index+=1;
            for(int i=0;i<membership.size();i++){
                Membership member = membership.get(i);
                row = sheet.createRow((short)row_index+i);
                row.createCell(0).setCellValue(member.getName());
                row.createCell(1).setCellValue(member.getEmail());
                row.createCell(2).setCellValue(member.getRole());
                row_index+=i;
            }
        return row_index;
    
    }
    
  public int detailedReportGoal(int projectId,int row_index,HSSFSheet sheet) throws SQLException{
    
            List<String> goals = _PROJECT_MANAGER.getProjectGoals(projectId);
            HSSFRow row = sheet.createRow((short)row_index);
            row.createCell(0).setCellValue("Goal");
            row_index+=1;
            for(int i=0;i<goals.size();i++){
                 row = sheet.createRow((short)row_index+i);
                row.createCell(0).setCellValue(goals.get(i));
                row_index+=i;
            }
        return row_index;
    
    }
    
    public int detailedReportObjetive(int projectId,int row_index,HSSFSheet sheet) throws SQLException{
    
            List<String> objetives = _PROJECT_MANAGER.getObjetives(projectId);
            HSSFRow row = sheet.createRow((short)row_index);
            row.createCell(0).setCellValue("Objetive");
            row_index+=1;
            for(int i=0;i<objetives.size();i++){
                 row = sheet.createRow((short)row_index+i);
                row.createCell(0).setCellValue(objetives.get(i));
                row_index+=i;
            }
        return row_index;
    }
    
       public int detailedReportDocument(int resourceId,int row_index,HSSFSheet sheet) throws SQLException{
    
            Map<String,String> documents = _INITIATIVE_MANAGER.getDocuments(resourceId);
            HSSFRow row = sheet.createRow((short)row_index);
            row.createCell(0).setCellValue("Document");
            row_index+=1;
            row = sheet.createRow((short)row_index);
            row.createCell(0).setCellValue("Type");
            row.createCell(1).setCellValue("Name");
            row_index+=1;
            int i=0;
            for (Map.Entry<String, String> document : documents.entrySet()) {
               row = sheet.createRow((short)row_index+i);
               row.createCell(0).setCellValue(document.getKey());
               row.createCell(0).setCellValue(document.getValue());
               row_index+=i;
               i++;
            }

        return row_index;
    }
        public int detailedReportKeyword(int resourceId,int row_index,HSSFSheet sheet) throws SQLException{
    
            List<String> keywords = _INITIATIVE_MANAGER.getObjetives(resourceId);
            HSSFRow row = sheet.createRow((short)row_index);
            row.createCell(0).setCellValue("Keywords");
            row_index+=1;
            for(int i=0;i<keywords.size();i++){
                 row = sheet.createRow((short)row_index+i);
                row.createCell(0).setCellValue(keywords.get(i));
                row_index+=i;
            }
        return row_index;
    
    }
    
   public int detailedReportAudience(int activityId,int row_index,HSSFSheet sheet) throws SQLException{
   
           
            Map<String,String> audience = _ACTIVITY_MANAGER.getDocuments(activityId);
            HSSFRow row = sheet.createRow((short)row_index);
            row.createCell(0).setCellValue("Target Audience");
            row_index+=1;
            row = sheet.createRow((short)row_index);
            row.createCell(0).setCellValue("Classification");
            row.createCell(1).setCellValue("Description");
            row_index+=1;
            int i=0;
            for (Map.Entry<String, String> aud : audience.entrySet()) {
               row = sheet.createRow((short)row_index+i);
               row.createCell(0).setCellValue(aud.getKey());
               row.createCell(0).setCellValue(aud.getValue());
               row_index+=i;
               i++;
            }
        return row_index;
       
   }     
   
      public int detailedReportCourses(int activityId,int row_index,HSSFSheet sheet) throws SQLException{
   
           
            Map<String,String> courses = _ACTIVITY_MANAGER.getActivityCourses(activityId);
            HSSFRow row = sheet.createRow((short)row_index);
            row.createCell(0).setCellValue("Courses");
            row_index+=1;
            row = sheet.createRow((short)row_index);
            row.createCell(0).setCellValue("Semester");
            row.createCell(1).setCellValue("Name");
            row_index+=1;
            int i=0;
            for (Map.Entry<String, String> course : courses.entrySet()) {
               row = sheet.createRow((short)row_index+i);
               row.createCell(0).setCellValue(course.getKey());
               row.createCell(0).setCellValue(course.getValue());
               row_index+=i;
               i++;
            }
        return row_index;
       
   }
   
    public int detailedReportInitiative(Initiative initiative, int row_index,HSSFSheet sheet) throws SQLException{
    
               row_index = detailedReportResource(initiative,"Initiative",row_index,sheet);
               HSSFRow row = sheet.createRow((short)row_index);
               row.createCell(0).setCellValue("Category");
               row_index++;
               row = sheet.createRow((short)row_index);
               row.createCell(0).setCellValue(initiative.getCategory());
               row_index++;
               row_index = detailedReportKeyword(initiative.getId(),row_index,sheet);
               row_index = detailedReportPurpose(initiative.getId(),row_index,sheet);
               row_index = detailedReportDocument(initiative.getId(),row_index,sheet);
               row_index = detailedReportWebsite(initiative.getId(),row_index,sheet);
               row_index = detailedReportMembership(initiative.getId(),row_index,sheet);
               
              return row_index;
    }
    public File detailedReport(List<Initiative>initiatives) throws Exception{
     try {
            File filename = new File("../Reports/DetailedReport.xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Detailed Report");  
            int row_index = 0;
            
            for(int i=0;i<initiatives.size();i++){
                Initiative initiative = initiatives.get(i);
                List<Project> projects = _INITIATIVE_MANAGER.getInitiativeProjects(initiative.getId());
                List<Activity> activities = _INITIATIVE_MANAGER.getInitiativeActivities(initiative.getId());
               row_index = detailedReportInitiative(initiative,row_index,sheet);
               row_index = detailedReportProject(projects,row_index,sheet);
               row_index = detailedReportActivity(activities,row_index,sheet);
                   
            }
         
            
            FileOutputStream file = new FileOutputStream(filename);
            workbook.write(file);
            file.close();
            return filename;
            

        } catch ( Exception ex) {
            throw new Exception("Error generating summary report",ex);
        }
    
    
    }
        
    public int detailedReportResource(Resource resource,String row_header,int row_index,HSSFSheet sheet){
    
       HSSFRow row = sheet.createRow((short)row_index);
       row.createCell(0).setCellValue(row_header);
       row_index++;
       row = sheet.createRow((short)row_index);
       row.createCell(0).setCellValue("Title");
       row.createCell(1).setCellValue("Description");
       row.createCell(2).setCellValue("Start Date");
       row.createCell(3).setCellValue("End Date");
       row_index++;
       row = sheet.createRow((short)row_index);
       row.createCell(0).setCellValue(resource.getTitle());
       row.createCell(1).setCellValue(resource.getDescription());
       row.createCell(2).setCellValue(resource.getStartdate());
       row.createCell(3).setCellValue(resource.getEnddate());               
        return row_index++;
    }
    
    public int detailedReportProject(List<Project>projects, int row_index,HSSFSheet sheet) throws SQLException{
      
        for(int i=0;i<projects.size();i++){
            Project project = projects.get(i);
            detailedReportResource(project,"Projects realted to Initiative",row_index,sheet);
            if(!project.getPiname().equals("")){
                HSSFRow row = sheet.createRow((short)row_index);
               row.createCell(0).setCellValue("PI Name");
               row_index++;
               row = sheet.createRow((short)row_index);
               row.createCell(0).setCellValue(project.getPiname());
               row_index++;
            }
            
            row_index = detailedReportKeyword(project.getId(),row_index,sheet);
            row_index = detailedReportGoal(project.getId(),row_index,sheet);
            row_index = detailedReportDocument(project.getId(),row_index,sheet);
            row_index = detailedReportWebsite(project.getId(),row_index,sheet);
            row_index = detailedReportMembership(project.getId(),row_index,sheet);
        }
        
        return row_index;
    }
        public int detailedReportActivity(List<Activity>activities, int row_index,HSSFSheet sheet) throws SQLException{
      
        for(int i=0;i<activities.size();i++){
            Activity activity = activities.get(i);
            detailedReportResource(activity,"Activities Related to Initiative",row_index,sheet);
            row_index = detailedReportKeyword(activity.getId(),row_index,sheet);
            row_index = detailedReportAudience(activity.getId(),row_index,sheet);
            row_index = detailedReportCourses(activity.getId(),row_index,sheet);
            row_index = detailedReportMembership(activity.getId(),row_index,sheet);
        }
        
        return row_index;
    }
    
    
}
