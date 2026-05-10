/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.placementsync.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    
    private static Connection connection=null;
    private static final String URl="jdbc:mysql://localhost:3306/placementSync";
    private static String USER="root";
    private static String PASSWORD="atharv30";
    
    private DBConnection(){
        
    }
    
    public static Connection getConnection()throws SQLException{
        
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                connection=DriverManager.getConnection(URl,USER,PASSWORD);
                System.out.println("DAtabase connected successfully");
                return connection;
            }
            catch(ClassNotFoundException e){
                System.out.println("MYSQl Driver not found!check your pom.xml");
                e.printStackTrace();
            }catch(SQLException e){
                System.out.println("failed to connect to database");
                e.printStackTrace();
            }
        
     return connection;  
    }
    
}
