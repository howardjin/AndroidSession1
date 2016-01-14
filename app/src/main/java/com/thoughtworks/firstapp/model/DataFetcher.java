package com.thoughtworks.firstapp.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by hjin on 1/13/16.
 */
public class DataFetcher {

    public static List<Tweet> fetchTweets(Context context) throws IOException {

        Gson gson = new Gson();
        InputStreamReader reader = new InputStreamReader(context.getAssets().open("tweets.json"));
        return gson.fromJson(reader, new TypeToken<List<Tweet>>(){}.getType());
    }

    public static User fetchUser(Context context) throws IOException {

        Gson gson = new Gson();
        InputStreamReader reader = new InputStreamReader(context.getAssets().open("user.json"));
        return gson.fromJson(reader, User.class);
    }

}
