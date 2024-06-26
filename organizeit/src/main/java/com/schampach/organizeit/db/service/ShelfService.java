package com.schampach.organizeit.db.service;

import com.schampach.organizeit.db.entity.Shelf;
import com.schampach.organizeit.db.repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShelfService {
    @Autowired
    ShelfRepository shelfRepository;

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
