package com.organizeit.db.entity;

import com.organizeit.db.id.UserShelfId;
import jakarta.persistence.*;

@Entity
@Table (name = "REL_USER_LIST")
@IdClass(UserShelfId.class)
public class UserShelfList {
    @Id
    @Column(name = "USER_ID")
    private int userId;

    @Id
    @Column(name = "LIST_ID")
    private int shelfListId;

    public UserShelfList(UserShelfId userShelfId) {
        this.userId = userShelfId.getUserId();
        this.shelfListId = userShelfId.getShelfListId();
    }

    public UserShelfList() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShelfListId() {
        return shelfListId;
    }

    public void setShelfListId(int shelfListId) {
        this.shelfListId = shelfListId;
    }
}
