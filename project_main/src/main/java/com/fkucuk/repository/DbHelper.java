package com.fkucuk.repository;

import org.sql2o.Sql2o;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbHelper {


    private static Sql2o sql2o;


    public static Sql2o getSql2o(){
            if (sql2o == null) {


                try {
                    System.out.println("Loading driver...");
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("Driver loaded!");
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("Cannot find the driver in the classpath!", e);
                }


                sql2o = new Sql2o(getConnectionUrl(), getConnectionUserName(), getConnectionPassword());
            }
            return sql2o;
    }

    private static String getConnectionUrl(){
        String connUrl;
        if (System.getProperty("RDS_HOSTNAME") != null) {
            String dbName = System.getProperty("RDS_DB_NAME");


            String hostname = System.getProperty("RDS_HOSTNAME");
            String port = System.getProperty("RDS_PORT");
            connUrl = "jdbc:mysql://" + hostname + ":" +
                    port + "/" + dbName;
        }else{
            connUrl = "jdbc:mysql://localhost:3306/fatapp?useSSL=false";
        }
        return connUrl;
    }

    private static String getConnectionUserName(){
        String userName;
        if( System.getProperty("RDS_USERNAME") != null){
            userName = System.getProperty("RDS_USERNAME");
        }else{
            userName = "fatapp";
        }
        return userName;
    }

    private static String getConnectionPassword(){
        String password;
        if (System.getProperty("RDS_PASSWORD") != null){
            password = System.getProperty("RDS_PASSWORD");
        }else{
            password = "*************";
        }
        return password;
    }


    private static DataSource getDataSource() {
        String jndiname = "jdbc/fatApp";
        DataSource dataSource;
        try {
            dataSource = (DataSource) new InitialContext().lookup("java:comp/env/" + jndiname);
        } catch (NamingException e) {
            // Handle error that it's not configured in JNDI.
            throw new IllegalStateException(jndiname + " is missing in JNDI!", e);
        }
        return dataSource;
    }

    public static String getRemoteConnectionString(){

        if (System.getProperty("RDS_HOSTNAME") != null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String dbName = System.getProperty("RDS_DB_NAME");
                String userName = System.getProperty("RDS_USERNAME");
                String password = System.getProperty("RDS_PASSWORD");
                String hostname = System.getProperty("RDS_HOSTNAME");
                String port = System.getProperty("RDS_PORT");
                return  "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
                //logger.trace("Getting remote connection with connection string from environment variables.");
                //logger.info("Remote connection successful.");

            }
            catch (ClassNotFoundException e) {
                //    logger.warn(e.toString());
            }
        }
        return null;
    }

    private static Connection getRemoteConnection() {
        if (System.getProperty("RDS_HOSTNAME") != null) {
            try {
                Class.forName("org.mysql.jdbc.Driver");
                String dbName = System.getProperty("RDS_DB_NAME");
                String userName = System.getProperty("RDS_USERNAME");
                String password = System.getProperty("RDS_PASSWORD");
                String hostname = System.getProperty("RDS_HOSTNAME");
                String port = System.getProperty("RDS_PORT");
                String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
                //logger.trace("Getting remote connection with connection string from environment variables.");
                return DriverManager.getConnection(jdbcUrl);
                //logger.info("Remote connection successful.");
            }
            catch (ClassNotFoundException e) {
                //logger.warn(e.toString());
                }
            catch (SQLException e) {
                //logger.warn(e.toString());
                }
        }
        return null;
    }

}
