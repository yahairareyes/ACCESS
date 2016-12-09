/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawkbyte.dataTable;

import com.hawkbyte.managers.ActivityManager;
import com.hawkbyte.managers.InitiativeManager;
import com.hawkbyte.managers.ProjectManager;
import com.hawkbyte.model.Activity;
import com.hawkbyte.model.Initiative;
import com.hawkbyte.model.Membership;
import com.hawkbyte.model.Project;
import com.hawkbyte.model.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author raizo
 */
public class ResourceLoader {
    
      private ProjectManager _PROJECT_MANAGER;
    private InitiativeManager _INITIATIVE_MANAGER;
    private ActivityManager _ACTIVITY_MANAGER;
    
    public ResourceLoader() throws SQLException, ClassNotFoundException{
    
        _PROJECT_MANAGER = new ProjectManager();
        _INITIATIVE_MANAGER = new InitiativeManager();
        _ACTIVITY_MANAGER = new ActivityManager();
    }
    
    public List<Resource> loadResources(int userId,String access_level) throws SQLException{
    
        List<Resource> resources = new ArrayList<Resource>();
        
     resources.addAll(_PROJECT_MANAGER.getProjects(userId));
     
     if(access_level.equals("administrator")){
         resources.addAll(_PROJECT_MANAGER.getDepartmentProjects());
        resources.addAll(_PROJECT_MANAGER.getCollegeProjects());
        resources.addAll(_PROJECT_MANAGER.getUniversityProjects());
        resources.addAll(_INITIATIVE_MANAGER.getDepartmentInitiatives());
        resources.addAll(_INITIATIVE_MANAGER.getCollegeInitiatives());
        resources.addAll(_INITIATIVE_MANAGER.getUniversityInitiatives());
     }else if(access_level.equals("chair")){
         resources.addAll(_PROJECT_MANAGER.getDepartmentProjects());
        resources.addAll(_PROJECT_MANAGER.getCollegeProjects());
         resources.addAll(_INITIATIVE_MANAGER.getDepartmentInitiatives());
        resources.addAll(_INITIATIVE_MANAGER.getCollegeInitiatives());
         
     }else if(access_level.equals("dean")){
         resources.addAll(_PROJECT_MANAGER.getDepartmentProjects());
         resources.addAll(_INITIATIVE_MANAGER.getDepartmentInitiatives());
     }
     
     resources.addAll(_PROJECT_MANAGER.getProjects(userId));
     resources.addAll(_INITIATIVE_MANAGER.getInitiatives(userId));
     resources.addAll(_ACTIVITY_MANAGER.getActivities(userId));
     return resources;
    }
          
    
}
