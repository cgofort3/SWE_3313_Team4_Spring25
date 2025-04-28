package com.cowboyclearance.store.database;


import java.sql.*;
import java.util.ArrayList;

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
    public static void addUser(User user) {
        update("INSERT INTO Users (Username, Password) VALUES ("+user.getEmail()+", "+user.getPassword()+");");
    }

    public static User getUser(String email) {
        //THIS IS A PLACEHOLDER METHOD PLEASE IMPLEMENT
        //needs to get users by email address
        //Also needs to return null if no user is found
        ResultSet result = query("");

        /*
        result.getSomethingIdk()
         */
        User user = null;
        return user;
    }

    public static void salesReport(User user) {

    }
    public static void makeAdmin(User user) {
        update("UPDATE Users SET IsAdmin = 1 WHERE Username = "+user.getEmail()+";");
    }

    /*public static void addInventory() {
        //INSERT INTO Inventory(ItemID, ItemName, Price, Description, Image) VALUES();
    }*/

    public static void addSale(User user, Sale sale ){
        update("INSERT INTO Sales (UserID, PurchaseDate, Subtotal, Tax, Shipping, FinalTotal, CreditCard, SecurityCode, ExpirationDate) " +
                "VALUES ("+user.getId()+", sale.getPurchaseDate(), sale.getSubtotal(), sale.getTax(), sale.getShipping(), sale.getFinalTotal(), " +
                "sale.getCreditCard(), sale.getSecurityCode(), sale.getExpirationDate()");
    }

    public static void addSaleItem(int[] items, Sale sale){
        String insert = "";
        for(int i = 0; i < items.length ; i++) {
            insert += "INSERT INTO SalesInventoryItem (SalesID, ItemID) VALUES ("+sale.getID()+", "+items[i]+");\n";
        }
        update(insert);
    }

    public static void saleReport() {

    }

    public static int getShipping(String shipping) throws SQLException {
        return (query("SELECT "+shipping+"FROM ShippingInfo")).getInt("shipping");
    }

    public static ArrayList<Inventory> getInventory(){
        return null;
    }


    /*
    Some more methods needed:
        + getUser(): get user by username
        + getInventory(): get an array of all inventory items
     */


}
