package com.organizeit.controller;

import com.organizeit.db.dto.DrawerDto;
import com.organizeit.db.dto.ItemDto;
import com.organizeit.db.entity.Drawer;
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

import java.util.ArrayList;
import java.util.List;

/**
 * The DrawerController class handles HTTP requests related to drawer entities within
 * the OrganizeIt application. It provides endpoints for retrieving, creating,
 * updating, and deleting drawers.
 */
@RestController
@RequestMapping("/drawer")
public class DrawerController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private DrawerService drawerService;

    @Autowired
    private ShelfService shelfService;

    @Autowired
    private DrawerRepository drawerRepository;

    /**
     * Retrieves all drawers from the database.
     *
     * @return A ResponseEntity containing a list of books or an appropriate error response.
     */
    @GetMapping("/getAllDrawer")
    public ResponseEntity<?> getDrawers() {
        try {
            List<Drawer> drawers = drawerService.getAllDrawer();
            List<DrawerDto> drawerDtos = new ArrayList<>();
            for (Drawer drawer : drawers) {
                DrawerDto drawerDto = new DrawerDto(drawer.getId(), drawer.getName(), drawer.getOrder(), drawer.getShelfId());

                List<Item> items = drawerService.getItemsByDrawerId(drawer.getId());
                List<ItemDto> itemDtoList = new ArrayList<>();
                if (items != null) {
                    for (Item item: items) {
                        itemDtoList.add(new ItemDto(item.getId(), item.getName(), item.getDesc(), item.getQuantity(), item.getDrawerId()));
                    }
                    drawerDto.setItems(itemDtoList);
                }

                drawerDtos.add(drawerDto);
            }
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(drawerDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }

    /**
     * Creates a new drawer in the database.
     *
     * @param drawerDto        Dto with all the data of the drawer.
     * @return A ResponseEntity indicating success or an appropriate error response.
     */
    @PostMapping("/createDrawer")
    public ResponseEntity<?> createDrawer(@RequestBody DrawerDto drawerDto) {
        try {
            Drawer drawer = convertDrawerDtoToDrawer(drawerDto);
            Drawer createdDrawer = drawerService.createDrawer(drawer);
            if(createdDrawer != null){
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(createdDrawer);
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getInternalServerErrorString());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }

    /**
     * Updates a drawer in the database.
     *
     * @param drawerDto        Dto with all the data of the drawer.
     * @return A ResponseEntity indicating success or an appropriate error response.
     */
    @PostMapping("/updateDrawer")
    public ResponseEntity<?> updateDrawerName(@RequestBody DrawerDto drawerDto) {
        try{
            Drawer drawer = convertDrawerDtoToDrawer(drawerDto);
            drawer.setId(drawerDto.getId());
            Drawer createdDrawer = drawerService.saveOrUpdate(drawer);
            if(createdDrawer != null){
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(createdDrawer);
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getInternalServerErrorString());
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }

    /**
     * Deletes a drawer from the database.
     *
     * @param id        Id of the drawer that has to be deleted.
     * @return A ResponseEntity indicating success or an appropriate error response.
     */
    @DeleteMapping("/deleteDrawer")
    public ResponseEntity<?> deleteDrawer(@RequestParam int id) {
        try {
            String response;
            if (!(response = drawerService.deleteDrawer(id)).equals(ErrorMessages.INSTANCE.getInternalServerErrorString())) {
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }

    public Drawer convertDrawerDtoToDrawer(DrawerDto drawerDto){
        Drawer drawer = new Drawer();
        drawer.setName(drawerDto.getName());
        drawer.setShelfId(drawerDto.getShelfId());
        return drawer;
    }
}
