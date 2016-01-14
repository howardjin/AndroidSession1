package com.thoughtworks.firstapp.model.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hjin on 1/14/16.
 */
public class UserProfile extends RealmObject {

    private User user;

    @PrimaryKey
    private String username;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
