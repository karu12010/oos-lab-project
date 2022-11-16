/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ooslab.mms.logic;

import com.ooslab.mms.db.DbAccess;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cliford
 */
public class Student{

    String rollNo;
    String name;
    
    
    public float calculateDues(){
        return 55.3f;
    }
    
    public static Student fetchStudent(String rollNo){
        //do stuff
        return null;
    }
    
    public static boolean isEmpty(){
        try {
            Connection conn = DbAccess.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM students");
            
            boolean empty = !rs.next();
            
            rs.close();
            conn.close();
          
            return empty;
        } catch (SQLException ex) {
            return true;
        }
        
    }
    
    public static boolean addStudents(File csvFile){
        try {
            Scanner sc = new Scanner(csvFile);
            Connection conn = DbAccess.getConnection();
            Statement stmt = conn.createStatement();
            while(sc.hasNext()){
                String line = sc.nextLine();
                String[] fields = line.split(",");
                stmt.executeUpdate(String.format("INSERT INTO students VALUES('%s','%s')", fields[0], fields[1]));
            }
            conn.close();
            return true;
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}
