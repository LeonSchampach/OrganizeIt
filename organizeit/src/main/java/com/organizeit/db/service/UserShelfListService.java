package com.organizeit.db.service;

import com.organizeit.db.entity.UserShelfList;
import com.organizeit.db.repository.UserShelfListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserShelfListService {
    @Autowired
    UserShelfListRepository userShelfListRepository;

    public UserShelfList createUserShelfList(UserShelfList userShelfList) {
        return userShelfListRepository.save(userShelfList);
    }

    public List<UserShelfList> getAllUserShelfList() {
        List<UserShelfList> userShelfLists = new ArrayList<UserShelfList>();
        userShelfListRepository.findAll().forEach(userShelfLists::add);
        return userShelfLists;
    }
}
