package com.cowboyclearance.store;

import com.cowboyclearance.store.database.Inventory;
import com.cowboyclearance.store.database.User;

import java.util.ArrayList;

public class Session {
    private String sessionId;
    private User user;
    private ArrayList<Inventory> cart;

    public Session(User user){
        this.user = user;
        this.cart = new ArrayList<Inventory>();
    }


    public void logout()
    {

    }
}
