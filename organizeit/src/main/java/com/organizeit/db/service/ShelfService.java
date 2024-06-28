package com.organizeit.db.service;

import com.organizeit.db.entity.Drawer;
import com.organizeit.db.entity.Shelf;
import com.organizeit.db.repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShelfService {
    @Autowired
    ShelfRepository shelfRepository;

    @Transactional
    public Shelf createShelf(Shelf shelf) {
        // Ensure bidirectional relationship
        for (Drawer drawer : shelf.getDrawers()) {
            drawer.setShelf(shelf);
        }
        return shelfRepository.save(shelf);
    }

    //getting all Shelf records
    public List<Shelf> getAllShelf(){
        List<Shelf> shelves = new ArrayList<Shelf>();
        shelfRepository.findAll().forEach(shelves::add);
        return shelves;
    }

    //getting a specific record
    public Shelf getShelfById(Integer id){
        if(shelfRepository.findById(id).isEmpty()){
            return null;
        }
        return shelfRepository.findById(id).get();
    }

    //saving/updating a specific record
    public void saveOrUpdate(Shelf shelf){
        shelfRepository.save(shelf);
    }

    //deleting a specific record
    public void deleteShelf(int id){
        shelfRepository.deleteById(id);
    }
}
