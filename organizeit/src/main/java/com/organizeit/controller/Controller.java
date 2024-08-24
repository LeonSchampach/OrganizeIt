package com.organizeit.controller;

import com.organizeit.db.entity.Drawer;
import com.organizeit.db.entity.Item;
import com.organizeit.db.entity.Shelf;
import com.organizeit.db.service.DrawerService;
import com.organizeit.db.service.ItemService;
import com.organizeit.db.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@org.springframework.stereotype.Controller
@org.springframework.stereotype.Controller
@SessionAttributes("endpointRegistry")
public class Controller {
    @Autowired
    private DrawerService drawerService;

    @Autowired
    private ShelfService shelfService;

    @GetMapping("/")
    public String index(Model model) {
        List<Shelf> shelves = shelfService.getAllShelf();
        List<Drawer> drawers = drawerService.getAllDrawer();
        model.addAttribute("shelves", shelves);
        model.addAttribute("drawers", drawers);
        return "index";
    }

    @GetMapping("/Drawers/{drawerId}")
    public String readEndpoints(@PathVariable long drawerId, Model model){
        List<Item> items = drawerService.getItemsByDrawerId(drawerId);
        Drawer drawer = drawerService.getDrawerById(drawerId);
        model.addAttribute("items", items);
        model.addAttribute("drawer", drawer);
        return "drawer";
    }

    @GetMapping("/hello")
    public String getGreetings(Model model){
        model.addAttribute("attribute1", "Hello");
        model.addAttribute("attribute2", "World");
        return "hello";
    }

}