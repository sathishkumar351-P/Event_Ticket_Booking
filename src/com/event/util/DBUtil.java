package com.event.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    public static Connection getDBConnection() throws Exception {

    	Class.forName("oracle.jdbc.OracleDriver");


    	Connection con = DriverManager.getConnection(
    		    "jdbc:oracle:thin:@localhost:1521:XE",
    		    "system",
    		    "1905"
    		);


        return con;   // 🔴 THIS LINE IS CRITICAL
    }
}
