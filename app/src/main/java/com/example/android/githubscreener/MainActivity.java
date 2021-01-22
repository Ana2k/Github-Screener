package com.example.android.githubscreener;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.githubscreener.followers.FollowersActivity;
import com.example.android.githubscreener.repos.ReposActivity;

public class MainActivity extends AppCompatActivity {
    /**MAjor Step -Last
     * S!: Create Edit Text in activity_main.xml file along with two buttons
     * S2: Button1-> Followers Button 2->Repos activity.
     * S3: Username passed via intent. and extracted in the respective activities
     */
    EditText mUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserName = (EditText) findViewById(R.id.inputUserName);

        Button buttonFollow = (Button) findViewById(R.id.buttonFollowers);
        Button buttonRepos = (Button) findViewById(R.id.buttonRepos);

        buttonFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent followIntent = new Intent(MainActivity.this, FollowersActivity.class);
                followIntent.putExtra("userName",getUserName());
                startActivity(followIntent);
            }
        });

        buttonRepos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reposIntent = new Intent(MainActivity.this, ReposActivity.class);
                reposIntent.putExtra("userName",getUserName());
                startActivity(reposIntent);
            }
        });



    }
    private String getUserName(){
        return mUserName.getText().toString();
    }
}