package com.organizeit.db.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class UserShelfId implements Serializable {

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "LIST_ID")
    private int shelfListId;

    public UserShelfId(int userId, int shelfListId) {
        this.userId = userId;
        this.shelfListId = shelfListId;
    }

    public UserShelfId() {
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
