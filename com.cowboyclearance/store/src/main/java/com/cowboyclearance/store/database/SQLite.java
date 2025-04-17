package com.cowboyclearance.store.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite {
    public static void query(String query) {
        try
                (
                        // create a database connection
                        Connection connection = DriverManager.getConnection("jdbc:sqlite:cowboy_clearance.sqlite");
                        Statement statement = connection.createStatement();
                )
        {
            statement.setQueryTimeout(30);  // set timeout to 30 sec.



        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            e.printStackTrace(System.err);
        }

    }
    public static void addUser(User user) {
        query("INSERT INTO sqlite_master (name) VALUES ('Cardinal');");//Cainan add queries and methods and stuff here idk how it all works
    }
    public static void updateUser(User user) {

    }
    public static void getAllUsers() {

    }
}
