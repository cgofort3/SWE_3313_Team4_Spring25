package com.cowboyclearance.store;


import com.cowboyclearance.store.database.Login;
import com.cowboyclearance.store.database.SQLite;
import com.cowboyclearance.store.database.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
public class LoginController {

    @GetMapping("/logout")
    RedirectView getLogout(HttpSession session) {
        session.invalidate();
        return new RedirectView("/");
    }
    @GetMapping("/login")
    String getLogin(Model model){
        model.addAttribute("login", new Login());
        return "login";
    }

    @PostMapping("/login")
    RedirectView postLogin(@ModelAttribute Login login, HttpSession session, Model model){

        User user = SQLite.getUser(login.getEmail());
        if(user == null){
            return new RedirectView("/login");
        }
        if(user.getPassword().equals(login.getPassword())){
            System.out.println("Logged in!");
            session.setAttribute("user", user.getEmail());
            return new RedirectView("/");
        }

        session.setAttribute("login", user.getEmail());

        session.setAttribute("cart", new ArrayList<Integer>());
        return new RedirectView("/");
    }
    @GetMapping("/register")
    String getRegister(Model model){
        model.addAttribute("login", new Login());
        return "register";
    }

    @PostMapping("/register")
    RedirectView postRegister(@ModelAttribute Login login, Model model){
        System.out.println(login.getEmail());
        User user = new User(-1, login.getEmail(), login.getPassword());
        SQLite.addUser(user);
        return new RedirectView("/login");
    }
}

