package com.example.truyenol.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.truyenol.R;
import com.example.truyenol.adapter.ChapterAdapter;
import com.example.truyenol.database.DatabaseHandler;
import com.example.truyenol.model.Chapter;

import java.util.ArrayList;

public class ChapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_chapter);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int idStory = 0;
        ListView lvChap = (ListView)findViewById(R.id.listChapter);
        TextView nameTxt = (TextView)findViewById(R.id.nameTxt);
        if(bundle!=null){
            nameTxt.setText(bundle.getString("nameStory"));
            idStory = bundle.getInt("idStory");
        }
        DatabaseHandler db = new DatabaseHandler(getBaseContext());
        ArrayList<Chapter> listchapter = db.getChapterByIdStory(idStory);
        ChapterAdapter chapterAdapter = new ChapterAdapter(this,listchapter);
        lvChap.setAdapter(chapterAdapter);
    }
}