package com.schampach.organizeit.db.repository;

import com.schampach.organizeit.db.entity.Drawer;
import com.schampach.organizeit.db.entity.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Integer> {
    List<Item> findByDrawer(Drawer drawer);
}
