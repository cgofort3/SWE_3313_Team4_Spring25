package com.cowboyclearance.store;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class ShopController {

    @GetMapping("/shop")
    String getInventory(Model model){
        model.addAttribute("inventory", Arrays.asList(
                new Inventory("Cowboy hat", 2000, "A very high quality hat.", "https://i.ebayimg.com/images/g/8D0AAOSwOa1h5GXl/s-l1200.jpg"),
                new Inventory("Extra cool cowboy hat", 4000, "An incredibly high quality hat.", "https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcR77e2KsBFOvaDZEqu6vVhS-i8Z1lIphYzD50ivytkIEiHp20ylEZ4S89IkNtyjLW5RuJkHF6spW9fXJSztLOsJtpFQMw4Yu0eNIZ0fLV6ozQfmc8eqbMXGEA")
        ));
        return "shop";
    }

}
