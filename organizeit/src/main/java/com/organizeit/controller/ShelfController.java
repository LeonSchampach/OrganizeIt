package com.organizeit.controller;

import com.organizeit.db.dto.DrawerDto;
import com.organizeit.db.dto.ShelfDto;
import com.organizeit.db.entity.Drawer;
import com.organizeit.db.entity.Shelf;
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
 * The ShelfController class handles HTTP requests related to shelf entities within
 * the OrganizeIt application. It provides endpoints for retrieving, creating,
 * updating, and deleting shelves.
 */
@RestController
@RequestMapping("/shelf")
public class ShelfController {

    @Autowired
    private DrawerService drawerService;

    @Autowired
    private ShelfService shelfService;

    /**
     * Retrieves all shelves from the database.
     *
     * @return A ResponseEntity containing a list of books or an appropriate error response.
     */
    @GetMapping("/getAllShelf")
    public ResponseEntity<?> getShelves() {
        try {
            List<Shelf> shelves = shelfService.getAllShelf();
            List<ShelfDto> shelfDtoList = new ArrayList<>();
            for (Shelf shelf : shelves) {
                ShelfDto shelfDto = new ShelfDto(shelf.getId(), shelf.getName(), shelf.getRoom(), shelf.getShelfListId());
                List<Drawer> drawers = shelfService.getDrawersByShelfId(shelf.getId());
                List<DrawerDto> drawerDtoList = new ArrayList<>();
                for (Drawer drawer : drawers) {
                    drawerDtoList.add(new DrawerDto(drawer.getId(), drawer.getName(), drawer.getShelfId()));
                }
                shelfDto.setDrawers(drawerDtoList);
                shelfDtoList.add(shelfDto);
            }
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(shelfDtoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }

    /**
     * Creates a new shelf in the database.
     *
     * @param shelfDto        Dto with all the data of the shelf.
     * @return A ResponseEntity indicating success or an appropriate error response.
     */
    @PostMapping("/createShelf")
    public ResponseEntity<?> createShelf(@RequestBody ShelfDto shelfDto) {
        try{
            Shelf shelf = convertShelfDtoToShelf(shelfDto);

            List<Drawer> drawers = shelfDto.getDrawers().stream()
                    .map(drawerDto -> {
                        Drawer drawer = new Drawer();
                        drawer.setName(drawerDto.getName());
                        return drawer;
                    })
                    .toList();

            try{
                Shelf createdShelf = shelfService.createShelf(shelf);
                List<DrawerDto> createdDrawers = new ArrayList<>();
                for (Drawer drawer : drawers){
                    drawer.setShelfId(createdShelf.getId());
                    Drawer createdDrawer = drawerService.createDrawer(drawer);
                    createdDrawers.add(new DrawerDto(createdDrawer.getId(), createdDrawer.getName(), createdDrawer.getShelfId()));
                }
                ShelfDto responseShelfDto = new ShelfDto(createdShelf.getId(), createdShelf.getName(), createdShelf.getRoom(), createdShelf.getShelfListId());
                responseShelfDto.setDrawers(createdDrawers);

                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(responseShelfDto);
            } catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getInternalServerErrorString());
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }

    /**
     * Updates a shelf from the database.
     *
     * @param shelfDto        Dto with all the data of the shelf.
     * @return A ResponseEntity indicating success or an appropriate error response.
     */
    @PostMapping("/updateShelf")
    public ResponseEntity<?> updateShelf(@RequestBody ShelfDto shelfDto) {
        try{
            Shelf shelf = convertShelfDtoToShelf(shelfDto);
            shelf.setId(shelfDto.getId());
            Shelf createdShelf = shelfService.saveOrUpdate(shelf);

            List<Drawer> drawers = shelfDto.getDrawers().stream()
                    .map(drawerDto -> {
                        Drawer drawer = new Drawer();
                        drawer.setId(drawerDto.getId());
                        drawer.setName(drawerDto.getName());
                        drawer.setShelfId(shelfDto.getId());
                        return drawer;
                    })
                    .toList();

            for (Drawer drawer : drawers){
                drawerService.saveOrUpdate(drawer);
            }

            List<DrawerDto> createdDrawers = new ArrayList<>();
            for (Drawer drawer : drawers){
                drawer.setShelfId(createdShelf.getId());
                Drawer createdDrawer = drawerService.createDrawer(drawer);
                createdDrawers.add(new DrawerDto(createdDrawer.getId(), createdDrawer.getName(), createdDrawer.getShelfId()));
            }

            ShelfDto responseShelfDto = new ShelfDto(createdShelf.getId(), createdShelf.getName(), createdShelf.getRoom(), createdShelf.getShelfListId());
            responseShelfDto.setDrawers(createdDrawers);

            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(responseShelfDto);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }

    /**
     * Deletes a shelf from the database.
     *
     * @param id        Id of the shelf that has to be deleted.
     * @return A ResponseEntity indicating success or an appropriate error response.
     */
    @DeleteMapping("/deleteShelf")
    public ResponseEntity<?> deleteShelf(@RequestParam int id) {
        try {
            String response;
            if (!(response = shelfService.deleteShelf(id)).equals(ErrorMessages.INSTANCE.getInternalServerErrorString())) {
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }

    public Shelf convertShelfDtoToShelf(ShelfDto shelfDto){
        Shelf shelf = new Shelf();
        shelf.setName(shelfDto.getName());
        shelf.setRoom(shelfDto.getRoom());
        shelf.setShelfListId(shelfDto.getShelfListId());
        return shelf;
    }
}
