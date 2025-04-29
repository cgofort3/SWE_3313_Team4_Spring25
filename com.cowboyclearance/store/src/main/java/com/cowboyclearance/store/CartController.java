package com.cowboyclearance.store;

import com.cowboyclearance.store.database.Inventory;
import com.cowboyclearance.store.database.SQLite;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CartController {
    @GetMapping("/cart")
    public String getCart(HttpSession session, Model model) {
        List<Integer> cartId = (List<Integer>) session.getAttribute("cart");
        if (cartId == null || cartId.isEmpty()) {
            model.addAttribute("cart", new ArrayList<>());
            model.addAttribute("total", "0.00");
            return "cart";
        }

        List<Inventory> allInventory = SQLite.getInventory();
        List<Inventory> cartInventory = new ArrayList<>();
        double total = 0.0;

        for (Inventory inventory : allInventory) {
            if (cartId.contains(inventory.getId())) {
                cartInventory.add(inventory);
                // Divide by 100 to convert cents to dollars
                total += inventory.getPrice() / 100.0;
            }
        }

        // Format total to 2 decimal places
        model.addAttribute("cart", cartInventory);
        model.addAttribute("total", String.format("%.2f", total));
        return "cart";
    }

    @GetMapping("/checkout")
    public String getCheckout(HttpSession session, Model model) {
        List<Integer> cartId = (List<Integer>) session.getAttribute("cart");
        if (cartId == null || cartId.isEmpty()) {
            return "redirect:/cart";
        }

        List<Inventory> allInventory = SQLite.getInventory();
        List<Inventory> cartInventory = new ArrayList<>();
        double subtotal = 0.0;

        for (Inventory inventory : allInventory) {
            if (cartId.contains(inventory.getId())) {
                cartInventory.add(inventory);
                subtotal += inventory.getPrice() / 100.0;
            }
        }

        double tax = subtotal * 0.06;

        model.addAttribute("cart", cartInventory);
        model.addAttribute("subtotal", String.format("%.2f", subtotal));
        model.addAttribute("tax", String.format("%.2f", tax));
        
        return "checkout";
    }
}