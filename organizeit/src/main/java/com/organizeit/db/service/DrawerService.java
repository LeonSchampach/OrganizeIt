package com.organizeit.db.service;

import com.organizeit.db.entity.Item;
import com.organizeit.db.entity.Shelf;
import com.organizeit.db.repository.ItemRepository;
import com.organizeit.db.entity.Drawer;
import com.organizeit.db.repository.DrawerRepository;
import com.organizeit.db.repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DrawerService {
    @Autowired
    DrawerRepository drawerRepository;

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    private ShelfRepository shelfRepository;

    @Transactional
    public Drawer createDrawer(Drawer drawer) {
        return drawerRepository.save(drawer);
    }

    //getting all Drawer records
    public List<Drawer> getAllDrawer(){
        List<Drawer> drawers = new ArrayList<Drawer>();
        drawerRepository.findAll().forEach(drawers::add);
        return drawers;
    }

    //getting a specific record
    public Drawer getDrawerById(int id){
        if(drawerRepository.findById(id).isEmpty()){
            return null;
        }
        return drawerRepository.findById(id).get();
    }

    //saving/updating a specific record
    public void saveOrUpdate(Drawer drawer){
        drawerRepository.save(drawer);
    }

    //deleting a specific record
    public void deleteDrawer(int id){
        drawerRepository.deleteById(id);
    }

    //Returns all Items for a specific record
    public List<Item> getItemsByDrawerId(int id) {
        if (!(itemRepository.findItemsByDrawerId(id).isEmpty())) {
            return itemRepository.findItemsByDrawerId(id);
        } else {
            return null;
        }
    }
}
