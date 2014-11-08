/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.seglan.shop.sourcecode;

import javax.sql.DataSource;
import org.apache.commons.dbcp.cpdsadapter.DriverAdapterCPDS;
import org.apache.commons.dbcp.datasources.SharedPoolDataSource;

public class Pool
{
    private static DataSource ds;

    static
    {
        DriverAdapterCPDS cpds = new DriverAdapterCPDS();
        try
        {
            cpds.setDriver("com.mysql.jdbc.Driver");
            cpds.setUrl("jdbc:mysql://brickberlin.es:3306/brickbe1_brickberlin");
            cpds.setUser("brickbe1_enoman");
            cpds.setPassword("88e1en4n0m4nX");
        }catch(Exception e){}
        SharedPoolDataSource tds = new SharedPoolDataSource();
        tds.setConnectionPoolDataSource(cpds);
        tds.setMaxActive(20);
        tds.setMaxWait(50);

        ds = tds;
    }

    public static java.sql.Connection getConnection()
    {
        try{return ds.getConnection();}
        catch(Exception e){return null;}
    }
}
