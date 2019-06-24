/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author ICTSFRAAS
 */
public class DataConnect 
{
   
    public static Connection getConnection() {

         return connectToLocalDB();

    }
 
    
    public static Connection  connectToLocalDB() 
    {
        String connection_name = "jdbc:mysql://localhost:3306/pizza"; 
        String connection_user = "root";
        String connextion_password = "root";
    
        try  
        { 
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                   connection_name , connection_user, connextion_password);
            
           // System.out.println("connectToLocalDB --> OK ");
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("connectToLocalDB --> Error "
                    + ex.getMessage());
            return null;
        }
    }

    
    
    public static void close(Connection con) {
        try {
                //System.out.println("connect to DB close --> OK ");
                con.close();
            } catch (Exception ex) 
            {
                 System.out.println("connect to DB close --> Error "+ex);
            }
    }
    
    
    
    
    
    
}
