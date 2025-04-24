package com.cowboyclearance.store.database;

import java.util.ArrayList;

public class Inventory {
    private int id;
    private String name;
    private int price;
    private String description;
    private String image;

    public static Inventory createInventory(){
        SQLite.query("");
        return new Inventory();
    }
    public Inventory(){}
    public Inventory(int id, String name, int price, String description, String image) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.description = description;
        this.image = image;
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
