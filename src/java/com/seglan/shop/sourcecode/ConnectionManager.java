/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.seglan.shop.sourcecode;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;


/**
 * @ inadareishvili
 */
public class ConnectionManager {
 
    private final static String username = "shoppin1_seglan";
    private final static String password = "igaraizabal";
    private final static String url = "jdbc:mysql://shoppingstage.net:3306/shoppin1_shoppingstage";
    
    /*private final static String username = "root";
    private final static String password = "root";
    private final static String url = "jdbc:mysql://192.168.1.100:3306/shopstage";*/
    public static Connection connection = null;
    public static int connectionCount = 0;
 
    public ConnectionManager() {
        try {
            if (dataSource == null) {
                dataSource = new BasicDataSource();
                String driver = "com.mysql.jdbc.Driver";               
                try {
                    dataSource.setDriverClassName(driver);
                    dataSource.setUrl(url);
                    dataSource.setUsername(username);
                    dataSource.setPassword(password);
                    dataSource.setMaxActive(10);
                    dataSource.setMaxWait(10000);
                    dataSource.setMaxIdle(10);                   
                    if (connection == null || connection.isClosed()) {
                        System.out.println(" requeition CONNECTION WITH FIRST SERVER.");
                        connection = dataSource.getConnection();
                        connectionCount++;
                    }
                } catch (SQLException e) {
                    System.out.println("***Connection Requisition*** Could not connect to the database msg :" + e.getMessage());
                }
            }else{
                connection = dataSource.getConnection();
                connectionCount++;
            }
        } catch (Exception e) {
            System.out.println("Error in Connection:" + e.toString());
        }
    }
    public static BasicDataSource dataSource;
}