package com.itskshitizsh.newsapps1.classes;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {


    private static final int RESPONSE_CODE_OK = 200;
    private static final int CONNECTION_TIMEOUT = 15000;
    private static final int READ_TIMEOUT = 10000;
    private static final String KEY_RESPONSE = "response";
    private static final String KEY_RESULTS = "results";
    private static final String KEY_WEBTITLE = "webTitle";
    private static final String KEY_SECTIONNAME = "sectionName";
    private static final String KEY_WEBPUBLICATIONDATE = "webPublicationDate";
    private static final String KEY_WEBURL = "webUrl";
    private static final String KEY_TAGS = "tags";

    private QueryUtils() {
    }

    public static List<News> fetchNewsData(String requestUrl) throws JSONException {
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return extractFeatureFromJson(jsonResponse);
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {

        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(READ_TIMEOUT);
            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == RESPONSE_CODE_OK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("LOG_TAG", "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("LOG_TAG", "Problem retrieving the news JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder outputStr = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                outputStr.append(line);
                line = reader.readLine();
            }
        }
        return outputStr.toString();
    }

    private static List<News> extractFeatureFromJson(String newsJSON) throws JSONException {
        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }
        String Title, category, date, url;
        List<News> newsList = new ArrayList<>();
        JSONObject baseJsonResponse = new JSONObject(newsJSON);
        JSONObject response = baseJsonResponse.getJSONObject(KEY_RESPONSE);
        JSONArray resultsArray = response.getJSONArray(KEY_RESULTS);
        for (int i = 0; i < resultsArray.length(); i++) {
            JSONObject currentResults = resultsArray.getJSONObject(i);
            Title = currentResults.getString(KEY_WEBTITLE);
            category = currentResults.getString(KEY_SECTIONNAME);
            date = currentResults.getString(KEY_WEBPUBLICATIONDATE);
            url = currentResults.getString(KEY_WEBURL);
            JSONArray tagsAuthor = currentResults.getJSONArray(KEY_TAGS);
            String author = "---- ----", authorUrl = "";
            if (tagsAuthor.length() != 0) {
                JSONObject currentTagsAuthor = tagsAuthor.getJSONObject(0);
                author = currentTagsAuthor.optString(KEY_WEBTITLE);
                authorUrl = currentTagsAuthor.getString(KEY_WEBURL);
            }
            date = date.replaceAll("[a-zA-Z]", " ");
            newsList.add(new News(Title, category, date, url, author, authorUrl));
        }
        return newsList;
    }
}