package com.organizeit.db.repository;

import com.organizeit.db.entity.Drawer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DrawerRepository extends CrudRepository<Drawer, Long> {
    List<Drawer> findDrawersByShelfId(long id);
}
