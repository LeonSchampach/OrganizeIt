package com.organizeit.db.service;

import com.organizeit.db.entity.Drawer;
import com.organizeit.db.entity.Item;
import com.organizeit.db.entity.Shelf;
import com.organizeit.db.repository.ItemRepository;
import com.organizeit.errorhandling.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    @Transactional
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }


    //getting all Item records
    public List<Item> getAllItem(){
        List<Item> items = new ArrayList<Item>();
        itemRepository.findAll().forEach(items::add);
        return items;
    }

    //getting a specific record
    public Item getItemById(int id){
        if(itemRepository.findById(id).isEmpty()){
            return null;
        }
        return itemRepository.findById(id).get();
    }



    //saving/updating a specific record
    public Item saveOrUpdate(Item item){
        return itemRepository.save(item);
    }

    //deleting a specific record
    public String deleteItem(int id){
        if(itemRepository.findById(id).isPresent()){
            itemRepository.deleteById(id);

            return "Item entries deleted!";
        }
        else {
            return ErrorMessages.INSTANCE.getInternalServerErrorString();
        }
    }
}
