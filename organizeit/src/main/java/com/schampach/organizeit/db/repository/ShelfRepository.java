package com.schampach.organizeit.db.repository;

import com.schampach.organizeit.db.entity.Shelf;
import org.springframework.data.repository.CrudRepository;

public interface ShelfRepository extends CrudRepository<Shelf, Integer> {
}
