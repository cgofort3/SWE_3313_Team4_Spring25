package com.cowboyclearance.store;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {
    @GetMapping("/cart")
    public String getCart(Model model){
        return "cart";
    }

    @GetMapping("/checkout")
    public String getCheckout(Model model){
        return "checkout";
    }
}
