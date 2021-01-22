package com.example.android.githubscreener.repos;

import android.text.TextUtils;
import android.util.Log;

import com.example.android.githubscreener.followers.Followers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class ReposQueryUtils {
    /*
   Step1: createUrl
   makehttpRequest_>
                   openconnection , wait, get Data using readfrom Stream(character stram changed to string)
                   jsonresponse returned
   extractfeaturesfrom JSON->
                    read that file, accordingly, extract rqd data in arraylist
                    return the smaller arraylist extracted.
                    ie.the data
   */
    private ReposQueryUtils() {
    }//empty const

    public static String LOG_TAG = "eena";

    public static List<Repos> fetchFollowersData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {

            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<Repos> repos = extractFeatureFromJson(jsonResponse);

        //Return the list of earthquakes
        return repos;

    }

    /*Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {

        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpsURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }


        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the news JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null)
                inputStream.close();/**ioexception can be thrown so android asks to add method*/
        }
        return jsonResponse;
    }

    /* Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));//
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();

            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return a list of {@link Followers} objects that has been built up from
     * parsing the given JSON response.
     */
    private static List<Repos> extractFeatureFromJson(String reposJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(reposJSON)) {
            return null;
        }

        List<Repos> repos = new ArrayList<>();

        try {

            // Create a JSONObject from the JSON response string
            JSONArray baseJsonResponse = new JSONArray(reposJSON);
            Log.d(LOG_TAG, baseJsonResponse.toString());

            // For each followers
            for (int i = 0; i < baseJsonResponse.length(); i++) {

                //Json
                JSONObject currentFollower = baseJsonResponse.getJSONObject(i);
                Log.d(LOG_TAG,currentFollower.toString());

                // Extract the value for the key
                String repoName = currentFollower.getString("name");

                String html_url = currentFollower.getString("html_url");

                String description = currentFollower.getString("description");

                String language = currentFollower.getString("language");




                // Create a new {@link Earthquake} object with the magnitude, location, time,
                // and url from the JSON response.
                Repos repoArr = new Repos(repoName, html_url,description,language );

                // Add the new {@link Earthquake} to the list of earthquakes.
                repos.add(repoArr);//Arraylist mei added
            }
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
        return repos;

    }
}
