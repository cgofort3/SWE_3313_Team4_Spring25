package com.cowboyclearance.store.database;

import java.util.ArrayList;

public class User extends Login{
    private int id;
    private Boolean admin;
    private String username;
    private String password;


    public User(Boolean admin, int id, String username, String password) {
        this.admin = admin;
        this.id = id;
        this.username = username;
        this.password = password;
    }
    public User(){
        this.id = -1;
        this.admin = false;
        this.username = "";
        this.password = "";
    }
    public User(int id, String username, String password) {
        this.id = id;
        this.admin = false;
        this.username = username;
        this.password = password;
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

    @Override
    public String toString(){
        return "id : " + id + "\nusername: " + username + "\npassword: " + password + "\n\n";
    }
}
