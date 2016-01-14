
package com.thoughtworks.firstapp.model;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

public class Tweet {

    @Expose
    private String content;
    @Expose
    private List<Image> images = new ArrayList<Image>();
    @Expose
    private User sender;

    /**
     * 
     * @return
     *     The content
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     * @return
     *     The images
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * 
     * @param images
     *     The images
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }

    /**
     * 
     * @return
     *     The sender
     */
    public User getSender() {
        return sender;
    }

    /**
     * 
     * @param sender
     *     The sender
     */
    public void setSender(User sender) {
        this.sender = sender;
    }

    public static Tweet fromTweet(com.thoughtworks.firstapp.model.db.Tweet dbTweet) {

        Tweet tweet = new Tweet();
        tweet.setSender(User.fromUser(dbTweet.getSender()));
        tweet.setContent(dbTweet.getContent());

        tweet.setImages(Lists.transform(dbTweet.getImageURLs(), new Function<com.thoughtworks.firstapp.model.db.Image, Image>() {
            @Override
            public com.thoughtworks.firstapp.model.Image apply(com.thoughtworks.firstapp.model.db.Image input) {
                return Image.fromImage(input);
            }
        }));

        return tweet;
    }

    public com.thoughtworks.firstapp.model.db.Tweet toTweet() {

        com.thoughtworks.firstapp.model.db.Tweet dbTweet = new com.thoughtworks.firstapp.model.db.Tweet();

        dbTweet.setContent(content);
        dbTweet.setSender(sender.toUser());

        RealmList<com.thoughtworks.firstapp.model.db.Image> imageUrls = new RealmList<>();
        for (com.thoughtworks.firstapp.model.Image image: images) {
            imageUrls.add(image.toImage());
        }
        dbTweet.setImageURLs(imageUrls);

        return dbTweet;
    }

}
