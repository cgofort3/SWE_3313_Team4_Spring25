package com.cowboyclearance.store.database;

public class User extends Login{
    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private Boolean admin;
    private String email;
    private String password;



    public User(Boolean admin, int id, String username, String password) {
        this.admin = admin;
        this.id = id;
        this.email = username;
        this.password = password;
    }
    public User(){
        this.id = -1;
        this.admin = false;
        this.email = "";
        this.password = "";
    }
    public User(int id, String username, String password) {
        this.id = id;
        this.admin = false;
        this.email = username;
        this.password = password;
    }

    public int getId(){
        return id;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString(){
        return "id : " + id + "\nusername: " + email + "\npassword: " + password + "\n\n";
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
