package com.cowboyclearance.store;


import com.cowboyclearance.store.database.Inventory;
import com.cowboyclearance.store.database.SQLite;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

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

        List<Inventory> inventory = SQLite.getSoldInventory();

        model.addAttribute("inventory", inventory);
        model.addAttribute("isAdmin", SQLite.getUser((String)session.getAttribute("user")).getAdmin());
        model.addAttribute("users", SQLite.getSaleUsers(inventory));


        return "sales";
    }

    @GetMapping("/admin/report/download")
    public void downloadCsv(HttpSession session, HttpServletResponse response) throws IOException {
        if(!SQLite.getUser((String)session.getAttribute("user")).getAdmin())return;

        String csv = "";
        try
        {
            csv = SQLite.exportSoldItemsCsvString();
            System.out.println(csv);

            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=\"salesreport.csv\"");

            try (PrintWriter writer = response.getWriter()) {
                writer.write(csv);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}
