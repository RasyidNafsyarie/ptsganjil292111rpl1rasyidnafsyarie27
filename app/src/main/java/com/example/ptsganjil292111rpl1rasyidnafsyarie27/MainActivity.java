package com.example.ptsganjil292111rpl1rasyidnafsyarie27;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<Model> movielist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Movies");

        recyclerView = findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        movielist = new ArrayList<>();
        adapter = new Adapter(movielist, this);
        recyclerView.setAdapter(adapter);

        getData();
    }

    private void getData() {
        AndroidNetworking.get("https://api.themoviedb.org/3/movie/now_playing?api_key=6ac7a042ac3b7599a689eb943fa0b6d0&language=en-US")
        .build()
        .getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray result = response.getJSONArray("results");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject resultObj = result.getJSONObject(i);

                        String original_title = resultObj.getString("original_title");
                        String overview = resultObj.getString("overview");
                        String release_date = resultObj.getString("release_date");
                        String poster_path = "https://image.tmdb.org/t/p/w500/".concat(resultObj.getString("poster_path"));


                        movielist.add(new Model(i, original_title, overview, release_date, poster_path));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(ANError anError) {
                System.out.println("Hello2");

            }
        });
    }
}