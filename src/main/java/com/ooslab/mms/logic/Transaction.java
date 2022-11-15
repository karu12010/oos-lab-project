/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ooslab.mms.logic;

/**
 *
 * @author cliford
 */
public class Transaction implements CommitDB {
    private int t_id, quantity;
    private String studentRollNo, productId;
    
    @Override
    public int addToDb() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static Transaction makeTransaction(MessAdmin ma, String rollNo, int p_id, int quantity){
        return null;
    }
    
}
