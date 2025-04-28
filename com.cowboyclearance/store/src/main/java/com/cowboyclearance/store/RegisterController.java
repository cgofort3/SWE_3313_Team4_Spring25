package com.cowboyclearance.store;


import com.cowboyclearance.store.database.Login;
import com.cowboyclearance.store.database.SQLite;
import com.cowboyclearance.store.database.User;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RegisterController {
    @GetMapping("/register")
    String getRegister(Model model){
        model.addAttribute("login", new Login());
        return "register";
    }

    @PostMapping("/register")
    RedirectView postRegister(@ModelAttribute Login login, Model model){
        User user = new User(-1, login.getEmail(), login.getPassword());
        SQLite.addUser(user);
        return new RedirectView("/login");
    }
}
