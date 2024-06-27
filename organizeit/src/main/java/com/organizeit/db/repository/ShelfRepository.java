package com.organizeit.db.repository;

import com.organizeit.db.entity.Shelf;
import org.springframework.data.repository.CrudRepository;

public interface ShelfRepository extends CrudRepository<Shelf, Integer> {
}
