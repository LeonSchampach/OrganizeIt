package com.organizeit.controller;

import com.organizeit.db.entity.UserRole;
import com.organizeit.db.service.UserRoleService;
import com.organizeit.errorhandling.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

/**
 *  The UserRoleController handles HTTP requests related to user roles within
 *  the SmartLibrary application. Provides endpoints for deleting, retrieving,
 *  and creating user roles.
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    UserRoleService userRoleService;

    /**
     * Deletes a user role from the system based on its ID.
     *
     * @param id The ID of the user role to delete.
     * @return A ResponseEntity indicating success or an appropriate error response.
     */
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserRole(@PathVariable String id) {
        try {
            String response = userRoleService.deleteUserRoleById(Integer.parseInt(id));
            if (!response.equals(ErrorMessages.INSTANCE.getInternalServerErrorString())) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(response);
            } else {
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }

    /**
     * Fetches a list of all user roles defined in the system.
     *
     * @return A ResponseEntity containing a list of UserRole objects or an appropriate error response.
     */
    @GetMapping("/getAllUserRole")
    public ResponseEntity<?> getAllUserRole() {
        try {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userRoleService.getAllUserRoles());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }

    /**
     * Creates a new user role within the system.
     *
     * @param identifier  A unique identifier for the user role.
     * @param description A textual description of the role's purpose.
     * @return A ResponseEntity containing the created UserRole or an appropriate error response.
     */
    @PostMapping("/createUserRole")
    public ResponseEntity<?> createUserRole(@RequestParam String identifier, @RequestParam String description) {
        try {
            String decodeDescription = java.net.URLDecoder.decode(description, StandardCharsets.UTF_8);
            String decodeIdentifier = java.net.URLDecoder.decode(identifier, StandardCharsets.UTF_8);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userRoleService.saveUserRole(new UserRole(decodeIdentifier, decodeDescription)));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(ErrorMessages.INSTANCE.getTryCatchErrorString());
        }
    }
}
