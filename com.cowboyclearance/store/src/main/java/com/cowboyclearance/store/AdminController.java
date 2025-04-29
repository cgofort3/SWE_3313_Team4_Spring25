package com.cowboyclearance.store;


import com.cowboyclearance.store.database.SQLite;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String getAdmin(HttpSession session, Model model) {

        if(SQLite.getUser((String)session.getAttribute("user")).getAdmin()){
            return "admin";
        }
        return "redirect:/";


    }

    @PostMapping("/admin")
    public String postAdmin(HttpSession session, @RequestParam("user") String user, Model model) {
        if(!SQLite.getUser((String)session.getAttribute("user")).getAdmin())return "redirect:/";


        SQLite.makeAdmin(user);
        System.out.println(user + " is now admin");
        return "redirect:/admin";
    }

    @GetMapping("/admin/report")
    public String getReport(HttpSession session, Model model) {
        if(!SQLite.getUser((String)session.getAttribute("user")).getAdmin())return "redirect:/";


        return "admin";
    }
}
