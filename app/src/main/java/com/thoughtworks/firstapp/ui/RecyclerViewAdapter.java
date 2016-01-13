package com.thoughtworks.firstapp.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.thoughtworks.firstapp.R;
import com.thoughtworks.firstapp.model.DataManager;
import com.thoughtworks.firstapp.model.Tweet;
import com.thoughtworks.firstapp.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjin on 1/13/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private User user;
    private List<Tweet> tweets;

    public RecyclerViewAdapter(Context context) {
        super();
        try {
            tweets = DataManager.loadTweets(context);
            user = DataManager.loadUser(context);
        } catch (IOException e) {
            tweets = new ArrayList<>();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ItemType.Header.value) {
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_header_layout, parent, false));
        }

        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            bindHeaderViewHolder((HeaderViewHolder) holder);
        } else {
            bindItemViewHolder((ItemViewHolder) holder, position - 1);
        }
    }

    private void bindItemViewHolder(ItemViewHolder holder, int position) {
        holder.bindData(tweets.get(position));
    }

    private void bindHeaderViewHolder(HeaderViewHolder holder) {
        holder.bindData(user);
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? ItemType.Header.value : ItemType.Row.value;
    }

    @Override
    public int getItemCount() {
        return tweets.size() + 1;
    }

    enum ItemType {

        Header(1), Row(2);

        final int value;

        ItemType(int value) {
            this.value = value;
        }
    }
}
