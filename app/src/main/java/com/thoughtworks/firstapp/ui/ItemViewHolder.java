package com.thoughtworks.firstapp.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thoughtworks.firstapp.R;
import com.thoughtworks.firstapp.model.Tweet;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hjin on 1/13/16.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.avatar)
    ImageView avatar;

    @Bind(R.id.header)
    TextView lblHeader;

    @Bind(R.id.content)
    TextView lblContent;

    public ItemViewHolder(View view) {
        super(view);

        ButterKnife.bind(this, view);
    }

    public void bindData(Tweet tweet) {
        lblHeader.setText(tweet.getSender().getNick());
        lblContent.setText(tweet.getContent());
        Picasso.with(itemView.getContext()).load(tweet.getSender().getAvatar()).into(avatar);
    }
}
