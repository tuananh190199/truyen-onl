package com.example.truyenol.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.truyenol.R;
import com.example.truyenol.adapter.SearchAdapter;
import com.example.truyenol.adapter.StoryAdapter;
import com.example.truyenol.adapter.StoryListViewAdapter;
import com.example.truyenol.database.DatabaseHandler;
import com.example.truyenol.model.Story;
import com.example.truyenol.model.User;

import java.util.ArrayList;

public class TimKiemActivity extends AppCompatActivity {
    int idUser;
    String fullname, position,email,linkAva;
    ListView listView;
    EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        listView =findViewById(R.id.listviewTimKiem);
        edt = findViewById(R.id.edit_search);
        Button searchButton = (Button)findViewById(R.id.button1);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt.getText().toString();
                DatabaseHandler db = new DatabaseHandler(getBaseContext());
                ArrayList<Story> listStory = new ArrayList<>();
                listStory = db.getStoriesByName(name);
                SearchAdapter searchAdapter = new SearchAdapter(getApplicationContext(),listStory);
                listView.setAdapter(searchAdapter);
            }
        });

    }
}