package com.cowboyclearance.store;

import com.cowboyclearance.store.database.Inventory;
import com.cowboyclearance.store.database.SQLite;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class ShopController {

    @GetMapping("/")
    RedirectView getHome(HttpSession session, Model model){
        if(session.getAttribute("login") != null){
            System.out.println(session.getAttribute("login").toString());
            return new RedirectView("/shop");
        }
        else{
            System.out.println("Not logged in!");
            return new RedirectView("/login");
        }
    }

    @GetMapping("/shop")
    String getShop(Model model){
        ArrayList<Inventory> inventory = SQLite.getInventory();
        model.addAttribute("inventory", Arrays.asList(
                new Inventory(0, "Cowboy hat", 2010, "A very high quality hat.", "https://i.ebayimg.com/images/g/8D0AAOSwOa1h5GXl/s-l1200.jpg"),
                new Inventory(1, "Extra cool cowboy hat", 4059, "An incredibly high quality hat.", "https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcR77e2KsBFOvaDZEqu6vVhS-i8Z1lIphYzD50ivytkIEiHp20ylEZ4S89IkNtyjLW5RuJkHF6spW9fXJSztLOsJtpFQMw4Yu0eNIZ0fLV6ozQfmc8eqbMXGEA")
        ));
        return "shop";
    }


}
