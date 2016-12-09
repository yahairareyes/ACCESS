/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawkbyte.authentication;

import com.hawkbyte.database.DatabaseConnection;
import com.hawkbyte.database.Properties;
import com.hawkbyte.model.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Reknek
 */



public class Authenticator {
    
    private final String _ACCESS_DB = "localhost:3306/acces";
   // private final String _ACCESS_DB = "www.yahairareyes.com:3306/ACCESS_DB";
    private Properties _PROPERTIES = new Properties();
    private Connection _CONNECTION;
    
    public Authenticator() throws SQLException, ClassNotFoundException{
        DatabaseConnection con = new DatabaseConnection();   
    _CONNECTION = con.createConnection(_ACCESS_DB,_PROPERTIES.getUsername(),_PROPERTIES.getPassword());
    }
    
    public User authenticate(String username, String password) throws SQLException{
        
     String query = "select password,Dean,Chair,Administrator from users where username='"+username+"';";
     Statement stm = _CONNECTION.createStatement();
     ResultSet result = stm.executeQuery(query);
     if(result.next()){
     if(password.equals(result.getString("password"))){
        query = "select Id,Dean,Chair,Administrator from users where username='"+username+"';";
        stm = _CONNECTION.createStatement();
        result = stm.executeQuery(query);
        
        if(result.next()){
            
            User user = new User();
            user.setUserId(result.getInt("Id"));
            if(result.getBoolean("Administrator")){
                user.setAccess_level("administrator");
                return user;
            }
        
               if(result.getBoolean("Chair")){
                user.setAccess_level("chair");
                return user;
            }
               
               if(result.getBoolean("Dean")){
                user.setAccess_level("dean");
                return user;
            }
                user.setAccess_level("faculty");
               return user;
        }
     }
     }
    return null;
    } 
    
}
