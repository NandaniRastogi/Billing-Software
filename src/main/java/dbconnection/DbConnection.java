/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbconnection;

import admin.AdminPanel;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import gettersetter.GetterSetter;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author nanda
 */
public class  DbConnection {
    
    
  public static Connection  con= null;
    public static  Connection createDbConnection(){
        
        try{
            
            
       System.out.println("I am in the method of createDbConnection");
         Properties p = new Properties();
          InputStream is = new FileInputStream("C:\\Users\\nanda\\Documents\\NetBeansProjects\\billingSoftware\\src\\main\\java\\dbconnection\\info.properties");

            if (is == null) {
                System.out.println("File not found ❌");
                return null;
            }

            p.load(is);
            String driverClass= p.getProperty("driverClass");
            String url = p.getProperty("url");
            String username = p.getProperty("username");
            String password = p.getProperty("password");
            
            
       Class.forName(driverClass);
         con = DriverManager.getConnection(url, username, password);
        
        }catch(Exception e){
            
            System.out.println(e);
        
        }
    
        return con;
    
    
    }
    
    
     public static int  insertValuesInDatabase(GetterSetter gs) {
          int i=0;
        try {
            Connection con = DbConnection.createDbConnection();
            PreparedStatement ps = con.prepareStatement("insert into register values(?,?,?,?,?,?)");
            ps.setString(1, gs.getName());
            ps.setString(2, gs.getEmail());
            ps.setString(3, gs.getPassword());
             ps.setString(4, gs.getGender());
            ps.setString(5, gs.getPhone());
            ps.setString(6, "employee");
            
          i = ps.executeUpdate();
        
        }catch(Exception e){
            System.out.println("My Exception");
        }
  
        return i;

    }
     
     
     public static ResultSet getItemDetails(String id){
         ResultSet rs = null;
        Connection con =  createDbConnection();
        try{
          PreparedStatement      ps=     con.prepareStatement("select * from items_details where id = ?");
          ps.setString(1,id);
         rs = ps.executeQuery();
        }catch(Exception e){
            System.out.println(e);
        }
        return rs;
     }
     
     public static void saveToDatabase(String phone, String name, String email, String gender, String payment) {

    try {
        Connection con = dbconnection.DbConnection.createDbConnection();

        String query = "INSERT INTO customer_details (phoneNo, name, email, gender, payment) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,phone );
        ps.setString(2, name);
        ps.setString(3, email);
        ps.setString(4, gender);
        ps.setString(5, payment);

        int i = ps.executeUpdate();

        if(i> 0){
            System.out.println("Succesfully Updated");
        }
        else{
            System.out.println("Database Does Not Updated");
        }


    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
}
