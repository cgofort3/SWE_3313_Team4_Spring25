package com.cowboyclearance.store.database;

import java.util.ArrayList;

public class User {
    private int id;
    private Boolean admin;
    private String username;
    private String password;
    private ArrayList<Inventory> cart;
    private static ArrayList<User> users = new ArrayList<User>();

    public static User createUser(String username, String password){
        SQLite.query("");//Cainan use this to create a new user in the table
        return new User();
    }

    public User(Boolean admin, int id, String username, String password) {
        this.admin = admin;
        this.id = id;
        this.username = username;
        this.password = password;
        this.cart = new ArrayList<Inventory>();
        users.add(this);
    }
    public User(){
        this.id = -1;
        this.admin = false;
        this.username = "";
        this.password = "";
        this.cart = new ArrayList<Inventory>();
        users.add(this);
    }
    public User(int id, String username, String password) {
        this.id = id;
        this.admin = false;
        this.username = username;
        this.password = password;
        this.cart = new ArrayList<Inventory>();
        users.add(this);
    }

    public int getId(){
        return id;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public ArrayList<Inventory> getCart() {
        return cart;
    }
    public void addToCart(Inventory inventory){
        this.cart.add(inventory);
    }
    public static ArrayList<User> getUsers() {
        return users;
    }

    @Override
    public String toString(){
        return "id : " + id + "\nusername: " + username + "\npassword: " + password + "\n\n";
    }
}
