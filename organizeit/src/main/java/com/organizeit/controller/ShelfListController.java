package com.organizeit.controller;

import com.organizeit.db.dto.DrawerDto;
import com.organizeit.db.dto.ShelfDto;
import com.organizeit.db.dto.UserDTO;
import com.organizeit.db.entity.Drawer;
import com.organizeit.db.entity.Shelf;
import com.organizeit.db.entity.ShelfList;
import com.organizeit.db.service.ShelfListService;
import com.organizeit.errorhandling.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shelfList")
public class ShelfListController {

    @Autowired
    private ShelfListService shelfListService;

    /**
     * Retrieves all shelf_lists from the database.
     *
     * @return A ResponseEntity containing a list of books or an appropriate error response.
     */
    @GetMapping("/getAllShelfList")
    public ResponseEntity<?> getShelfList() {
        try {
            List<ShelfList> shelfLists = shelfListService.getAllShelfLists();
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(shelfLists);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }

    /**
     * Retrieves all shelf_lists for a specified user from the database.
     *
     * @param userId The id of the user.
     * @return A ResponseEntity containing a list of books or an appropriate error response.
     */
    @GetMapping("/getAllShelfListsByUserId")
    public ResponseEntity<?> getShelfLists(@RequestParam int userId) {
        try {
            List<ShelfList> shelfLists = shelfListService.getAllShelfListsByUserId(userId);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(shelfLists);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }

    /**
     * Creates a new ShelfList in the Database.
     *
     * @param name The name of the new ShelfList.
     * @return A ResponseEntity indicating success or an appropriate error response.
     */
    @PostMapping("/createShelfList")
    public ResponseEntity<?> createShelfList(@RequestParam String name) {
        try {
            ShelfList shelfList = shelfListService.createShelfList(new ShelfList(name));
            if(shelfList != null)
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(shelfList);
            else
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getInternalServerErrorString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }
}
