package com.fkucuk.repository;

import org.sql2o.Sql2o;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Created by fat on 04.12.2016.
 */
public class DbHelper {


    private static Sql2o sql2o;


    public static Sql2o getSql2o(){
            if (sql2o == null)
                sql2o = new Sql2o(getDataSource());

            return sql2o;
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

}


