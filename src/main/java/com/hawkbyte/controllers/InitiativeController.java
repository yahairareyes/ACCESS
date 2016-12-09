/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawkbyte.controllers;

import com.hawkbyte.managers.InitiativeManager;
import com.hawkbyte.model.Activity;
import com.hawkbyte.model.Membership;
import com.hawkbyte.model.Initiative;
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
public class InitiativeController {
    
     private InitiativeManager _INITIATIVE_MANAGER;
    public InitiativeController() throws SQLException, ClassNotFoundException{
    
     _INITIATIVE_MANAGER = new InitiativeManager();
    }
    
      @RequestMapping(value="/initiative/create/{user}/{level}",method=RequestMethod.GET)
    public String getCreateForm(@PathVariable("user")int user, @PathVariable("level")String level,@ModelAttribute("initiative")Initiative initiative,ModelMap model){
        model.put("initiative",new Initiative());
        model.put("action","/ACCESS/initiative/create/"+user+"/"+level);
        model.put("cancel","/ACCESS/resource/"+user+"/"+level);
        model.put("member",new Membership());
        return "Initiative";  
    }
            @RequestMapping(value="/initiative/create/{user}/{level}",method=RequestMethod.POST)
    public String submitCreateForm(@PathVariable("user")int user, @PathVariable("level")String level,@ModelAttribute("initiative")Initiative initiative,ModelMap model) throws SQLException{
        int initiativeId = _INITIATIVE_MANAGER .createInitiative(user, initiative);
       
        if(initiativeId<0){
            return "Error";
        }else{
            _INITIATIVE_MANAGER.addDocument(initiativeId,initiative.getDocument().getType(),"", initiative.getDocument().getAttachment());
           _INITIATIVE_MANAGER.addKeyword(initiativeId,initiative.getKeyword());
           _INITIATIVE_MANAGER.addInitiativePurpose(initiativeId, initiative.getPurpose());
           _INITIATIVE_MANAGER.addWebsite(initiativeId, initiative.getWebsite());
           _INITIATIVE_MANAGER.addMembership(initiativeId,initiative.getMembership().getId(),initiative.getMembership().getName(),initiative.getMembership().getEmail(),initiative.getMembership().getRole());
        }
        
        return "redirect:/initiative/view/"+user+"/"+level+"/"+initiativeId;
        
    }
    
           @RequestMapping(value="/initiative/view/{user}/{level}/{initiativeId}",method=RequestMethod.GET)
    public String getViewForm(@ModelAttribute("initiative")Initiative initiative,@PathVariable("initiativeId")int initiativeId,@PathVariable("user")int user,@PathVariable("level")String level,ModelMap model) throws SQLException{
        initiative =  _INITIATIVE_MANAGER.getInitiative(initiativeId);
        initiative.setActivities( _INITIATIVE_MANAGER.linkActivities(user, initiativeId));
        
        model.put("initiative", initiative);
        model.put("membership", _INITIATIVE_MANAGER.getMembership(initiativeId));
        model.put("keywords", _INITIATIVE_MANAGER.getKeywords(initiativeId));
        model.put("purposes", _INITIATIVE_MANAGER.getInitiativePurposes(initiativeId));
        model.put("websites", _INITIATIVE_MANAGER.getWebsites(initiativeId));
        model.put("documents", _INITIATIVE_MANAGER.getDocuments(initiativeId));
        model.put("activities", _INITIATIVE_MANAGER.getInitiativeActivities(initiativeId));
        model.put("action","/ACCESS/initiative/edit/"+user+"/"+level+"/"+initiativeId);
        model.put("delete_initiative","/ACCESS/initiative/delete/"+user+"/"+level+"/"+initiativeId);
        model.put("cancel","/ACCESS/resource/"+user+"/"+level);
          model.put("add_activity","/ACCESS/activity/create/"+user+"/"+level);
        model.put("member",new Membership());
        return "Initiative";  
    }
    
               @RequestMapping(value="/initiative/edit/{user}/{level}/{initiativeId}",method=RequestMethod.POST)
    public String getEditForm(@ModelAttribute("initiative")Initiative initiative,@PathVariable("initiativeId")int initiativeId,@PathVariable("user")int user,@PathVariable("level")String level,ModelMap model) throws SQLException{
          
        if(!_INITIATIVE_MANAGER.updateInitiative(initiativeId, initiative)){
            return "Error";
        }
       
       _INITIATIVE_MANAGER.addKeyword(initiativeId,initiative.getKeyword());
       _INITIATIVE_MANAGER.addDocument(initiativeId, initiative.getDocument().getType(),"", initiative.getDocument().getAttachment());
       _INITIATIVE_MANAGER.addWebsite(initiativeId, initiative.getWebsite());
        _INITIATIVE_MANAGER.addInitiativePurpose(initiativeId, level);
       _INITIATIVE_MANAGER.addMembership(initiativeId,initiative.getMembership().getId(),initiative.getMembership().getName(),initiative.getMembership().getEmail(),initiative.getMembership().getRole());
      _INITIATIVE_MANAGER.setActivities(initiative.getActivities(), initiativeId);
        return "redirect:/initiative/view/"+user+"/"+level+"/"+initiativeId;  
    }
    
       @RequestMapping(value="/initiative/delete/{user}/{level}/{initiativeId}",method=RequestMethod.GET)
    public String deleteInitiative(@PathVariable("initiativeId")int initiativeId,@PathVariable("user")int user,@PathVariable("level")String level,ModelMap model) throws SQLException{
      
       
            if(_INITIATIVE_MANAGER.deleteInitiative(user, initiativeId)){
            return "redirect:/resource/"+user+"/"+level;
            }
        return "Error";
    }
    
              @RequestMapping(value="/initiative/delete/{user}/{level}/{initiativeId}/{id}",method=RequestMethod.GET)
    public String deleteMembership(@PathVariable("initiativeId")int initiativeId,@PathVariable("user")int user,@PathVariable("level")String level,@PathVariable("id")int id,ModelMap model) throws SQLException{
      
       
            if(_INITIATIVE_MANAGER.deleteMembership(id,initiativeId)){
            return "redirect:/initiative/view/"+user+"/"+level+"/"+initiativeId;  
            }
        return "Error";
    }
    
             @RequestMapping(value="/initiative/link_activities/{user}/{level}/{initiativeId}",method=RequestMethod.POST)
    public String linkActivities(@ModelAttribute("link_initiatives")List<Activity>activities,@PathVariable("initiativeId")int initiativeId,@PathVariable("user")int user,@PathVariable("level")String level,ModelMap model) throws SQLException{
        _INITIATIVE_MANAGER.setActivities(activities, initiativeId);
        

        return "redirect:/initiative/view/"+user+"/"+level+"/"+initiativeId;  
    }
}
