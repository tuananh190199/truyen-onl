package com.example.truyenol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.truyenol.R;
import com.example.truyenol.fragment.AccountFragment;
import com.example.truyenol.fragment.HomeFragment;
import com.example.truyenol.fragment.RankingFragment;
import com.example.truyenol.model.Story;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class HomeActivity extends AppCompatActivity {
    private ArrayList<Story> stories=new ArrayList<>();
    private String author[] = {"Kentaro Miura","Oda","Akira Toriyama","Fujiko Fujio","Naoki Urasawa"};
    Random au = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getTopStory();
        BottomNavigationView bottomNav = findViewById(R.id.botNavigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.navigation_home);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                    Fragment fragment = null;
                    switch (item.getItemId()){
                        case R.id.navigation_home:
                            fragment = new HomeFragment();
                            break;
                        case R.id.navigation_ranking:
                            fragment = new RankingFragment(stories);
                            break;
                        case R.id.navigation_user:
                            fragment = new AccountFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_selected,
                            fragment).commit();
                    return true;
                }
            };
    public void getTopStory(){
        RequestQueue queue= Volley.newRequestQueue(getBaseContext());
        String url = "https://api.jikan.moe/v3/top/manga/1/manga";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("top");
                            for(int i=0;i<jsonArray.length();i++){
                                int randomIndex = au.nextInt(author.length);
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                Story story=new Story();
                                story.setId(jsonObject.getInt("mal_id"));
                                story.setNameStory(jsonObject.getString("title"));
                                story.setLinkImg(jsonObject.getString("image_url"));
                                story.setType(jsonObject.getString("type"));
                                story.setStatus((jsonObject.getString("end_date")=="null")?true:false);
                                story.setAuthor(author[randomIndex]);
                                stories.add(story);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        queue.add(jsonObjectRequest);
    }

}