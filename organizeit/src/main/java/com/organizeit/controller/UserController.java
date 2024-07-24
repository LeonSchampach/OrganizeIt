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
     * @param userDTO A userDTO object containing the user's details.
     * @return A ResponseEntity indicating success or an appropriate error response.
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        try {
            User user = userService.registerUser(userDTO);
            if(user != null)
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(user);
            else
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getInternalServerErrorString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }

    /**
     * Handles user login and authentication.
     *
     * @param loginDTO A LoginDTO object containing the user's login credentials.
     * @return A ResponseEntity with a LoginResponse indicating success, failure, or an appropriate error.
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        try {
            LoginResponse loginResponse = userService.loginUser(loginDTO);
            if (loginResponse.getStatus()) {
                return ResponseEntity.ok(loginResponse);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON).body(loginResponse);
            }
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

    /**
     * Updates an existing user's profile information.
     *
     * @param id        The ID of the user to update.
     * @param firstName The updated first name (optional).
     * @param lastName  The updated last name (optional).
     * @param mail      The updated email address (optional).
     * @return A ResponseEntity containing the updated user information or an appropriate error response.
     */
    @PostMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName, @RequestParam(required = false) String mail) {
        try {
            User user = userService.getUserById(Integer.parseInt(id));
            if (user != null) {
                if (firstName != null) {
                    String decodeFirstName = URLDecoder.decode(firstName, StandardCharsets.UTF_8);
                    user.setFirstname(decodeFirstName);
                }
                if (lastName != null) {
                    String decodeLastname = URLDecoder.decode(lastName, StandardCharsets.UTF_8);
                    user.setLastname(decodeLastname);
                }
                if (mail != null) {
                    String decodeMail = URLDecoder.decode(mail, StandardCharsets.UTF_8);
                    user.setMail(decodeMail);
                }
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userService.saveUser(user));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body("Bad request!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }
}
