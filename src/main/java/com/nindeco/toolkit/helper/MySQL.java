/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nindeco.toolkit.helper;

import com.nindeco.toolkit.SQLServer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author danabol
 */
public class MySQL
{

    //<editor-fold desc="Method">
    /**
     * Вставляет данные в таблицу MySQL<br>
     *
     * @param sql - строка запроса.
     * @return результат (ResultSet).
     */
    public int update (String sql)
    {
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        int res = 0;
        try
        {
            conn = DriverManager.getConnection(SQLServer.URL+SQLServer.DB, SQLServer.USER, SQLServer.PASS);
            preparedStmt = conn.prepareStatement(sql);
            res = preparedStmt.executeUpdate();
            preparedStmt.close();
            conn.close();
            return res;
        }
        catch (SQLException e)
        {
            //Handle errors for JDBC
            e.printStackTrace();
            return res;
        }
        catch (Exception e)
        {
            //Handle errors for Class.forName
            e.printStackTrace();
            return res;
        }
        finally
        {
            try
            {
                if (preparedStmt != null)
                {
                    preparedStmt.close();
                }
                if (conn != null)
                {
                    conn.close();
                }
            }
            catch (SQLException se)
            {
                se.printStackTrace();
            }
        }//end try
    }

    /**
     * Парсит данные из MySQL.
     *
     * @param sql строка запроса.
     * @return лист с данными спарсенные из DB.
     */
    public List<Map<String, Object>> parseData (String sql)
    {
        Connection conn = null;
        Statement stmt = null;
        Map<String, Object> row;
        List<Map<String, Object>> resultList;
        ResultSetMetaData metaData;
        try
        {
            conn = DriverManager.getConnection(SQLServer.URL+SQLServer.DB, SQLServer.USER, SQLServer.PASS);
            stmt = (Statement) conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            resultList = new ArrayList<>();
            metaData = rs.getMetaData();
            Integer columnCount = metaData.getColumnCount();
            while (rs.next())
            {
                row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++)
                {
                    row.put(metaData.getColumnName(i), rs.getObject(i));
                }
                resultList.add(row);
            }
            rs.close();
            stmt.close();
            conn.close();
            return resultList;
        }
        catch (SQLException se)
        {
            //Handle errors for JDBC
            se.printStackTrace();
            return null;
        }
        catch (Exception e)
        {
            //Handle errors for Class.forName
            e.printStackTrace();
            return null;
        }
        finally
        {
            try
            {
                if (stmt != null)
                {
                    stmt.close();
                }
                if (conn != null)
                {
                    conn.close();
                }
            }
            catch (SQLException se)
            {
                se.printStackTrace();
            }
        }//end try
    }
// </editor-fold>
}
