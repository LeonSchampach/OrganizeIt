package com.organizeit.db.service;

import com.organizeit.db.entity.ShelfList;
import com.organizeit.db.entity.UserShelfList;
import com.organizeit.db.repository.ShelfListRepository;
import com.organizeit.db.repository.UserShelfListRepository;
import com.organizeit.errorhandling.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShelfListService {
    @Autowired
    ShelfListRepository shelfListRepository;

    @Autowired
    UserShelfListRepository userShelfListRepository;

    public ShelfList createShelfList(ShelfList shelfList) {
        return shelfListRepository.save(shelfList);
    }

    public List<ShelfList> getAllShelfLists(){
        return (List<ShelfList>) shelfListRepository.findAll();
    }

    public List<ShelfList> getAllShelfListsByUserId(long userId) {
        List<ShelfList> allShelfLists = (List<ShelfList>) shelfListRepository.findAll();
        List<UserShelfList> allUserShelfLists = (List<UserShelfList>) userShelfListRepository.findAll();

        List<ShelfList> shelfLists = new ArrayList<>();

        for (UserShelfList userShelfList : allUserShelfLists) {
            if (userId == userShelfList.getUserId()) {
                for (ShelfList shelfList : allShelfLists) {
                    if (shelfList.getId() == userShelfList.getShelfListId()) {
                        shelfLists.add(shelfList);
                    }
                }
            }
        }

        return shelfLists;
    }

    public ShelfList removeShelfListById(long listId) {
        ShelfList shelfList = null;
        if(shelfListRepository.findById(listId).isPresent()){
            shelfList = shelfListRepository.findById(listId).get();
            List<UserShelfList> userShelfLists = userShelfListRepository.findUserShelfListsByShelfListId(listId);
            userShelfListRepository.deleteAll(userShelfLists);

            // TODO: Delete All Shelves, Drawers and Items in this ShelfList

            shelfListRepository.deleteById(listId);
        }

        return shelfList;
    }
}
