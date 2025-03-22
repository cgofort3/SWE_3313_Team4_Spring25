package com.cowboyclearance.store;

public class InventoryItem {
    private static int nextId = 0;
    private int id;
    private String name;
    private int price;
    private String description;
    private String image;
    public InventoryItem(String name, int price, String description, String image) {
        id = ++nextId;
        this.name = name;
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
