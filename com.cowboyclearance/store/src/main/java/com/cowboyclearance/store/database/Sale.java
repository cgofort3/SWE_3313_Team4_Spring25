package com.cowboyclearance.store.database;

import java.util.ArrayList;

public class Sale {

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private User user;
    private int subtotal;
    private int total;
    private String date;
    private String shipping;


    public Sale(){
        this.id = 0;
        this.user = null;
        this.subtotal = 0;
        this.total = 0;
        this.date = "";
        this.shipping = "";
    }

    public Sale(User user, int subtotal, int total, String date, String shipping) {
        this.user = user;
        this.subtotal = subtotal;
        this.total = total;
        this.date = date;
        this.shipping = shipping;
    }
    public ArrayList<Integer> getItems() {
        return items;
    }

    public void setItems(ArrayList<Integer> items) {
        this.items = items;
    }

    private ArrayList<Integer> items;

    public int getID() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

}
