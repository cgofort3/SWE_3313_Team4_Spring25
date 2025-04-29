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
    public String getCart(HttpSession session, Model model){
        List<Integer> cartId = (List<Integer>) session.getAttribute("cart");
        System.out.println("cartId: " + cartId);
        List<Inventory> allInventory = SQLite.getInventory();
        List<Inventory> cartInventory = new ArrayList<>();
        for (Inventory inventory : allInventory) {
            if (cartId.contains(inventory.getId())) {
                cartInventory.add(inventory);
            }
        }

        model.addAttribute("cart", cartInventory);
        return "cart";


    }

    @GetMapping("/checkout")
    public String getCheckout(Model model){
        return "checkout";
    }
}
