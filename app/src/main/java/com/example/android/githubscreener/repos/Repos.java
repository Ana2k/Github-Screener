package com.example.android.githubscreener.repos;

public class Repos {
    //Major step 1: blueprint for rest of the file
    //check json file to know which datas to retrieve and add them here

    //Declaring variables
    private String mRepoName;
    private String mLogin;
    private String mHtmlUrl;
    private String mDescription;
    private String mLanguage;/**...issues if url*/

    public Repos(String repoName, String htmlUrl,String description,String language){
        mRepoName = repoName;
        mHtmlUrl = htmlUrl;
        mDescription = description;
        mLanguage = language;
    }

    public String getmRepoName(){return mRepoName;}
    public String getmHtmlUrl(){
        return mHtmlUrl;
    }
    public String getmDescription(){return mDescription;}
    public String getmLanguage(){return mLanguage;}
}
