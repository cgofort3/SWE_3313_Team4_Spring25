package com.cowboyclearance.store;

import com.cowboyclearance.store.database.Inventory;
import com.cowboyclearance.store.database.SQLite;
import com.cowboyclearance.store.database.Sale;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CartController {
    @GetMapping("/cart")
    public String getCart(HttpSession session, Model model){
        List<Integer> cartId = (List<Integer>) session.getAttribute("cart");
        if (cartId == null || cartId.isEmpty()) {
            model.addAttribute("cart", new ArrayList<Integer>());
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
    /*

    @GetMapping("/checkout")
    public String getCheckout(HttpSession session, Model model) {
        List<Integer> cartId = (List<Integer>) session.getAttribute("cart");
        if (cartId == null || cartId.isEmpty()) {
            return "redirect:/cart";
        }

        List<Inventory> allInventory = SQLite.getInventory();
        List<Inventory> cartInventory = new ArrayList<>();
        float subtotal = 0.0f;

        for (Inventory inventory : allInventory) {
            if (cartId.contains(inventory.getId())) {
                cartInventory.add(inventory);
                subtotal += inventory.getPrice();
            }
        }
        subtotal /= 100.0;

        float tax = subtotal * 0.06f;

        model.addAttribute("cart", cartInventory);
        model.addAttribute("subtotal", String.format("%.2f", subtotal));
        model.addAttribute("tax", String.format("%.2f", tax));

        session.setAttribute("cartItems", cartInventory);
        session.setAttribute("subtotal", subtotal);
        session.setAttribute("tax", tax);

        return "checkout";
    }

    @PostMapping("/checkout")
    public String postCheckout(HttpSession session, Model model, @RequestParam("shippingSpeed") String shipping, @RequestParam("cardNumber") String cardNumber) {
        ArrayList<Inventory> cart = (ArrayList<Inventory>)session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            return "redirect:/cart";
        }
        Sale sale = new Sale();
        sale.setItems((ArrayList<Integer>)session.getAttribute("cart"));
        sale.setUser(SQLite.getUser((String)session.getAttribute("user")));
        sale.setSubtotal((Math.round(100 * (float)session.getAttribute("subtotal"))));
        sale.setTax((Math.round(100 * (float)session.getAttribute("tax"))));
        System.out.println("Subtotal: " + sale.getSubtotal());
        //System.out.println("Subtotal: " + sale.getSubtotal() + "\nTax: " + (int)(Float.parseFloat((String)session.getAttribute("tax"))));
        sale.setTotal((int)(sale.getSubtotal() * (1 + (float)session.getAttribute("tax") / 100)));
        System.out.println("sale = " + sale.getTotal());
        sale.setShipping(shipping);
        SQLite.addSale(sale);
        session.removeAttribute("subtotal");
        session.removeAttribute("tax");
        session.removeAttribute("cartItems");



        session.setAttribute("cart", new ArrayList<Integer>());
        model.addAttribute("cartItems", (ArrayList<Inventory>)session.getAttribute("cartItems"));
        model.addAttribute("cardNumber", cardNumber);
        model.addAttribute("sale", sale);
        return "receipt";

    }

     */



    @GetMapping("/checkout")
    public String getCheckout(HttpSession session, Model model) {
        List<Integer> cartId = (List<Integer>) session.getAttribute("cart");
        if (cartId == null || cartId.isEmpty()) {
            return "redirect:/cart";
        }

        List<Inventory> allInventory = SQLite.getInventory();
        ArrayList<Inventory> cartInventory = new ArrayList<>();
        double subtotal = 0.0f;

        for (Inventory inventory : allInventory) {
            if (cartId.contains(inventory.getId())) {
                cartInventory.add(inventory);
                subtotal += inventory.getPrice();
            }
        }
        subtotal /= 100.0;

        double tax = subtotal * 0.06f;

        model.addAttribute("cart", cartInventory);
        model.addAttribute("subtotal", String.format("%.2f", subtotal));
        model.addAttribute("tax", String.format("%.2f", tax));

        Sale sale = new Sale();
        sale.setItems(cartInventory);
        sale.setTax((int)(100 * Double.parseDouble((String)model.getAttribute("tax"))));
        sale.setSubtotal((int)(100 * Double.parseDouble((String)model.getAttribute("subtotal"))));
        sale.setUser(SQLite.getUser((String)session.getAttribute("user")));

        session.setAttribute("sale", sale);
        return "checkout";
    }

    @PostMapping("/checkout")
    public String postCheckout(HttpSession session, Model model, @RequestParam("shippingSpeed") String shipping, @RequestParam("cardNumber") String cardNumber) {
        ArrayList<Inventory> cart = (ArrayList<Inventory>)session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            return "redirect:/cart";
        }
        Sale sale = (Sale)session.getAttribute("sale");
        sale.setTotal((int)(sale.getSubtotal() + (float)sale.getTax()));
        System.out.println("sale = " + sale.getTotal());
        sale.setShipping(shipping);
        SQLite.addSale(sale);



        session.removeAttribute("sale");
        session.setAttribute("cart", new ArrayList<Integer>());
        model.addAttribute("cartItems", (ArrayList<Inventory>)session.getAttribute("cartItems"));
        model.addAttribute("cardNumber", cardNumber);
        model.addAttribute("sale", sale);
        return "receipt";

    }
}
