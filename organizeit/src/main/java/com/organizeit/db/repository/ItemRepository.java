package com.organizeit.db.repository;

import com.organizeit.db.entity.Drawer;
import com.organizeit.db.entity.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Integer> {
    List<Item> findItemsByDrawerId(int id);
}
