/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employeemanagement;

/**
 *
 * @author Gagandeep Singh
 */

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Gagandeep Singh
 * 
 */
public class database {
    
    public static Connection connectDb(){
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            
            //Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/employee", "root", "");
            
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "");
            return connect;
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
    
}
