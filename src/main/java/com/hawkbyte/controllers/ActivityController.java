/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawkbyte.controllers;

import com.hawkbyte.managers.ActivityManager;
import com.hawkbyte.model.Activity;
import com.hawkbyte.model.Membership;
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
public class ActivityController {
         private ActivityManager _ACTIVITY_MANAGER;
    public ActivityController() throws SQLException, ClassNotFoundException{
    
    _ACTIVITY_MANAGER= new ActivityManager();
    }
    
         @RequestMapping(value="/activity/create/{user}/{level}",method=RequestMethod.GET)
    public String getCreateForm(@PathVariable("user")int user, @PathVariable("level")String level,@ModelAttribute("activity")Activity activity,ModelMap model){
        model.put("activity",new Activity());
        model.put("action","/ACCESS/activity/create/"+user+"/"+level);
        model.put("cancel","/ACCESS/resource/"+user+"/"+level);
        
        model.put("member",new Membership());
        return "Activity";  
    }
   
                @RequestMapping(value="/activity/create/{user}/{level}",method=RequestMethod.POST)
    public String submitCreateForm(@PathVariable("user")int user, @PathVariable("level")String level,@ModelAttribute("activity")Activity activity,ModelMap model) throws SQLException{
        int activityId =  _ACTIVITY_MANAGER.createActivity(user, activity);
       
        if(activityId<0){
            return "Error";
        }else{
           _ACTIVITY_MANAGER.addKeyword(activityId,activity.getKeyword());
            _ACTIVITY_MANAGER.addActivityCourse(activityId, activity.getCourse().getSemester(),activity.getCourse().getCrn());
              _ACTIVITY_MANAGER.addActivityAudience(activityId,activity.getAudience().getClasification(),activity.getAudience().getDescription());
           _ACTIVITY_MANAGER.addMembership(activityId,activity.getParticipant().getId(),activity.getParticipant().getName(),activity.getParticipant().getEmail(),activity.getParticipant().getRole());
        }
        
        return "redirect:/activity/view/"+user+"/"+level+"/"+activityId;
        
    }
    
               @RequestMapping(value="/activity/view/{user}/{level}/{activityId}",method=RequestMethod.GET)
    public String getViewForm(@ModelAttribute("activity")Activity activity,@PathVariable("activityId")int activityId,@PathVariable("user")int user,@PathVariable("level")String level,ModelMap model) throws SQLException{
        activity = _ACTIVITY_MANAGER.getActivity(activityId);
       
        
        model.put("activity", activity);
        model.put("participant", _ACTIVITY_MANAGER.getMembership(activityId));
        model.put("keywords", _ACTIVITY_MANAGER.getKeywords(activityId));
        model.put("audience",  _ACTIVITY_MANAGER.getActivityAudience(activityId));
        model.put("courses",_ACTIVITY_MANAGER.getActivityCourses(activityId));
        model.put("action","/ACCESS/activity/edit/"+user+"/"+level+"/"+activityId);
        model.put("delete_activity","/ACCESS/activity/delete/"+user+"/"+level+"/"+activityId);
        model.put("cancel","/ACCESS/resource/"+user+"/"+level);
        model.put("member",new Membership());
        return "Activity";  
    }
    
                   @RequestMapping(value="/activity/edit/{user}/{level}/{activityId}",method=RequestMethod.POST)
    public String getEditForm(@ModelAttribute("activity")Activity activity,@PathVariable("activityId")int activityId,@PathVariable("user")int user,@PathVariable("level")String level,ModelMap model) throws SQLException{
        
        
        if(!_ACTIVITY_MANAGER.updateActivity(activityId, activity)){
            return "Error";
        }
         _ACTIVITY_MANAGER.addKeyword(activityId,activity.getKeyword());
            _ACTIVITY_MANAGER.addActivityCourse(activityId, activity.getCourse().getSemester(),activity.getCourse().getCrn());
              _ACTIVITY_MANAGER.addActivityAudience(activityId,activity.getAudience().getClasification(),activity.getAudience().getDescription());
           _ACTIVITY_MANAGER.addMembership(activityId,activity.getParticipant().getId(),activity.getParticipant().getName(),activity.getParticipant().getEmail(),activity.getParticipant().getRole());
     
        return "redirect:/activity/view/"+user+"/"+level+"/"+activityId;  
    }
    
          @RequestMapping(value="/activity/delete/{user}/{level}/{activityId}",method=RequestMethod.GET)
    public String deleteActivity(@PathVariable("activityId")int activityId,@PathVariable("user")int user,@PathVariable("level")String level,ModelMap model) throws SQLException{
      
       
            if( _ACTIVITY_MANAGER.deleteActivity(user, activityId)){
            return "redirect:/resource/"+user+"/"+level;
            }
        return "Error";
    }
    
                  @RequestMapping(value="/activity/delete/{user}/{level}/{activityId}/{id}",method=RequestMethod.GET)
    public String deleteMembership(@PathVariable("activityId")int activityId,@PathVariable("user")int user,@PathVariable("level")String level,@PathVariable("id")int id,ModelMap model) throws SQLException{
      
       
            if(_ACTIVITY_MANAGER.deleteMembership(id,activityId)){
            return "redirect:/activity/view/"+user+"/"+level+"/"+activityId;  
            }
        return "Error";
    }
}
