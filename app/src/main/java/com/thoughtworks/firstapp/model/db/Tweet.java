package com.thoughtworks.firstapp.model.db;

import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Tweet extends RealmObject {

    @PrimaryKey
    private String ID;

    private String content;

    private RealmList<Image> imageURLs;

    private User sender;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public RealmList<Image> getImageURLs() {
        return imageURLs;
    }

    public void setImageURLs(RealmList<Image> imageURLs) {
        this.imageURLs = imageURLs;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Tweet() {
        super();
        ID = UUID.randomUUID().toString();
    }

}
