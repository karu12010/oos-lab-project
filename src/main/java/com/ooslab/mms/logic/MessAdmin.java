/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ooslab.mms.logic;

import com.ooslab.mms.db.DbAccess;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author cliford
 */
public class MessAdmin {
    private String pwd;
    private static MessAdmin current;
    
    public boolean changePwd(String newPwd){
        boolean success = false;
        try {
            Connection conn = DbAccess.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(String.format("UPDATE mess_admins SET password='%s' WHERE password='%s'", newPwd, this.pwd));
            success = true;
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return success;
    }
    
    public boolean addProduct(String p_name, int price){
        return !p_name.equals("") && Product.addProduct(this, p_name, price) != null;
    }
    
    public Transaction addTransaction(String rollno, int p_id, int quantity){
        return Transaction.makeTransaction(this, rollno, p_id, quantity);
    }
    
    public boolean verifyStudent(String rollNo){
        return Student.fetchStudent(rollNo) != null;
    }
    
    public static MessAdmin login(){
        return current;
    }
    
    public static boolean verifyLogin(String pass){
        boolean success = false;
        try {
            Connection conn = DbAccess.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mess_admins WHERE password=\'"+pass+"\'");
            success = rs.next();
            rs.close();
            conn.close();
            stmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        if(success){
            current = new MessAdmin();
            current.pwd = pass;
        }
        return success;
    }
    
    public boolean clearStudentData(){
        try{
            Connection conn = DbAccess.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM students");
            stmt.close();
            conn.close();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        
    }
}
