/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawkbyte.controllers;

import com.hawkbyte.model.Resource;
import com.hawkbyte.dataTable.ResourceLoader;
import com.hawkbyte.dataTable.Data;
import com.hawkbyte.dataTable.Driver;
import com.hawkbyte.model.Project;
import com.hawkbyte.model.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Reknek
 */

@Controller
public class ResourceController {
      
   
    @RequestMapping(value="/resource/{user}/{level}", method={RequestMethod.POST,RequestMethod.GET})
    public String defaultResource(@PathVariable("user")int user, @PathVariable("level")String level,ModelMap model) throws SQLException, ClassNotFoundException{
    
        ResourceLoader loader = new ResourceLoader();
        List<Resource> resources = loader.loadResources(user,level);
        Data table = new Data();
        table.setResource(resources);
        model.put("table",table);
       
        
        return "Resources";
    }

    @RequestMapping(value="/resource", method=RequestMethod.GET)
    public String defaultResource(ModelMap model) throws SQLException, ClassNotFoundException{
    
        ResourceLoader loader = new ResourceLoader();
        //List<Resource> resources = loader.loadResources(user,level);
        Driver driver = new Driver();
        
        Data table = new Data();
        //table.setResource(resources);
        
        table.setResource(driver.getResources());
        model.put("table",table);
       
        
        return "Resources";
    }
    
@RequestMapping(value="/resource/view/{user}/{level}/{resourceType}/{resourceId}", method=RequestMethod.GET)
 public String viewResource(@PathVariable("user")int user,@PathVariable("level")String level,@PathVariable("resourceType")String resourceType,@PathVariable("resourceId")int resourceId,ModelMap model) throws SQLException, ClassNotFoundException{
        String url = "Error";
        if(resourceType.equals("Project")){
            url =  "redirect:/project/view/"+user+"/"+level+"/"+resourceId;
        }else if(resourceType.equals("Initiative")){
             url =  "redirect:/initiative/view/"+user+"/"+level+"/"+resourceId;
        }else if(resourceType.equals("Activity")){
            url = "redirect:/activity/view/"+user+"/"+level+"/"+resourceId;
        
        }
     
       
       
        
        return url;
    }
    


    
}
