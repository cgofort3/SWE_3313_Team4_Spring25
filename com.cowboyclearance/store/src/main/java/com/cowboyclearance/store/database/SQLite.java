package com.cowboyclearance.store.database;

import com.cowboyclearance.store.database.Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite {


    public static ResultSet query(String query) {
        ResultSet result = null;
        try
                (
                        // create a database connection
                        Connection connection = DriverManager.getConnection("jdbc:sqlite:cowboy_clearance.sqlite");
                        Statement statement = connection.createStatement();
                )
        {
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            //statement.executeUpdate("insert into person values(2, 'yui')");
            result = statement.executeQuery(query);

        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            e.printStackTrace(System.err);
        }
        return result;
    }
    public static void update(String query) {
        try
                (
                        // create a database connection
                        Connection connection = DriverManager.getConnection("jdbc:sqlite:cowboy_clearance.sqlite");
                        Statement statement = connection.createStatement();
                )
        {
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate(query);
            //ResultSet rs = statement.executeQuery("select * from person");

        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            e.printStackTrace(System.err);
        }
    }
    public static void addUser(Login login) {
        update("INSERT INTO Users (Username, Password) VALUES ("+login.getUsername()+", "+login.getPassword()+");");
    }
    public static void salesReport(User user) {

    }
    public static void makeAdmin(User user) {
        update("UPDATE Users SET IsAdmin = 1 WHERE Username = "+user.getUsername()+";");
    }

    /*public static void addInventory() {
        //INSERT INTO Inventory(ItemID, ItemName, Price, Description, Image) VALUES();
    }*/

    public static void addSale(){

    }

    public static void addSaleItem(){

    }

    public static void saleReport() {

    }


}
