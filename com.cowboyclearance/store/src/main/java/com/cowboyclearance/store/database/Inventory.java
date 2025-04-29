package com.cowboyclearance.store.database;

import java.util.ArrayList;
import java.text.NumberFormat;
import java.util.Currency;

public class Inventory {
    private int id;
    private String name;
    private int price;
    private String description;

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String image;

    public Inventory(){
        this.id = 0;
        this.name = "";
        this.price = 0;
        this.description = "";
        this.image = "";
    }
    public Inventory(int id, String name, int price, String description, String image) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public String getFormattedPrice()
    {
        return NumberFormat.getCurrencyInstance().format((double)getPrice() / 100);

    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
}
