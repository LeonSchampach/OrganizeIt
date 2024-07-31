package com.organizeit.controller;

import com.organizeit.db.dto.ItemDto;
import com.organizeit.db.entity.Item;
import com.organizeit.db.repository.DrawerRepository;
import com.organizeit.db.service.DrawerService;
import com.organizeit.db.service.ItemService;
import com.organizeit.db.service.ShelfService;
import com.organizeit.errorhandling.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The ItemController class handles HTTP requests related to item entities within
 * the OrganizeIt application. It provides endpoints for retrieving, creating,
 * updating, and deleting items.
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private DrawerService drawerService;

    @Autowired
    private ShelfService shelfService;

    @Autowired
    private DrawerRepository drawerRepository;

    /**
     * Retrieves all items from the database.
     *
     * @param drawerId          Id of the drawer whose items should be returned
     * @return A ResponseEntity containing a list of books or an appropriate error response.
     */
    @GetMapping("/getItemsByDrawerId")
    public ResponseEntity<?> getItems(@RequestParam int drawerId) {
        try {
            List<Item> items = drawerService.getItemsByDrawerId(drawerId);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(items);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }

    /**
     * Creates a new item in the database.
     *
     * @param itemDto        Dto with all the data of the item.
     * @return A ResponseEntity indicating success or an appropriate error response.
     */
    @PostMapping("/createItem")
    public ResponseEntity<?> createItem(@RequestBody ItemDto itemDto) {
        try {
            Item item = new Item();
            item.setName(itemDto.getName());
            item.setDesc(itemDto.getDesc());
            item.setQuantity(itemDto.getQuantity());
            item.setDrawerId(itemDto.getDrawerId());

            Item createdItem = itemService.createItem(item);
            if(createdItem != null){
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(createdItem);
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getInternalServerErrorString());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }

    /**
     * Updates a item from the database.
     *
     * @param itemDto        Dto with all the data of the item.
     * @return A ResponseEntity indicating success or an appropriate error response.
     */
    @PostMapping("/updateItem")
    public ResponseEntity<?> updateItem(@RequestBody ItemDto itemDto) {
        try{
            Item item = convertItemDtoToItem(itemDto);
            item.setId(itemDto.getId());
            Item createdItem = itemService.saveOrUpdate(item);
            if(createdItem != null){
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(createdItem);
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getInternalServerErrorString());
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }

    /**
     * Deletes a item from the database.
     *
     * @param id        Id of the item that has to be deleted.
     * @return A ResponseEntity indicating success or an appropriate error response.
     */
    @DeleteMapping("/deleteItem")
    public ResponseEntity<?> deleteItem(@RequestParam int id) {
        try {
            String response;
            if (!(response = itemService.deleteItem(id)).equals(ErrorMessages.INSTANCE.getInternalServerErrorString())) {
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }

    public Item convertItemDtoToItem(ItemDto itemDto){
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setDesc(itemDto.getDesc());
        item.setQuantity(itemDto.getQuantity());
        item.setDrawerId(itemDto.getDrawerId());
        return item;
    }
}
