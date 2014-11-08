/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.sourcecode;

import java.sql.*;

/**
 *
 * @author Iñaki Garaizabal
 */
public class DBAccess {

    private Connection conn;
    public DBAccess(Connection Conn){conn=Conn;}
    public boolean executeSQL(String sqlQuery) {
        PreparedStatement stmt =null;
        //Connection conn=common.getConnection();
        try {
            if (conn != null) {
                stmt = conn.prepareStatement(sqlQuery);
                stmt.execute();
                return true;
            }else{return false;}
        } catch (SQLException ex) {
            return false;
        }
        finally
        {
            try
            {
               stmt.close();
//               conn.close();
            }catch ( Exception ex) {}
        }
    }
    public int executeSQL_Id(String sqlQuery) {
        
        Statement stmt =null;
        try {
            if (conn != null) {
                stmt =conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY,java.sql.ResultSet.CONCUR_UPDATABLE);
                stmt.executeUpdate(sqlQuery,Statement.RETURN_GENERATED_KEYS);
                ResultSet res=stmt.getGeneratedKeys();
                if(res.next()){return res.getInt(1);}
                else{return -1;}
            }else{return -1;}
        } catch (SQLException ex) {
            return -1;
        }
        finally
        {
            try
            {
               stmt.close();
//               conn.close();
            }catch ( Exception ex) {}
        }
    }
    public ResultSet resultsetSQL(String sqlQuery) {
        PreparedStatement stmt =null;
        ResultSet res=null;
        try
        {
            if (conn != null) {
                stmt = conn.prepareStatement(sqlQuery);
                res=stmt.executeQuery();
                return res;
            }else{return null;}
        } catch (SQLException ex) {
            return null;
        }
    }
}