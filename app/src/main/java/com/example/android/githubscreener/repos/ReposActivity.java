package com.example.android.githubscreener.repos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.githubscreener.R;
import com.example.android.githubscreener.followers.Followers;
import com.example.android.githubscreener.followers.FollowersAdapter;
import com.example.android.githubscreener.followers.FollowersQueryUtils;

import java.util.ArrayList;
import java.util.List;

public class ReposActivity extends AppCompatActivity {
     /*Step 1 : creating basic variables
    Step2 : implementing AsyncTask and related methods
    Step3: is creating Adapters---is that thing by which you will be joining things
    * */

    public static final String LOG_TAG="eena";
    public static final String REPOS_URL="https://api.github.com/users/Ana2k/repos";

    //Adapter fr accesing
    private ReposAdapter mReposAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        TextView repoOwner = (TextView) findViewById(R.id.repoOwner);

        ListView reposListView = (ListView) findViewById(R.id.repos_list);

        mReposAdapter = new ReposAdapter(this, new ArrayList<Repos>());
        reposListView.setAdapter(mReposAdapter);

        reposListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Repos currentRepos = mReposAdapter.getItem(position);

                Uri reposUri= Uri.parse(currentRepos.getmHtmlUrl());

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, reposUri);
                startActivity(websiteIntent);
            }
        });
        ReposAsyncTask task = new ReposAsyncTask();
        task.execute(REPOS_URL);
    }

    private class ReposAsyncTask extends AsyncTask<String, Void, List<Repos>> {

        @Override
        protected List<Repos> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Repos> result = ReposQueryUtils.fetchFollowersData(urls[0]);//runnign jsonREsponse in background...calling Queryutils for extraction etc...
            return result;
        }

        @Override
        protected void onPostExecute(List<Repos> data) {
            mReposAdapter.clear();

            if(data!=null && !data.isEmpty()){
                //may be no response or an empty array
                mReposAdapter.addAll(data);
            }
        }
    }
}