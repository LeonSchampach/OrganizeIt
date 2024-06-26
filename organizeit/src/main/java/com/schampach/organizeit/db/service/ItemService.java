package com.schampach.organizeit.db.service;

import com.schampach.organizeit.db.entity.Item;
import com.schampach.organizeit.db.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    //getting all Item records
    public List<Item> getAllItem(){
        List<Item> items = new ArrayList<Item>();
        itemRepository.findAll().forEach(items::add);
        return items;
    }

    //getting a specific record
    public Item getItemById(Integer id){
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
    public void deleteItem(int id){
        itemRepository.deleteById(id);
    }
}
