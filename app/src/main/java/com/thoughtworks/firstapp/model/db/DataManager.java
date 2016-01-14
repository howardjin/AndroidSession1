package com.thoughtworks.firstapp.model.db;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.thoughtworks.firstapp.Application;

import java.util.List;

import io.realm.Realm;

/**
 * Created by hjin on 1/14/16.
 */
public class DataManager {

    public static void saveTweets(List<com.thoughtworks.firstapp.model.Tweet> tweets) {
        Realm realm = Realm.getInstance(Application.context);

        realm.beginTransaction();
        realm.allObjects(Tweet.class).clear();

        for (com.thoughtworks.firstapp.model.Tweet tweet : tweets) {
            realm.copyToRealmOrUpdate(tweet.toTweet());
        }

        realm.commitTransaction();
    }

    public static void saveUserProfile(com.thoughtworks.firstapp.model.User user) {
        Realm realm = Realm.getInstance(Application.context);

        realm.beginTransaction();

        UserProfile profile = new UserProfile();
        profile.setUser(user.toUser());
        profile.setUsername(user.getUsername());
        realm.copyToRealmOrUpdate(profile);
        realm.commitTransaction();
    }

    public static List<com.thoughtworks.firstapp.model.Tweet> loadTweets() {
        List<Tweet> dbTweets = Realm.getInstance(Application.context).allObjects(Tweet.class);

        return Lists.transform(dbTweets, new Function<Tweet, com.thoughtworks.firstapp.model.Tweet>() {

            @Override
            public com.thoughtworks.firstapp.model.Tweet apply(Tweet input) {
                return com.thoughtworks.firstapp.model.Tweet.fromTweet(input);
            }
        });
    }

    public static com.thoughtworks.firstapp.model.User loadUserProfile() {
        User dbUser = Realm.getInstance(Application.context).allObjects(UserProfile.class).first().getUser();
        com.thoughtworks.firstapp.model.User user = new com.thoughtworks.firstapp.model.User();
        user.setUsername(dbUser.getUsername());
        user.setAvatar(dbUser.getAvatarURL());
        user.setNick(dbUser.getNickname());
        user.setProfileImage(dbUser.getProfileImageURL());
        return user;
    }
}
