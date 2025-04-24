package com.cowboyclearance.store;


import com.cowboyclearance.store.database.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    String getRegister(Model model){
        model.addAttribute("login", new Login());
        return "login";
    }

    @PostMapping("/login")
    String postLogin(@ModelAttribute Login login, Model model){
        User user = new User(-1, login.getUsername(), login.getPassword());
        model.addAttribute("login", new Login());
        model.addAttribute("wrongPassword", "1");
        login.setPassword("");
        model.addAttribute("login", login);
        return "login";
    }
}

