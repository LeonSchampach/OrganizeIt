package com.organizeit.db.repository;

import com.organizeit.db.entity.User;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides methods for managing and accessing User entities in the database.
 */
@EnableJpaRepositories
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
