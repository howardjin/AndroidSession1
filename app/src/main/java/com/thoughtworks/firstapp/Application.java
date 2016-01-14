package com.thoughtworks.firstapp;

import android.content.Context;

/**
 * Created by hjin on 1/14/16.
 */
public class Application extends android.app.Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
