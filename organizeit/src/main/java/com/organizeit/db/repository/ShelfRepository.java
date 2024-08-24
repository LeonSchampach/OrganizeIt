package com.organizeit.db.repository;

import com.organizeit.db.entity.Shelf;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShelfRepository extends CrudRepository<Shelf, Long> {
    List<Shelf> findShelvesByShelfListId(long id);
}
