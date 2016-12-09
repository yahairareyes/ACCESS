/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawkbyte.controllers;

import com.hawkbyte.managers.ProjectManager;
import com.hawkbyte.model.Initiative;
import com.hawkbyte.model.Membership;
import com.hawkbyte.model.Project;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProjectController {
    
     private ProjectManager _PROJECT_MANAGER;
    public ProjectController() throws SQLException, ClassNotFoundException{
    
     _PROJECT_MANAGER = new ProjectManager();
    }
    
    @RequestMapping(value="/project/create/{user}/{level}",method=RequestMethod.GET)
    public String getCreateForm(@PathVariable("user")int user, @PathVariable("level")String level,@ModelAttribute("project")Project project,ModelMap model){
        model.put("project",new Project());
        model.put("action","/ACCESS/project/create/"+user+"/"+level);
        model.put("cancel","/ACCESS/resource/"+user+"/"+level);
        model.put("add_initiative","/ACCESS/initiative/create/"+user+"/"+level);
        model.put("member",new Membership());
        return "Project";  
    }
    
        @RequestMapping(value="/project/create/{user}/{level}",method=RequestMethod.POST)
    public String submitCreateForm(@PathVariable("user")int user, @PathVariable("level")String level,@ModelAttribute("project")Project project,ModelMap model) throws SQLException{
        int projectId = _PROJECT_MANAGER.createProject(user, project);
       
        if(projectId<0){
            return "Error";
        }else{
            _PROJECT_MANAGER.addDocument(projectId, project.getDocument().getType(),"", project.getDocument().getAttachment());
            _PROJECT_MANAGER.addKeyword(projectId,project.getKeyword());
            _PROJECT_MANAGER.addObjetive(projectId, project.getObjetive());
            _PROJECT_MANAGER.addWebsite(projectId, project.getWebsite());
            _PROJECT_MANAGER.addMembership(projectId,project.getMembership().getId(),project.getMembership().getName(),project.getMembership().getEmail(),project.getMembership().getRole());
        }
        
        return "redirect:/project/view/"+user+"/"+level+"/"+projectId;
        
    }
    
       @RequestMapping(value="/project/view/{user}/{level}/{projectId}",method=RequestMethod.GET)
    public String getViewForm(@ModelAttribute("project")Project project,@PathVariable("projectId")int projectId,@PathVariable("user")int user,@PathVariable("level")String level,ModelMap model) throws SQLException{
        project = _PROJECT_MANAGER.getProject(projectId);
        project.setInitiatives(_PROJECT_MANAGER.linkInitiative(user, projectId));
        
        model.put("project", project);
        model.put("membership",_PROJECT_MANAGER.getMembership(projectId));
        model.put("funded_projects",_PROJECT_MANAGER.getFundedProjects(user));
        model.put("keywords",_PROJECT_MANAGER.getKeywords(projectId));
        model.put("goals",_PROJECT_MANAGER.getProjectGoals(projectId));
        model.put("websites",_PROJECT_MANAGER.getWebsites(projectId));
        model.put("documents",_PROJECT_MANAGER.getDocuments(projectId));
        model.put("initiatives",_PROJECT_MANAGER.getProjectInitiatives(projectId));
        model.put("action","/ACCESS/project/edit/"+user+"/"+level+"/"+projectId);
        model.put("delete_project","/ACCESS/project/delete/"+user+"/"+level+"/"+projectId);
        model.put("cancel","/ACCESS/resource/"+user+"/"+level);
          model.put("add_initiative","/ACCESS/initiative/create/"+user+"/"+level);
        model.put("member",new Membership());
        return "Project";  
    }
    
           @RequestMapping(value="/project/edit/{user}/{level}/{projectId}",method=RequestMethod.POST)
    public String getEditForm(@ModelAttribute("project")Project project,@PathVariable("projectId")int projectId,@PathVariable("user")int user,@PathVariable("level")String level,ModelMap model) throws SQLException{
        System.out.println(project.getInitiatives().get(0).getActive());
        
        if(!_PROJECT_MANAGER.updateProject(projectId, project)){
            return "Error";
        }
       
        _PROJECT_MANAGER.addKeyword(projectId,project.getKeyword());
        _PROJECT_MANAGER.addDocument(projectId, project.getDocument().getType(),"", project.getDocument().getAttachment());
        _PROJECT_MANAGER.addObjetive(projectId, project.getObjetive());
        _PROJECT_MANAGER.addWebsite(projectId, project.getWebsite());
         _PROJECT_MANAGER.addProjectGoal(projectId, project.getGoal());
           _PROJECT_MANAGER.setInitiatives(project.getInitiatives(), projectId);
       _PROJECT_MANAGER.addMembership(projectId,project.getMembership().getId(),project.getMembership().getName(),project.getMembership().getEmail(),project.getMembership().getRole());
       
        return "redirect:/project/view/"+user+"/"+level+"/"+projectId;  
    }
    
      @RequestMapping(value="/project/delete/{user}/{level}/{projectId}",method=RequestMethod.GET)
    public String deleteProject(@PathVariable("projectId")int projectId,@PathVariable("user")int user,@PathVariable("level")String level,ModelMap model) throws SQLException{
      
       
            if(_PROJECT_MANAGER.deleteProject(user, projectId)){
            return "redirect:/resource/"+user+"/"+level;
            }
        return "Error";
    }
    
          @RequestMapping(value="/project/delete/{user}/{level}/{projectId}/{id}",method=RequestMethod.GET)
    public String deleteMembership(@PathVariable("projectId")int projectId,@PathVariable("user")int user,@PathVariable("level")String level,@PathVariable("id")int id,ModelMap model) throws SQLException{
      
       
            if(_PROJECT_MANAGER.deleteMembership(id,projectId)){
            return "redirect:/project/view/"+user+"/"+level+"/"+projectId;  
            }
        return "Error";
    }
          @RequestMapping(value="/project/link_initiatives/{user}/{level}/{projectId}",method=RequestMethod.POST)
    public String linkInitiatives(@ModelAttribute("link_initiatives")List<Initiative>initiatives,@PathVariable("projectId")int projectId,@PathVariable("user")int user,@PathVariable("level")String level,ModelMap model) throws SQLException{
        _PROJECT_MANAGER.setInitiatives(initiatives, projectId);
        

        return "redirect:/project/view/"+user+"/"+level+"/"+projectId;  
    }

}
