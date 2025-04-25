package com.cowboyclearance.store.database;

import com.cowboyclearance.store.Login;
import java.sql.*;
import java.util.ArrayList;
public class SQLite {

    public static void initializeDatabase() {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT NOT NULL,
                password TEXT NOT NULL
            )
            """;
        update(createTableSQL);
    }

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

    public static void addSale(User user, Sales sale ){
        update("INSERT INTO Sales (UserID, PurchaseDate, Subtotal, Tax, Shipping, FinalTotal, CreditCard, SecurityCode, ExpirationDate) " +
                "VALUES ("+user.getId()+", sale.getPurchaseDate(), sale.getSubtotal(), sale.getTax(), sale.getShipping(), sale.getFinalTotal(), " +
                "sale.getCreditCard(), sale.getSecurityCode(), sale.getExpirationDate()");
    }

    public static void addSaleItem(int[] items, Sales sale){
        String insert = "";
        for(int i = 0; i < items.length ; i++) {
            insert += "INSERT INTO SalesInventoryItem (SalesID, ItemID) VALUES ("+sales.getID()+", "+items[i]+");\n";
        }
        update(insert);
    }

    public static void saleReport() {

    }

    public static int getShipping(String shipping) throws SQLException {
        return (query("SELECT "+shipping+"FROM ShippingInfo")).getInt("shipping");
    }


}
