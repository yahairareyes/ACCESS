/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawkbyte.controllers;

import com.hawkbyte.model.Credentials;
import com.hawkbyte.authentication.Authenticator;
import com.hawkbyte.model.User;
import java.sql.SQLException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Reknek
 */
@Controller
public class AuthenticatorController {
    
    
      @RequestMapping(value="/", method={RequestMethod.GET,RequestMethod.POST})
    public String defaultLogin(@ModelAttribute("credentials")Credentials credentials){
    
        
        return "login";
    }
    
      @RequestMapping(value="/login", method={RequestMethod.POST,RequestMethod.GET})
    public String validateLogin(@ModelAttribute("credentials")Credentials credentials,ModelMap model) throws SQLException, ClassNotFoundException{
    
        Authenticator authenticator = new Authenticator();
        User user = authenticator.authenticate(credentials.getUsername(), credentials.getPassword());
        if(user !=null){
            model.put("user", user.getUserId());
            model.put("level", user.getAccess_level());
            return "redirect:/resource/{user}/{level}";
            
        }
        
        return "login";
    }
    
}
