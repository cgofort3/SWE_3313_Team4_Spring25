package com.cowboyclearance.store;

import com.cowboyclearance.store.database.Inventory;
import com.cowboyclearance.store.database.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
@RequestMapping("/session")
public class SessionController {

    // Create a session and store an attribute
    @GetMapping("/create")
    public RedirectView createSession(HttpSession session, @RequestParam(defaultValue = "shop") String redir) {
        // Set a session attribute (e.g., username)
        session.setAttribute("username", "JohnDoe");
        // Retrieve and return the session ID
        String sessionId = session.getId();

        System.out.println(redir);
        return new RedirectView("/" + redir);
    }

    // Retrieve session attribute
    @GetMapping("/get")
    public String getSession(HttpSession session) {
        // Get the session attribute (username)
        String username = (String) session.getAttribute("username");

        // If no session exists, return an error message
        if (username == null) {
            return "login";
        }

        // Return session data to the client
        return "Session found with username: " + username;
    }

    // Invalidate the session
    @GetMapping("/invalidate")
    public RedirectView invalidateSession(HttpSession session) {
        // Invalidate the session
        session.invalidate();

        return new RedirectView("/login");
    }
}
