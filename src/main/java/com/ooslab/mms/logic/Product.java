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
import java.util.ArrayList;

/**
 *
 * @author cliford
 */
public class Product implements CommitDB{
    public int p_id;
    public String p_name;
    public int price;
    public static ArrayList<Product> products;
    
    static {
        products = new ArrayList<>();
    }

    @Override
    public int addToDb() {
        int id = -1;
        try {
            Connection conn = DbAccess.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(
                    String.format("INSERT INTO products (p_name, price) VALUES ('%s', %d)", p_name, price), 
                    Statement.RETURN_GENERATED_KEYS
            );
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                id =  generatedKeys.getInt(1);
                System.out.println("key"+id);
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }
    
    void getStats(){
        
    }
    
    static Product addProduct(MessAdmin ma, String name, int price){
        Product p = new Product();
        p.p_name = name;
        p.price = price;
        p.p_id = p.addToDb();
        if(p.p_id != -1){
            products.add(p);
            return p;
        }
        return null;        
    }
}
