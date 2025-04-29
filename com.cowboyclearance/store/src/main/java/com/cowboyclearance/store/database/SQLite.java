package com.cowboyclearance.store.database;

import java.io.StringWriter;
import java.sql.*;
import java.util.*;

/**
 * Utility class for SQLite operations.
 * Uses try-with-resources, PreparedStatements, and batch updates.
 */
public class SQLite {
    private static final String URL = "jdbc:sqlite:cowboy_clearance.sqlite";




    /**
     * Given a list of Inventory items, return for each item a comma-separated
     * string of purchaser emails for that item. The returned list aligns by index
     * with the input list.
     *
     * @param items list of Inventory objects
     * @return list of comma-separated emails for each inventory item
     * @throws SQLException on DB error
     */
    public static List<String> getSaleUsers(List<Inventory> items) {
        List<String> emailsList = new ArrayList<>(items.size());
        String sql =
                "SELECT GROUP_CONCAT(u.Email) AS Emails " +
                        "FROM SalesInventoryItem si " +
                        "JOIN Sales s ON si.SalesID = s.SalesID " +
                        "JOIN Users u ON s.UserID = u.UserID " +
                        "WHERE si.ItemID = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Inventory inv : items) {
                ps.setInt(1, inv.getId());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String csv = rs.getString("Emails");
                        emailsList.add(csv != null ? csv : "");
                    } else {
                        emailsList.add("");
                    }
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return emailsList;
    }


    /**
     * Retrieve all inventory items that have been sold at least once.
     * Uses a thread-safe list wrapper and try-with-resources for JDBC.
     */
    public static List<Inventory> getSoldInventory() {
        // Thread-safe list to hold results
        List<Inventory> soldItems = Collections.synchronizedList(new ArrayList<>());  // :contentReference[oaicite:0]{index=0}

        // Select each inventory item that appears in the sales-item junction table
        String sql =
                "SELECT DISTINCT i.ItemID, i.ItemName, i.Price, i.Description, i.Image " +
                        "FROM Inventory i " +
                        "INNER JOIN SalesInventoryItem si ON i.ItemID = si.ItemID";               // standard SQL JOIN

        try (
                Connection conn = DriverManager.getConnection(URL);                       // JDBC URL connection
                PreparedStatement ps = conn.prepareStatement(sql);                        // parameter-free PreparedStatement
                ResultSet rs = ps.executeQuery()                                          // executeQuery returns ResultSet
        ) {
            while (rs.next()) {
                Inventory inv = new Inventory();
                inv.setId(rs.getInt("ItemID"));                                       // read integer column
                inv.setName(rs.getString("ItemName"));                                // read string column
                inv.setPrice(rs.getInt("Price"));
                inv.setDescription(rs.getString("Description"));
                inv.setImage(rs.getString("Image"));
                soldItems.add(inv);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return soldItems;
    }

    public static String exportSoldItemsCsvString() throws SQLException {
        String sql =
                "SELECT si.ItemID, i.ItemName, u.Email AS UserEmail " +
                        "FROM SalesInventoryItem si " +
                        "INNER JOIN Sales s ON si.SalesID = s.SalesID " +
                        "INNER JOIN Users u ON s.UserID = u.UserID " +
                        "INNER JOIN Inventory i ON si.ItemID = i.ItemID";              // standard SQL JOIN :contentReference[oaicite:0]{index=0}

        StringWriter sw = new StringWriter();                             // in-memory writer

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            ResultSetMetaData md = rs.getMetaData();                       // get column info
            int cols = md.getColumnCount();

            // Write header row
            for (int i = 1; i <= cols; i++) {
                sw.write(md.getColumnName(i));
                if (i < cols) sw.write(',');
            }
            sw.write("\n");

            // Write data rows
            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    String field = rs.getString(i);
                    if (field == null) {
                        field = "";
                    }
                    // escape if contains comma, quote, or newline
                    if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
                        field = "\"" + field.replace("\"", "\"\"") + "\"";
                    }
                    sw.write(field);
                    if (i < cols) sw.write(',');
                }
                sw.write("\n");
            }
        }

        return sw.toString();
    }

    /**
     * Execute a SELECT query with parameters; return an open ResultSet.
     * Caller must process and close the ResultSet.
     */
    private static ResultSet executeQuery(Connection conn, String sql, Object... params) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
        return ps.executeQuery();
    }

    /**
     * Execute an INSERT/UPDATE/DELETE with parameter binding.
     */
    private static void executeUpdate(Connection conn, String sql, Object... params) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            ps.executeUpdate();
        }
    }

    /**
     * Add a new user; ID is auto-generated by the database.
     */
    /*
    public static void addUser(User user) {
        System.out.println("Adding user " + user.getEmail());
        String sql = "INSERT INTO Users (Username, Password) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();

            // Capture generated ID and set into model
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    user.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }
     */

    /**
     * Add a new user; ID is auto-generated by the database.
     * New users are non-admins by default.
     */
    public static void addUser(User user) {
        String sql = "INSERT INTO Users (Email, Password, IsAdmin) VALUES (?, ?, 0)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();

            // Capture generated ID and set into model
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    user.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }


    /**
     * Fetch a user by email; return null if not found.
     */
    public static User getUser(String email) {
        String sql = "SELECT UserID, Email, Password, IsAdmin FROM Users WHERE Email = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             ResultSet rs = executeQuery(conn, sql, email)) {

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("UserID"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setAdmin(rs.getBoolean("IsAdmin"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return null;
    }

    /**
     * Promote a user to administrator.
     */
    public static void makeAdmin(String email) {
        String sql = "UPDATE Users SET IsAdmin = 1 WHERE Email = ?";
        try (Connection conn = DriverManager.getConnection(URL)) {
            executeUpdate(conn, sql, email);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    /**
     * Insert a sale and its sale-items in a single transaction.
     * Sale.id is populated from the generated keys.
     */
    public static void addSale(Sale sale) {
        String saleSql =
                "INSERT INTO Sales (UserID, PurchaseDate, Subtotal, Tax, Shipping, FinalTotal) VALUES (?, ?, ?, ?, ?, ?)";
        String itemSql = "INSERT INTO SalesInventoryItem (SalesID, ItemID) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL)) {
            conn.setAutoCommit(false);

            // Insert sale record
            try (PreparedStatement psSale = conn.prepareStatement(saleSql, Statement.RETURN_GENERATED_KEYS)) {
                psSale.setInt(1, sale.getUser().getId());
                psSale.setString(2, sale.getDate());
                psSale.setInt(3, sale.getSubtotal());
                psSale.setInt(4, 0);               // tax placeholder
                psSale.setString(5, sale.getShipping());
                psSale.setInt(6, sale.getTotal());
                psSale.executeUpdate();

                try (ResultSet keys = psSale.getGeneratedKeys()) {
                    if (keys.next()) {
                        sale.setId(keys.getInt(1));
                    }
                }
            }

            // Batch insert items
            try (PreparedStatement psItem = conn.prepareStatement(itemSql)) {
                for (int itemId : sale.getItemIds()) {
                    psItem.setInt(1, sale.getID());
                    psItem.setInt(2, itemId);
                    psItem.addBatch();
                }
                psItem.executeBatch();
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    /**
     * Retrieve all sales for a user.
     */
    public static List<Map<String, Object>> getSalesReportData() {
        List<Map<String, Object>> reportData = new ArrayList<>();
        String sql = "SELECT s.SalesID, u.Email AS UserEmail, s.PurchaseDate, " +
                "s.Subtotal, s.Tax, s.Shipping, s.FinalTotal, " +
                "GROUP_CONCAT(i.ItemID) AS Items " +
                "FROM Sales s " +
                "JOIN Users u ON s.UserID = u.UserId " +
                "LEFT JOIN SalesInventoryItem si ON s.SalesID = si.SalesID " +
                "LEFT JOIN Inventory i ON si.ItemID = i.ItemID " +
                "GROUP BY s.SalesID";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    row.put(columnName, rs.getObject(i));
                }
                reportData.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return reportData;
    }

    /**
     * Retrieve inventory list; thread-safe list wrapper.
     */
    public static List<Inventory> getInventory() {
        List<Inventory> list = Collections.synchronizedList(new ArrayList<>());
        String sql = "SELECT ItemID, ItemName, Price, Description, Image FROM Inventory";

        try (Connection conn = DriverManager.getConnection(URL);
             ResultSet rs = executeQuery(conn, sql)) {

            while (rs.next()) {
                Inventory inv = new Inventory();
                inv.setId(rs.getInt("ItemID"));
                inv.setName(rs.getString("ItemName"));
                inv.setPrice(rs.getInt("Price"));
                inv.setDescription(rs.getString("Description"));
                inv.setImage(rs.getString("Image"));
                list.add(inv);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return list;
    }

    /**
     * Retrieve inventory items that haven't been sold
     */
    public static List<Inventory> getUnsoldInventory() {
        List<Inventory> unsoldItems = Collections.synchronizedList(new ArrayList<>());
        String sql = "SELECT i.ItemID, i.ItemName, i.Price, i.Description, i.Image "
                + "FROM Inventory i "
                + "WHERE i.ItemID NOT IN (SELECT ItemID FROM SalesInventoryItem)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Inventory inv = new Inventory();
                inv.setId(rs.getInt("ItemID"));
                inv.setName(rs.getString("ItemName"));
                inv.setPrice(rs.getInt("Price"));
                inv.setDescription(rs.getString("Description"));
                inv.setImage(rs.getString("Image"));
                unsoldItems.add(inv);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return unsoldItems;
    }

    /**
     * Lookup a shipping rate column by name. Column values are integers representing cents.
     */
    public static int getShippingInfo(String column) {
        String sql = "SELECT [" + column + "] FROM ShippingInfo";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            return rs.getInt(column);
        }
        catch (SQLException e) {
            e.printStackTrace(System.err);
            return 0;
        }
    }
}