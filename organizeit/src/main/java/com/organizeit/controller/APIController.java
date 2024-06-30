package com.organizeit.controller;

import com.organizeit.db.dto.DrawerDto;
import com.organizeit.db.dto.ItemDto;
import com.organizeit.db.dto.ShelfDto;
import com.organizeit.db.entity.Drawer;
import com.organizeit.db.entity.Item;
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

import java.util.List;
import java.util.stream.Collectors;

/**
 * The BookController class handles HTTP requests related to book entities within
 * the SmartLibrary application. It provides endpoints for retrieving, creating,
 * updating, and deleting books, as well as fetching book information from the
 * Google Books API.
 */
@RestController
@RequestMapping("/api")
public class APIController {


    @Autowired
    private ItemService itemService;

    @Autowired
    private DrawerService drawerService;

    @Autowired
    private ShelfService shelfService;
    @Autowired
    private DrawerRepository drawerRepository;

    /**
     * Retrieves a book from the database based on its ISBN.
     *
     * @param isbn The ISBN of the book to retrieve.
     * @return A ResponseEntity containing the book information or an appropriate error response.
     */
    /*@GetMapping("/getBook/{isbn}")
    public ResponseEntity<?> getBook(@PathVariable String isbn) {
        try {
            Book book = itemService.getBookByIsbn(isbn);
            if (book != null) {
                return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(book);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getInternalServerErrorString());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }*/

    /**
     * Updates an existing book in the database based on its ISBN.
     * Allows updating the book's title and/or publisher ID.
     *
     * @param isbn        The ISBN of the book to update.
     * @param title       The new title of the book (optional).
     * @param publisherId The new publisher ID of the book (optional).
     * @return A ResponseEntity indicating success or an appropriate error response.
     */
    /*@PostMapping("/updateBookByIsbn/{isbn}")
    public ResponseEntity<?> updateBooks(@PathVariable String isbn, @RequestParam(required = false) String title, @RequestParam(required = false) String publisherId) {
        try {
            Book book = itemService.getBookByIsbn(isbn);
            if (book != null) {
                if (title != null) {
                    String decodeTitle = URLDecoder.decode(title, StandardCharsets.UTF_8);
                    book.setTitle(decodeTitle);
                }
                if (publisherId != null) {
                    String decodePublisherId = URLDecoder.decode(publisherId, StandardCharsets.UTF_8);
                    book.setPublisherId(Integer.parseInt(decodePublisherId));
                }
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(itemService.saveBook(book));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getInternalServerErrorString());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }*/

    /**
     * Deletes a book from the database based on its ISBN.
     *
     * @param isbn The ISBN of the book to delete.
     * @return A ResponseEntity indicating success or an appropriate error response.
     */
    /*@PostMapping("/deleteBook/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
        try {
            String response;
            if (!(response = itemService.deleteBookByIsbn(isbn)).equals(ErrorMessages.INSTANCE.getInternalServerErrorString())) {
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }*/

    /**
     * Retrieves all books from the database.
     *
     * @return A ResponseEntity containing a list of books or an appropriate error response.
     */
    /*@GetMapping("/getAllBook")
    public ResponseEntity<?> getBooks() {
        try {
            List<Book> books = itemService.getAllBook();
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(books);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }*/

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
            item.setDesc(itemDto.getName());
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
     * Creates a new drawer in the database.
     *
     * @param drawerDto        Dto with all the data of the drawer.
     * @return A ResponseEntity indicating success or an appropriate error response.
     */
    @PostMapping("/createDrawer")
    public ResponseEntity<?> createDrawer(@RequestBody DrawerDto drawerDto) {
        try {
            Drawer drawer = new Drawer();
            drawer.setName(drawerDto.getName());
            drawer.setShelfId(drawerDto.getShelfId());

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
                    .collect(Collectors.toList());

            Shelf createdShelf = shelfService.createShelf(shelf);
            for (Drawer drawer : drawers){
                drawer.setShelfId(createdShelf.getId());
                drawerService.createDrawer(drawer);
            }
            if(createdShelf != null){
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(createdShelf);
            }else{
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
            if(createdShelf != null){
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(createdShelf);
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getInternalServerErrorString());
            }
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
    @PostMapping("/deleteShelf")
    public ResponseEntity<?> deleteShelf(@RequestBody int id) {
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
        return shelf;
    }
}