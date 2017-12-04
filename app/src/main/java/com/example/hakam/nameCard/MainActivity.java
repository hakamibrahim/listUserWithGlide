package com.example.hakam.nameCard;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.hakam.namecard.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);

        Uri builtUri = Uri.parse("http://wilik.id/iak").buildUpon().build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new nameCard().execute(url);
    }

    public class nameCard extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String nameCardResults = null;
            try {
                nameCardResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return nameCardResults;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String nameCardSearchResults) {
            progressBar.setVisibility(View.GONE);
            if (nameCardSearchResults != null && !nameCardSearchResults.equals("")) {
                try {
                    JSONObject object = new JSONObject(nameCardSearchResults);
                    JSONArray userArray = object.getJSONArray("users");
                    List<User> userList = new ArrayList<>();
                    for(int i = 0; i<userArray.length(); i++){
                        JSONObject userObject = userArray.getJSONObject(i);
                        String photo = userObject.getString("photo");
                        String name = userObject.getString("name");
                        int age = userObject.getInt("age");
                        User users = new User(photo,name,age);
                        userList.add(users);
                    }
                    setupRecyclerView(userList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setupRecyclerView(List<User> userList) {
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        UserAdapter mAdapter = new UserAdapter(userList, this);
        mRecyclerView.setAdapter(mAdapter);
    }
}
