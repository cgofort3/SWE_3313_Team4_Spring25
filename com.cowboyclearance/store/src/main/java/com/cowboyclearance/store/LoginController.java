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
        /*
        User user = SQLite.getUser(login.getEmail());
        if(user == null){
            return new RedirectView("/login");
        }
        if(user.getPassword().equals(login.getPassword())){
            session.setAttribute("user", user.getEmail());
            return new RedirectView("/");
        }
        */
        session.setAttribute("login", login);
        return new RedirectView("/");
    }
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

