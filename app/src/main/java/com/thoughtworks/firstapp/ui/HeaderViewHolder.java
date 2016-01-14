package com.thoughtworks.firstapp.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thoughtworks.firstapp.R;
import com.thoughtworks.firstapp.model.User;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hjin on 1/13/16.
 */
public class HeaderViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.background)
    ImageView background;

    @Bind(R.id.name)
    TextView lblName;

    @Bind(R.id.avatar)
    ImageView avatar;

    public HeaderViewHolder(View view) {
        super(view);

        ButterKnife.bind(this, view);
    }

    public void bindData(User user) {
        if (user == null) {
            return;
        }

        lblName.setText(user.getNick());

        Context context = itemView.getContext();
        Picasso.with(context).load(user.getProfileImage()).into(background);
        Picasso.with(context).load(user.getAvatar()).into(avatar);
    }
}
