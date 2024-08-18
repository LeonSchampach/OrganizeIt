package com.organizeit.db.service;

import com.organizeit.db.entity.Drawer;
import com.organizeit.db.entity.Shelf;
import com.organizeit.db.repository.DrawerRepository;
import com.organizeit.db.repository.ItemRepository;
import com.organizeit.db.repository.ShelfRepository;
import com.organizeit.errorhandling.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShelfService {
    @Autowired
    private ShelfRepository shelfRepository;

    @Autowired
    private DrawerRepository drawerRepository;

    @Autowired
    private ItemRepository itemRepository;


    @Transactional
    public Shelf createShelf(Shelf shelf) {
        return shelfRepository.save(shelf);
    }

    //getting all Shelf records
    public List<Shelf> getAllShelf(){
        List<Shelf> shelves = new ArrayList<Shelf>();
        shelfRepository.findAll().forEach(shelves::add);
        return shelves;
    }

    public List<Shelf> getShelvesByShelfListId(int id){
        return new ArrayList<>(shelfRepository.findShelvesByShelfListId(id));
    }

    //getting a specific record
    public Shelf getShelfById(int id){
        if(shelfRepository.findById(id).isEmpty()){
            return null;
        }
        return shelfRepository.findById(id).get();
    }

    //saving/updating a specific record
    public Shelf saveOrUpdate(Shelf shelf){
        return shelfRepository.save(shelf);
    }

    //deleting a specific record
    public String deleteShelf(int id){
        if(shelfRepository.findById(id).isPresent()){
            List<Drawer> drawers = drawerRepository.findDrawersByShelfId(id);
            for (Drawer drawer : drawers){
                itemRepository.deleteAll(itemRepository.findItemsByDrawerId(drawer.getId()));
            }
            drawerRepository.deleteAll(drawerRepository.findDrawersByShelfId(id));
            shelfRepository.deleteById(id);

            return "Shelf and associated Drawer and Item entries deleted!";
        }
        else {
            return ErrorMessages.INSTANCE.getInternalServerErrorString();
        }
    }

    //Returns all Drawers for a specific record
    public List<Drawer> getDrawersByShelfId(int id) {
        if (!(drawerRepository.findDrawersByShelfId(id).isEmpty())) {
            return drawerRepository.findDrawersByShelfId(id);
        } else {
            return null;
        }
    }
}
