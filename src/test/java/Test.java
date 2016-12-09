/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Reknek
 */
import com.hawkbyte.database.DatabaseConnection;
import com.hawkbyte.database.Properties;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Test {
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
    
        Properties prop = new Properties();
        DatabaseConnection con = new DatabaseConnection();
        Connection con1 = con.createConnection("localhost:3306/access","root","");
        
                 
          DatabaseMetaData md = con1.getMetaData();
ResultSet rs = md.getTables(null, null, "%", null);
while (rs.next()) {
  System.out.println(rs.getString(3));
}
    }
           
    

    
}
