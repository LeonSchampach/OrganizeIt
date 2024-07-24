package com.organizeit.db.service;

import com.organizeit.db.dto.LoginDTO;
import com.organizeit.db.dto.UserDTO;
import com.organizeit.db.entity.User;
import com.organizeit.db.repository.UserRepository;
import com.organizeit.errorhandling.ErrorMessages;
import com.organizeit.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The userService class provides business logic related to managing user
 * accounts, including registration, login, retrieval, and deletion. It interacts
 * with the userRepository for data access and uses PasswordEncoder for secure
 * password handling.
 */
@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    /**
     * Deletes a user from the database and sets the user ID to null
     * for any copies of books they have checked out.
     *
     * @param id The ID of the user to delete.
     * @return A confirmation message if successful, or an error message if the user is not found.
     */
    public String deleteUser(int id) {
        Optional<User> deleteuser = userRepository.findById(id);
        if (deleteuser.isPresent()) {
            userRepository.deleteById(id);
            return "User deleted!";
        } else {
            return ErrorMessages.INSTANCE.getLoginError();
        }
    }

    /**
     * Saves or updates a user in the database.
     *
     * @param user The user entity to be saved.
     * @return The saved user entity (this may include a newly generated ID or updates).
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Retrieves a list of all users from the database.
     *
     * @return A List of all user entities.
     */
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user from the database by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The user entity if found, or null if not found.
     */
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null); // Shorter version with Optional
    }

    /**
     * Registers a new user. Encodes the password before saving.
     *
     * @param userDTO A userDTO object containing the user's registration data.
     * @return A message indicating the success or failure of the registration.
     */
    public User registerUser(UserDTO userDTO) {
        User existingUser = userRepository.findByMail(userDTO.getMail());
        if (existingUser == null) {
            String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
            User user = new User(userDTO.getFirstname(), userDTO.getLastname(), userDTO.getMail(), encodedPassword);
            return userRepository.save(user);
        }
        return null;
    }

    /**
     * Handles user login by comparing the provided credentials with
     * a stored user's email and encoded password.
     *
     * @param loginDTO A LoginDTO object containing the user's email and password.
     * @return A LoginResponse object indicating the success/failure of login and the user's ID (if successful).
     */
    public LoginResponse loginUser(LoginDTO loginDTO) {
        User user = userRepository.findByMail(loginDTO.getMail());
        if (user != null) {
            if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
                return new LoginResponse("Login Success", true, user.getId());
            } else {
                return new LoginResponse("Login Failed", false);
            }
        }
        return new LoginResponse("User does not exist", false);
    }
}
