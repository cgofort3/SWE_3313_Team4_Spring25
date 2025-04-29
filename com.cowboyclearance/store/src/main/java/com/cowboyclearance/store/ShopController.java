package com.cowboyclearance.store;

import com.cowboyclearance.store.database.Inventory;
import com.cowboyclearance.store.database.SQLite;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ShopController {

    @GetMapping("shop")
    String getHome(HttpSession session, Model model){
        if(session.getAttribute("user") != null){
            System.out.println(session.getAttribute("user").toString());
            List<Inventory> inventory = SQLite.getUnsoldInventory();

            model.addAttribute("inventory", inventory);
            model.addAttribute("isAdmin", SQLite.getUser((String)session.getAttribute("user")).getAdmin());
            return "shop";
        }
        else{
            return "redirect:/login";
        }
    }
    @GetMapping("/")
    String index(HttpSession session, Model model){
        return "redirect:/shop";
    }

    @PostMapping("/shop/add")
    String postShopAdd(@RequestParam("productId") int id,HttpSession session, Model model){
        List<Integer> cart = (List<Integer>) session.getAttribute("cart");
        if(cart == null)return "redirect:/";
        if (!cart.contains(id)) {
            cart.add(id);
        }
        session.setAttribute("cart", cart);
        System.out.println("cart size: " + cart.size());
        return "redirect:/shop";
    }

    @PostMapping("/shop/remove")
    String postShopRemove(@RequestParam("productId") int id,HttpSession session, Model model){
        List<Integer> cart = (List<Integer>) session.getAttribute("cart");
        if(cart == null)return "redirect:/";
        if (cart.contains(id)) {
            cart.remove(Integer.valueOf(id));
        }
        session.setAttribute("cart", cart);
        System.out.println("cart size: " + cart.size());
        return "redirect:/cart";
    }


}
