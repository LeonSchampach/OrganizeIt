package com.organizeit.db.repository;

import com.organizeit.db.entity.UserShelfList;
import com.organizeit.db.id.UserShelfId;
import org.springframework.data.repository.CrudRepository;

public interface UserShelfListRepository extends CrudRepository<UserShelfList, UserShelfId> {
}
