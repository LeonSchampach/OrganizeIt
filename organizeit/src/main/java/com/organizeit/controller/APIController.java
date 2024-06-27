package com.organizeit.controller;

import com.organizeit.db.entity.Item;
import com.organizeit.db.service.DrawerService;
import com.organizeit.db.service.ItemService;
import com.organizeit.errorhandling.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

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
     * @param name        The name of the item.
     * @param desc        A description for the item.
     * @param drawerName  The name of the Drawer that holds the item.
     * @return A ResponseEntity indicating success or an appropriate error response.
     */
    @PostMapping("/createItem")
    public ResponseEntity<?> createItem(@RequestParam String name, @RequestParam String desc, @RequestParam String drawerName) {
        try {
            String decodeName = URLDecoder.decode(name, StandardCharsets.UTF_8);
            String decodeDesc = URLDecoder.decode(desc, StandardCharsets.UTF_8);
            String decodeDrawerName = URLDecoder.decode(drawerName, StandardCharsets.UTF_8);
            if(!decodeName.isEmpty()){
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(itemService.saveOrUpdate(new Item(decodeName, decodeDesc, drawerService.getDrawerByName(decodeDrawerName))));
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getInternalServerErrorString());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }

    /**
     * Fetches book information from the Google Books API based on the book's ISBN.
     *
     * @param isbn The ISBN of the book for which to fetch information.
     * @return A ResponseEntity containing a BookApiInformations object with the retrieved
     * book data (title, authors, publisher) or an appropriate error response.
     */
    /*@GetMapping("/getBookApiInformationsByIsbn")
    public ResponseEntity<?> getBookApiInformationsByIsbn(@RequestParam String isbn) {
        try {
            String decodeIsbn = URLDecoder.decode(isbn, StandardCharsets.UTF_8);
            URL url = new URL("https://www.googleapis.com/books/v1/volumes?q=isbn:" + decodeIsbn);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line);
                }
                inputStream.close();
                httpURLConnection.disconnect();
                JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
                JsonArray jsonArray = jsonObject.getAsJsonArray("items");
                String selfLink = null;
                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject jsonSingleObject = jsonArray.get(i).getAsJsonObject();
                    if (jsonSingleObject.get("selfLink").getAsString() != null) {
                        selfLink = jsonSingleObject.get("selfLink").getAsString();
                    }
                }
                if (selfLink != null) {
                    url = new URL(selfLink);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        inputStream = httpURLConnection.getInputStream();
                        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        response = new StringBuilder();
                        while ((line = bufferedReader.readLine()) != null) {
                            response.append(line);
                        }
                        inputStream.close();
                        httpURLConnection.disconnect();

                        jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
                        JsonObject bookObject = jsonObject.get("volumeInfo").getAsJsonObject();
                        String title = bookObject.get("title").getAsString();
                        JsonArray jsonAuthors = bookObject.getAsJsonArray("authors");
                        ArrayList<String> authors = new ArrayList<>();
                        for (int i = 0; i < jsonArray.size(); i++) {
                            authors.add(jsonAuthors.get(i).getAsString());
                        }
                        String publisher = bookObject.get("publisher").getAsString();

                        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(new BookApiInformations(title, authors, publisher).toJson());
                    }
                }
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getInternalServerErrorString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }*/
}