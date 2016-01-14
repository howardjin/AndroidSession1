package com.thoughtworks.firstapp.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.thoughtworks.firstapp.model.DataFetcher;
import com.thoughtworks.firstapp.model.db.DataManager;
import com.thoughtworks.firstapp.util.Constants;

import java.io.IOException;

public class DataFetcherService extends IntentService {

    private static final String ACTION_FEATH_DATA = "com.thoughtworks.firstapp.action.FEATH_DATA";

    public DataFetcherService() {
        super("DataFetcherService");
    }

    public static void startActionFetchData(Context context) {
        Intent intent = new Intent(context, DataFetcherService.class);
        intent.setAction(ACTION_FEATH_DATA);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null) return;

        final String action = intent.getAction();

        if (ACTION_FEATH_DATA.equals(action)) {
            handleActionFetchData();
        }
    }

    private void handleActionFetchData() {

        try {
            DataManager.saveTweets(DataFetcher.fetchTweets(this));
            DataManager.saveUserProfile(DataFetcher.fetchUser(this));
            onDataFetchSuccess();
        } catch (IOException e) {
            onDataFetchFailed(e);
        }
    }

    private void onDataFetchFailed(IOException e) {
        Intent intent = new Intent(Constants.DATA_FETCH_FAIL);
        intent.putExtra(Constants.DATA_FETCH_FAIL_PARAM, e);
        sendBroadcast(intent);
    }

    private void onDataFetchSuccess() {
        sendBroadcast(new Intent(Constants.DATA_FETCH_SUCCESS));
    }

}
