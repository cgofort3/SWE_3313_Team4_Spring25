package com.cowboyclearance.store.database;

public class Login {
    private String email;
    private String password;
    public Login(){
        email = "";
        password = "";
    }
    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public String getEmail() {

        return email;
    }
    public void setEmail(String email) {

        this.email = email;
    }
    public String getPassword() {

        return password;
    }
    public void setPassword(String password) {

        this.password = password;
    }
    @Override
    public String toString() {
        return "Email: " + this.getEmail() + "\nPassword: " + this.getPassword();
    }
}
