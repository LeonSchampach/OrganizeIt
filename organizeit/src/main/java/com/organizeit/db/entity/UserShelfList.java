package com.organizeit.db.entity;

import com.organizeit.db.id.UserShelfId;
import jakarta.persistence.*;

@Entity
@Table (name = "REL_USER_LIST")
@IdClass(UserShelfId.class)
public class UserShelfList {
    @Id
    @Column(name = "USER_ID")
    private long userId;

    @Id
    @Column(name = "LIST_ID")
    private long shelfListId;

    public UserShelfList(UserShelfId userShelfId) {
        this.userId = userShelfId.getUserId();
        this.shelfListId = userShelfId.getShelfListId();
    }

    public UserShelfList() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getShelfListId() {
        return shelfListId;
    }

    public void setShelfListId(long shelfListId) {
        this.shelfListId = shelfListId;
    }
}
