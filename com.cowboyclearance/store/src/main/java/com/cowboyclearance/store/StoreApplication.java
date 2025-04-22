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
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(0, "Jared", "1234"));
        users.add(new User(0, "Ryan", "123456"));
        users.add(new User(0, "Carter", "4321"));
        users.add(new User(0, "Cainan", "987654321"));
        users.forEach(SQLite::addUser);

    }

    /*
    @Override
    public void run(String... args) throws Exception {

        System.out.println("Hello World");
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Jared", "1234"));
        users.add(new User("Ryan", "123456"));
        users.add(new User("Carter", "4321"));
        users.add(new User("Cainan", "987654321"));
        users.forEach(SQLite::addUser);
    }
     */
}
