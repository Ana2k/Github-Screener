package com.example.android.githubscreener.followers;

public class Followers {
    //Major step 1: blueprint for rest of the file
    //check json file to know which datas to retrieve and add them here

    //Declaring variables
    private String mLogin;
    private String mAvatarUrl;
    private String mHtmlUrl;/**...issues if url*/

    public Followers(String login,  String avatarUrl,
                     String htmlUrl){
        mLogin = login;
        mAvatarUrl=avatarUrl;
        mHtmlUrl = htmlUrl;
    }

    public String getmLogin(){
        return mLogin;
    }
    public String getmAvatarUrl(){
        return mAvatarUrl;
    }
    public String getmHtmlUrl(){
        return mHtmlUrl;
    }

}
