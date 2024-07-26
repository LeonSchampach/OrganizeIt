package com.organizeit.db.service;

import com.organizeit.db.entity.ShelfList;
import com.organizeit.db.repository.ShelfListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShelfListService {
    @Autowired
    ShelfListRepository shelfListRepository;

    public ShelfList createShelfList(ShelfList shelfList) {
        return shelfListRepository.save(shelfList);
    }
}
