package com.thoughtworks.firstapp.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.thoughtworks.firstapp.R;
import com.thoughtworks.firstapp.model.db.DataManager;
import com.thoughtworks.firstapp.service.DataFetcherService;
import com.thoughtworks.firstapp.util.Constants;
import com.thoughtworks.firstapp.util.LogUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private RecyclerViewAdapter recyclerViewAdapter;

    private BroadcastReceiver successReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            recyclerViewAdapter.setUser(DataManager.loadUserProfile());
            recyclerViewAdapter.setTweets(DataManager.loadTweets());
            recyclerViewAdapter.notifyDataSetChanged();
        }
    };

    private BroadcastReceiver failReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Throwable t = (Throwable) intent.getSerializableExtra(Constants.DATA_FETCH_FAIL_PARAM);
            LogUtils.LOGE("MainActivity", t.getMessage(), t);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();

        registerReceiver(successReceiver, new IntentFilter(Constants.DATA_FETCH_SUCCESS));
        registerReceiver(failReceiver, new IntentFilter(Constants.DATA_FETCH_FAIL));

        fetchData();
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(successReceiver);
        unregisterReceiver(failReceiver);
    }

    private void fetchData() {
        DataFetcherService.startActionFetchData(this);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);
    }




}
