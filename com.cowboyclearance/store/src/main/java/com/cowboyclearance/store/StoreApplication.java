package com.cowboyclearance.store;


import com.cowboyclearance.store.database.SQLite;
import com.cowboyclearance.store.database.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class StoreApplication {


    public static void main(String[] args) {

        SpringApplication.run(StoreApplication.class, args);
        System.out.println("Hello World");

    }
}
