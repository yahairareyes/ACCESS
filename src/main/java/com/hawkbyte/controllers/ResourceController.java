/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawkbyte.controllers;

import com.hawkbyte.model.Resource;
import com.hawkbyte.dataLoader.ResourceLoader;
import com.hawkbyte.dataTable.ResourceDataTable;
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
        ResourceDataTable table = new ResourceDataTable();
        table.setResource(resources);
        model.put("table",table);
       
        
        return "Resources";
    }
}
