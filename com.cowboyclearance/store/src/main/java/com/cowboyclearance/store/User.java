package com.cowboyclearance.store;

import java.util.ArrayList;
import java.util.UUID;

public class User {
    private static int nextId = 1;
    private int id;
    private Boolean admin;
    private String username;
    private String password;
    private ArrayList<Inventory> cart;
    private static ArrayList<User> users;

    public User(Boolean admin, String username, String password) {
        this.admin = admin;
        this.username = username;
        this.password = password;
        this.cart = new ArrayList<Inventory>();
        this.id = ++nextId;
        users = new ArrayList<User>();
        users.add(this);
    }
    public User(){
        this.admin = false;
        this.username = "";
        this.password = "";
        this.cart = new ArrayList<Inventory>();
        this.id = ++nextId;
        users = new ArrayList<User>();
        users.add(this);
    }
    public User(String username, String password) {
        this.admin = false;
        this.username = username;
        this.password = password;
        this.cart = new ArrayList<Inventory>();
        this.id = ++nextId;
        users = new ArrayList<User>();
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

    public String toString(){
        return "id : " + id + "\nusername: " + username + "\npassword: " + password + "\n\n";
    }
}
