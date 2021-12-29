package com.example.truyenol.activity.ModifyStory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.truyenol.R;
import com.example.truyenol.adapter.StoryListViewAdapter;
import com.example.truyenol.database.DatabaseHandler;
import com.example.truyenol.model.Story;

import java.util.ArrayList;

public class SearchStory extends AppCompatActivity {
    private Button searchBtn;
    private EditText typeNameTxt;
    private ListView listView;
    private ArrayList<Story> storyList;
    private StoryListViewAdapter storyListViewAdapter;
    private int act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_story);
        searchBtn = findViewById(R.id.searchBtn);
        typeNameTxt = findViewById(R.id.typeNameTxt);
        listView = findViewById(R.id.listView);
        storyList = new ArrayList<>();
        Intent intent=SearchStory.this.getIntent();
        act=Integer.parseInt(intent.getStringExtra("act"));
        storyListViewAdapter = new StoryListViewAdapter(storyList);
        listView.setAdapter(storyListViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getChapterActivity(position);
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchStories();

            }
        });
    }
        public void getChapterActivity(int position){
            Intent intent;
            if(act==1)
                intent = new Intent(this,ModifyStory.class);
            else if(act==2)
                intent = new Intent(this,DeleteStory.class);
            else
                intent = new Intent(this,ModifyChapter.class);
            intent.putExtra("id",String.valueOf(storyList.get(position).getId()));
            intent.putExtra("chapterNumber",String.valueOf(storyList.get(position).getNumberChapter()));
            finish();
            startActivity(intent);
        }
        public void searchStories(){
            DatabaseHandler db=new DatabaseHandler(getBaseContext());
            storyList=db.getStoriesByName(typeNameTxt.getText().toString());
            storyListViewAdapter=new StoryListViewAdapter(storyList);
            listView.setAdapter(storyListViewAdapter);
        }
}