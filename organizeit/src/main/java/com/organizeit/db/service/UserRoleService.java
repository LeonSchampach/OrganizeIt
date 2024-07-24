package com.organizeit.db.service;

import com.organizeit.db.entity.UserRole;
import com.organizeit.db.repository.UserRoleRepository;
import com.organizeit.errorhandling.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides business logic for managing UserRoles, including saving, deleting,
 * retrieving individual roles, and fetching all roles.
 */
@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    /**
     * Saves a new UserRole or updates an existing one in the database.
     *
     * @param userRole The UserRole object to be saved.
     * @return The saved UserRole entity.
     */
    public UserRole saveUserRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    /**
     * Deletes a UserRole from the database by its ID.
     *
     * @param userRoleId The ID of the UserRole to delete.
     * @return A confirmation message if successful, or an error message if the UserRole is not found.
     */
    public String deleteUserRoleById(int userRoleId) {
        if(userRoleRepository.findById(userRoleId).isPresent()) {
            userRoleRepository.deleteById(userRoleId);
            return "Role deleted";
        }
        return ErrorMessages.INSTANCE.getInternalServerErrorString();
    }

    /**
     * Retrieves all UserRoles from the database.
     *
     * @return A List of all UserRole entities.
     */
    public List<UserRole> getAllUserRoles() {
        return userRoleRepository.findAll();
    }

    /**
     * Retrieves a specific UserRole from the database by its ID.
     *
     * @param userRoleId The ID of the UserRole to retrieve.
     * @return The UserRole entity if found, or null if not found.
     */
    public UserRole getUserRoleById(int userRoleId) {
        if(userRoleRepository.findById(userRoleId).isPresent()) {
            return userRoleRepository.findById(userRoleId).get();
        }
        return null;
    }
}
