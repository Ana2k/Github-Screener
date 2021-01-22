package com.example.android.githubscreener.repos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.githubscreener.R;
import com.example.android.githubscreener.followers.Followers;

import java.util.List;

public class ReposAdapter extends ArrayAdapter<Repos> {

    public ReposAdapter(@NonNull Context context, List<Repos> repos) {
        super(context,0, repos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Inflate
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_repos_item,parent, false);
        }//MAjor Step3 : where do i show this ? i getView and inflate it. and add values and extract values

        //Refer
        TextView repoName = (TextView) listItemView.findViewById(R.id.repoName);
        TextView html_url = (TextView) listItemView.findViewById(R.id.repoUrl);
        TextView repoDescription = (TextView) listItemView.findViewById(R.id.repoDescription);
        TextView repoLanguage = (TextView) listItemView.findViewById(R.id.repoLanguage);

        //Extract
        Repos currentRepos = getItem(position);
        repoName.setText(currentRepos.getmRepoName());
        html_url.setText(currentRepos.getmHtmlUrl());
        repoDescription.setText(currentRepos.getmDescription());
        repoLanguage.setText(currentRepos.getmLanguage());

        return listItemView;


    }
}
