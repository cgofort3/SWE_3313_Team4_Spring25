package com.cowboyclearance.store;


import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    @GetMapping("/register")
    String getRegister(Model model){
        return "register";
    }

    @PostMapping("/register")
    String postRegister(@ModelAttribute String password, @ModelAttribute String username, Model model){
        User user = new User(username, password);
        model.addAttribute("user", user);
        System.out.println(User.getUsers());
        return "register";
    }
}
