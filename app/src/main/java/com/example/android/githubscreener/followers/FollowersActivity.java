package com.example.android.githubscreener.followers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.githubscreener.R;

import java.util.ArrayList;
import java.util.List;

public class FollowersActivity extends AppCompatActivity {
    /*Step 1 : creating basic variables
    Step2 : implementing AsyncTask and related methods
    Step3: is creating Adapters---is that thing by which you will be joining things
    * */

    public static final String LOG_TAG="eena";
    public static final String FOLLOWERS_URL="https://api.github.com/users/Ana2k/followers";

    //Adapter fr accesing
    private FollowersAdapter mFollowersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        ListView followersListView = (ListView) findViewById(R.id.followers_list);

        mFollowersAdapter = new FollowersAdapter(this,new ArrayList<Followers>());
        followersListView.setAdapter(mFollowersAdapter);

        followersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Followers currentfollowers = mFollowersAdapter.getItem(position);

                Uri followersUri= Uri.parse(currentfollowers.getmHtmlUrl());

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, followersUri);
                startActivity(websiteIntent);
            }
        });

        FollowersAsyncTask task = new FollowersAsyncTask();
        task.execute(FOLLOWERS_URL);

    }

    private class FollowersAsyncTask extends AsyncTask<String, Void, List<Followers>>{

        @Override
        protected List<Followers> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Followers> result = FollowersQueryUtils.fetchFollowersData(urls[0]);//runnign jsonREsponse in background...calling Queryutils for extraction etc...
            return result;

        }

        @Override
        protected void onPostExecute(List<Followers> data) {
            mFollowersAdapter.clear();

            if(data!=null && !data.isEmpty()){
                //may be no response or an empty array
                mFollowersAdapter.addAll(data);
            }
        }
    }
}