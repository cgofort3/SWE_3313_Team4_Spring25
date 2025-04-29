package com.cowboyclearance.store;


import com.cowboyclearance.store.database.SQLite;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String getAdmin() {


        return "admin";
    }

    @PostMapping("/admin")
    public String postAdmin(@RequestParam("user") String user, Model model) {
        SQLite.makeAdmin(user);
        System.out.println(user + " is now admin");
        return "redirect:/admin";
    }

    @GetMapping("/admin/report")
    public String getReport() {

        return "admin";
    }
}
