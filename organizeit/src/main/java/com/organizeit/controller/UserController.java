package com.organizeit.controller;

import com.organizeit.db.dto.LoginDTO;
import com.organizeit.db.dto.UserDTO;
import com.organizeit.db.entity.User;
import com.organizeit.db.service.UserService;
import com.organizeit.errorhandling.ErrorMessages;
import com.organizeit.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * The UserController handles HTTP requests related to user entities within
 * the SmartLibrary application. Provides endpoints for user registration,
 * login, updating user information, deletion, and retrieval of user data.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Deletes a user from the database based on their ID.
     *
     * @param id The ID of the user to delete.
     * @return A ResponseEntity indicating success or an appropriate error response.
     */
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        try {
            String response = userService.deleteUser(Integer.parseInt(id));
            if (!response.equals(ErrorMessages.INSTANCE.getLoginError())) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(response);
            } else {
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }

    /**
     * Registers a new user in the system.
     *
     * @return A ResponseEntity indicating success or an appropriate error response.
     */
    @GetMapping("/register")
    public ResponseEntity<?> registerUser() {
        try {
            User user = userService.saveUser(new User());
            if(user != null)
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(user);
            else
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getInternalServerErrorString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }

    /**
     * Retrieves a list of all users registered in the system.
     *
     * @return A ResponseEntity containing a list of users or an appropriate error response.
     */
    @GetMapping("/getAllUser")
    public ResponseEntity<?> getAllUser() {
        try {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userService.getAllUser());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }
}
