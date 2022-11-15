/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.ooslab.mms;
import com.ooslab.mms.db.DbAccess;
import com.ooslab.mms.ui.LoginPage;
import java.sql.SQLException;

/**
 *
 * @author cliford
 */
public class Mms {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DbAccess.connect();
        new LoginPage().setVisible(true);
    }
}
