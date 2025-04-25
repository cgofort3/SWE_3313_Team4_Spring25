package com.cowboyclearance.store;


import com.cowboyclearance.store.database.Login;
import com.cowboyclearance.store.database.User;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    @GetMapping("/register")
    String getRegister(Model model){
        model.addAttribute("login", new Login());
        return "register";
    }

    @PostMapping("/register")
    String postRegister(@ModelAttribute Login login, Model model){
        User user = new User(-1, login.getUsername(), login.getPassword());
        model.addAttribute("login", new Login());
        return "register";
    }
}
