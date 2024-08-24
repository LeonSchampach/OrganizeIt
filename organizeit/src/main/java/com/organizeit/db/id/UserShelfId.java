package com.organizeit.db.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class UserShelfId implements Serializable {

    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "LIST_ID")
    private long shelfListId;

    public UserShelfId(long userId, long shelfListId) {
        this.userId = userId;
        this.shelfListId = shelfListId;
    }

    public UserShelfId() {
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
