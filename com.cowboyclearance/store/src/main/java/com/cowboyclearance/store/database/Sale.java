package com.cowboyclearance.store.database;

import java.text.NumberFormat;
import java.util.ArrayList;

public class Sale {

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private int id;
    private User user;
    private int subtotal;
    private int total;
    private String date;
    private String shipping;

    public int getTax() {
        return tax;
    }

    public String getFormattedTax()
    {
        return NumberFormat.getCurrencyInstance().format((float)getTax() / 100);

    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    private int tax;


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
    public ArrayList<Inventory> getItems() {
        return items;
    }

    public ArrayList<Integer> getItemIds() {
        ArrayList<Integer> itemIds = new ArrayList<>();
        for (Inventory item : items) {
            itemIds.add(item.getId());
        }
        return itemIds;
    }

    public void setItems(ArrayList<Inventory> items) {
        this.items = items;
    }

    private ArrayList<Inventory> items;

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

    public String getFormattedSubtotal()
    {
        return NumberFormat.getCurrencyInstance().format((double)getSubtotal() / 100);

    }

    public String getFormattedTotal()
    {
        return NumberFormat.getCurrencyInstance().format((double)getTotal() / 100);

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
