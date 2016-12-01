/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawkbyte.database;

/**
 *
 * @author Reknek
 */

import java.sql.*;
public class DatabaseConnection {
    
    public Connection createConnection(String database_url, String username, String password) throws SQLException, ClassNotFoundException{
    
    try{
      String url = "jdbc:mysql://"+database_url;
      Class.forName("com.mysql.jdbc.Driver");  
     Connection con=DriverManager.getConnection(url,username,password);  

     
    return con;

}catch(SQLException e){ 
    throw new SQLException(e);
}
        
    }
    
}
