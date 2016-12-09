/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawkbyte.controllers;

import com.hawkbyte.managers.InitiativeManager;
import com.hawkbyte.model.Directory;
import com.hawkbyte.report.SummaryReport;
import com.hawkbyte.report.DetailedReport;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DirectoryController {
    
        private InitiativeManager _INITIATIVE_MANAGER;
        private SummaryReport _SUMMARY_REPORT;
        private DetailedReport _DETAILED_REPORT;
        
    public DirectoryController() throws SQLException, ClassNotFoundException{
    
     _INITIATIVE_MANAGER = new InitiativeManager();
     _SUMMARY_REPORT = new SummaryReport();
     _DETAILED_REPORT = new DetailedReport();
    }
    
    @RequestMapping(value="/initiative/directory/{report}/{user}/{level}",method=RequestMethod.POST)
    public String getDirectory(@PathVariable("report")String report,@PathVariable("user")int user, @PathVariable("level")String level,@ModelAttribute("directory")Directory directory,ModelMap model) throws SQLException, Exception{
        
        
      
          model.put("sumary_report","/ACCESS/initiative/directory/summary/"+user+"/"+level);
            model.put("detailed_report","/ACCESS/initiative/directory/detailed/"+user+"/"+level);
            
        if(!directory.getCategory().equals("")){
            directory.setInitiatives(_INITIATIVE_MANAGER.viewByCategory(user, directory.getCategory()));
            model.put("directory",directory);
            return "Directory";  
            
        }
        
          if(!directory.getCollege().equals("")){
            directory.setInitiatives(_INITIATIVE_MANAGER.viewByCollege(user, directory.getCollege()));
             model.put("directory",directory);
            return "Directory";  
        }
        
           if(!directory.getDepartment().equals("")){
            directory.setInitiatives(_INITIATIVE_MANAGER.viewByDepartment(user, directory.getDepartment()));
             model.put("directory",directory);
            return "Directory";  
        }
          directory.setInitiatives(_INITIATIVE_MANAGER.getInitiatives(user));
           model.put("directory",directory);
        return "Directory";  
    }
    

     @RequestMapping(value="/initiative/directory/{report}/{user}/{level}",method=RequestMethod.GET)
     ModelAndView defaultDirectory(HttpServletResponse response,@PathVariable("report")String report,@PathVariable("user")int user, @PathVariable("level")String level,@ModelAttribute("directory")Directory directory,ModelMap model) throws SQLException, Exception{
         ModelAndView modelAndView = new ModelAndView();
         if(!report.equals("none")){
          
            if(report.equals("summary")){
                 modelAndView = new ModelAndView("excelView", "initiatives",directory.getInitiatives());
                 

            }else if(report.equals("detailed")){
                 //file = _DETAILED_REPORT.detailedReport(directory.getInitiatives());
            }
       

        }
        
        
          directory.setInitiatives(_INITIATIVE_MANAGER.getInitiatives(user));
          model.put("directory",directory);
           model.put("sumary_report","/ACCESS/initiative/directory/summary/"+user+"/"+level);
            model.put("detailed_report","/ACCESS/initiative/directory/detailed/"+user+"/"+level);
        
        
       
        return modelAndView;
    }
    

    
    
}
