package com.poly.Jdbc;

import java.sql.*;

public class SQLConnect {

    private static Connection con;

    public SQLConnect() {
    }

    protected static void driverTest() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (java.lang.ClassNotFoundException e) {
            throw new Exception("MySQL JDBC Driver not found ...");
        }
    }

    public static Connection getcon() throws Exception {

//        String url;
//        String user;
//        String pass;
//        String temphost = DatabaseHelper.host;
//        String tempuser = DatabaseHelper.user;
//        String temppass = DatabaseHelper.pass;
//        String tempdb = DatabaseHelper.database;
//        String tempport = DatabaseHelper.port;
    	String url;
    	String user;
    	String pass;
    	String temphost = "LAPTOP-QQ0D4I19";
    	String tempuser="sa";
    	String temppass="songlong";
    	String tempdb = "ThuocTay";
    	String tempport = "1433";
        try {
//            SQLConnect.driverTest();
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            url = "jdbc:sqlserver://" + temphost + ":" + tempport + ";databaseName=" + tempdb;
            user = tempuser;
            pass = temppass;
            
//            url = "jdbc:sqlserver://LAPTOP-QQ0D4I19:1433;databaseName=ThuocTay";
//            user = "sa";
//            pass = "songlong";
            con = DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            throw new Exception("Khong The Ket Noi Den Database Server ..." + e.getMessage());
        }
        return con;
    }

    public static PreparedStatement prepareStatement(String sql, Object... args) {
        try {

            PreparedStatement ppst = null;
            if (sql.trim().startsWith("{")) {
                ppst = getcon().prepareCall(sql);
            } else {
                ppst = getcon().prepareStatement(sql);
            }
            for (int i = 0; i < args.length; i++) {
                ppst.setObject(i + 1, args[i]);
            }
            return ppst;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeUpdate(String sql, Object... args) {
        try {
            PreparedStatement st = prepareStatement(sql, args);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public static ResultSet excuteQuery(String sql, Object... args) {
        try {
            PreparedStatement st = prepareStatement(sql, args);
            return st.executeQuery();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
