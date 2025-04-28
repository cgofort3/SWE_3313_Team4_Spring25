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

@Controller
public class LoginController {

    @GetMapping("/logout")
    RedirectView logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("/");
    }
    @GetMapping("/login")
    String getRegister(Model model){
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
            session.setAttribute("user", user.getEmail());
            return new RedirectView("/");
        }
        return new RedirectView("/login");
    }
}

