package com.organizeit.controller;

import com.organizeit.db.entity.UserShelfList;
import com.organizeit.db.id.UserShelfId;
import com.organizeit.db.service.UserShelfListService;
import com.organizeit.errorhandling.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userShelfList")
public class UserShelfListController {

    @Autowired
    private UserShelfListService userShelfListService;

    /**
     * Creates a new ShelfList in the Database.
     *
     * @param userId        Id of the User that will be added to the ShelfList.
     * @param shelfListId   Id of the ShelfList.
     * @return A ResponseEntity indicating success or an appropriate error response.
     */
    @PostMapping("/createUserShelfList")
    public ResponseEntity<?> createUserShelfList(@RequestParam long userId, @RequestParam long shelfListId) {
        try {
            UserShelfList userShelfList = userShelfListService.createUserShelfList(new UserShelfList(new UserShelfId(userId, shelfListId)));
            if(userShelfList != null)
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userShelfList);
            else
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getInternalServerErrorString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }

    @GetMapping("/getAllUserShelfLists")
    public ResponseEntity<?> getAllUserShelfLists(){
        try {
            List<UserShelfList> userShelfLists = userShelfListService.getAllUserShelfList();
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userShelfLists);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }

}
