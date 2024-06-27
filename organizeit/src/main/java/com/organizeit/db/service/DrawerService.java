package com.organizeit.db.service;

import com.organizeit.db.entity.Item;
import com.organizeit.db.repository.ItemRepository;
import com.organizeit.db.entity.Drawer;
import com.organizeit.db.repository.DrawerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DrawerService {
    @Autowired
    DrawerRepository drawerRepository;

    @Autowired
    ItemRepository itemRepository;

    //getting all Drawer records
    public List<Drawer> getAllDrawer(){
        List<Drawer> drawers = new ArrayList<Drawer>();
        drawerRepository.findAll().forEach(drawers::add);
        return drawers;
    }

    //getting a specific record
    public Drawer getDrawerByName(String name){
        if(drawerRepository.findById(name).isEmpty()){
            return null;
        }
        return drawerRepository.findById(name).get();
    }

    //saving/updating a specific record
    public void saveOrUpdate(Drawer drawer){
        drawerRepository.save(drawer);
    }

    //deleting a specific record
    public void deleteDrawer(String name){
        drawerRepository.deleteById(name);
    }

    //Returns all Items for a specific record
    public List<Item> getItemsByDrawerName(String drawerName) {
        Drawer drawer = drawerRepository.findById(drawerName).orElse(null);
        if (drawer != null) {
            return itemRepository.findByDrawer(drawer);
        } else {
            return null;
        }
    }
}
