package com.organizeit.db.repository;

import com.organizeit.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

/**
 * Provides methods for managing and accessing User entities in the database.
 */
@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Finds a User by their email and password (likely used for login).
     *
     * @param mail     The user's email.
     * @param password The user's password.
     * @return The User entity if found, or null if not.
     */
    User findByMailAndPassword(String mail, String password);

    /**
     * Finds a User by their email address.
     *
     * @param mail The user's email.
     * @return The user entity if found, or null if not.
     */
    User findByMail(String mail);
}
