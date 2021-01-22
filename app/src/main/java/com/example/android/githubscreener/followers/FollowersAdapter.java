package com.example.android.githubscreener.followers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.githubscreener.R;
import com.example.android.githubscreener.followers.Followers;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FollowersAdapter  extends ArrayAdapter<Followers> {

    public FollowersAdapter(@NonNull Context context, List<Followers> news) {
        super(context,0, news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Inflate
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_follower_item,parent, false);
        }//MAjor Step3 : where do i show this ? i getView and inflate it. and add values and extract values


        //fetching reference for showing json
        TextView loginSetView = (TextView) listItemView.findViewById(R.id.login);
        ImageView avatarSetView = (ImageView) listItemView.findViewById(R.id.avatar_imageView);

        //Extract value
        Followers currentFollowers = getItem(position);
        loginSetView.setText(currentFollowers.getmLogin());
        //IMAGE TO BE SET

        Picasso.get()
                .load(currentFollowers.getmAvatarUrl())
                .placeholder(R.drawable.ic_launcher_background)
                //.error(R.drawable.)
                .resize(111,111)
                .into(avatarSetView);
        Log.d("GEtIMAGEURL",currentFollowers.getmAvatarUrl());



        return listItemView;
    }
}
