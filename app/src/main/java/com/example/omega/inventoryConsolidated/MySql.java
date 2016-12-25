package com.example.omega.inventoryConsolidated;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by OMEGA on 7/30/2016.
 */
public class MySql {
    public static String ip;
    public static String port;
    public static Boolean isConnected;

   public  static String getIp(){
       return ip;
   }

    public static Connection getSqlConnection() {
        ip = "jdbc:mysql://107.129.117.101:3306";
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(ip, "djneo33", "thehalo33");
            isConnected = true;

        } catch (Exception e) {
            e.printStackTrace();
            isConnected = false;
        }
        return connection;

    }
}
