/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawkbyte.managers;

import com.hawkbyte.database.DatabaseConnection;
import com.hawkbyte.database.Properties;
import com.hawkbyte.model.Membership;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Reknek
 */
public class ResourceManager {
    
    protected final String _ACCESS_DB = "localhost:3306/access";
    //protected final String _ACCESS_DB = "//www.yahairareyes.com:3306/ACCESS_DB";
    protected Properties _PROPERTIES = new Properties();
    protected Connection _CONNECTION;
    
    public ResourceManager() throws SQLException, ClassNotFoundException{
    
        DatabaseConnection con = new DatabaseConnection();   
    _CONNECTION = con.createConnection(_ACCESS_DB, _PROPERTIES.getUsername(), _PROPERTIES.getPassword());
    }
    
           public boolean addKeyword(int resourceId,String keyword) throws SQLException{
       
               if(!keyword.equals("")){
               
           String query = "insert into keyword (Resource_Id,Keyword) values("+resourceId+",'"+keyword+"')";
           Statement stm = _CONNECTION.createStatement();
          
           
           if(stm.executeUpdate(query) > 0){
               return true;
           }
               
           stm.close();
               }
           return false;
       }
        
        public boolean addWebsite(int resourceId,String website) throws SQLException{
        if(!website.equals("")){
               
           String query = "insert into website (Resource_Id,Website) values("+resourceId+",'"+website+"')";
           Statement stm = _CONNECTION.createStatement();
          
           
           if(stm.executeUpdate(query) > 0){
               return true;
           }
            stm.close();
        }
           return false;
       }   
           
         public boolean addDocument(int resourceId,String type,String location,String name) throws SQLException{
       
              if(!type.equals("") && !name.equals("")){
               
           String query = "insert into document (Resource_Id,Type,Name,Location) values("+resourceId+",'"+type+"','"+name+"','"+location+"')";
           Statement stm = _CONNECTION.createStatement();
          
           
           if(stm.executeUpdate(query) > 0){
               return true;
           }
            stm.close();
              }
           return false;
       }
         
        public boolean addMembership(int resourceId,int id,String name,String email,String role) throws SQLException{
       
            if(id > 0 && !name.equals("") && !email.equals("") && !role.equals("")){
            
           String query = "insert into membership (Id,Resource_Id,Name,Email,Role) values("+id+","+resourceId+",'"+name+"','"+email+"','"+role+"')";
           Statement stm = _CONNECTION.createStatement();
          
           
           if(stm.executeUpdate(query) > 0){
               return true;
           }
            stm.close();
            }
           return false;
       } 
        
         public boolean addObjetive(int resourceId,String objetive) throws SQLException{
       if(!objetive.equals("")){
           String query = "insert into objetive (Resource_Id,Objetive) values("+resourceId+",'"+objetive+"')";
           Statement stm = _CONNECTION.createStatement();
          
           
           if(stm.executeUpdate(query) > 0){
               return true;
           }
            stm.close();
       }
           return false;
       }
         
 
             public List<String> getObjetives(int resourceId) throws SQLException{
        
         String query = "Select Objetive from objetive where Resource_Id = "+resourceId;
        Statement stm = _CONNECTION.createStatement();
        ResultSet result = stm.executeQuery(query);
        if(result != null){
         List<String> objetives = new ArrayList<String>();
        while(result.next()){
            objetives.add(result.getString("Objetive"));
            
        }
         
            return objetives;
        }
         stm.close();
        return null;
    }
     
                      public List<String> getWebsites(int resourceId) throws SQLException{
        
         String query = "Select Website from website where Resource_Id = "+resourceId;
        Statement stm = _CONNECTION.createStatement();
        ResultSet result = stm.executeQuery(query);
        List<String> websites = new ArrayList<String>();
        if(result != null){
         
        while(result.next()){
            websites.add(result.getString("Website"));
            
        }
         
            
        }
         stm.close();
        return websites;
    }    
             
       public List<String> getKeywords(int resourceId) throws SQLException{
        
         String query = "Select Keyword from keyword where Resource_Id = "+resourceId;
        Statement stm = _CONNECTION.createStatement();
        ResultSet result = stm.executeQuery(query);
          List<String> keywords = new ArrayList<String>();
        if(result != null){
       
        while(result.next()){
            keywords.add(result.getString("Keyword"));
            
        }
           
            
        }
         stm.close();
        return keywords;
    }         
             

     
       public Map<String,String> getDocuments(int resourceId) throws SQLException{
        
         String query = "Select Type,Name from document where Resource_Id = "+resourceId;
        Statement stm = _CONNECTION.createStatement();
        ResultSet result = stm.executeQuery(query);
         Map<String,String> documents = new HashMap<String,String>();
        if(result != null){
        
        while(result.next()){
            documents.put(result.getString("Type"),result.getString("Name"));
            
        }
            
            
        }
         stm.close();
       return documents;
    }
       
          public List<Membership> getMembership(int resourceId) throws SQLException{
        
         String query = "Select * from membership where Resource_Id = "+resourceId;
        Statement stm = _CONNECTION.createStatement();
        ResultSet result = stm.executeQuery(query);
        List<Membership> membership = new ArrayList<Membership>();
       
         
        while(result.next()){
            Membership member = new Membership();
            member.setId(result.getInt("Id"));
            member.setName(result.getString("Name"));
            member.setEmail(result.getString("Email"));
            member.setRole(result.getString("Role"));
            membership.add(member);
            
        
          
           
        }
             stm.close();
           return membership;
    }  
       public boolean deleteMembership(int id,int resourceId) throws SQLException{
            String query = "delete from membership where Resource_Id = "+resourceId+" and Id="+id;
        Statement stm = _CONNECTION.createStatement();
       if(stm.executeUpdate(query) > 0){
       
           return true;
        }
       return false;
       }  
     
         
}
