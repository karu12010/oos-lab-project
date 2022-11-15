/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ooslab.mms.db;

import com.ooslab.mms.logic.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author cliford
 */
public class DbAccess {
    public static void connect() throws ClassNotFoundException, SQLException{
        Class.forName("org.sqlite.JDBC");
        Connection conn = getConnection();
        Statement statement = conn.createStatement();

        //create all tables if not exist
        statement.execute("CREATE TABLE IF NOT EXISTS mess_admins(password TEXT)");
        statement.execute("CREATE TABLE IF NOT EXISTS students(rollno TEXT PRIMARY KEY, name TEXT)");
        statement.execute("CREATE TABLE IF NOT EXISTS products(p_id INTEGER PRIMARY KEY, p_name TEXT UNIQUE, price INTEGER)");
        statement.execute("CREATE TABLE IF NOT EXISTS transactions(t_id INTEGER PRIMARY KEY, rollno TEXT, p_id INTEGER, quantity INTEGER,"
                + "FOREIGN KEY(rollno) REFERENCES students(rollno), FOREIGN KEY(p_id) REFERENCES products(p_id))");
        
        ResultSet rs = statement.executeQuery("SELECT * FROM mess_admins");
        
        if(!rs.next()){
            // no admins present
            statement.executeUpdate("INSERT INTO mess_admins VALUES(\'admin\')");
            System.out.println("admin created");
        }
        
        rs.close();
        
        //get all the products into memory
        rs = statement.executeQuery("SELECT * FROM products");
        while(rs.next()){
            Product p = new Product();
            p.p_id = rs.getInt("p_id");
            p.p_name = rs.getString("p_name");
            p.price = rs.getInt("price");
            Product.products.add(p);
        }
        rs.close();
        
        statement.close();
        conn.close();
    }
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:sqlite:main.db");
    }
}