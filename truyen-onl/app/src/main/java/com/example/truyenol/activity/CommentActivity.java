package com.example.truyenol.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.truyenol.R;
import com.example.truyenol.adapter.CommentAdapter;
import com.example.truyenol.database.DatabaseHandler;
import com.example.truyenol.model.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int idStory=0;
        List<Comment> listComment = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            idStory = bundle.getInt("idStory");
        }
        ListView listCmt = (ListView) findViewById(R.id.listComment);
        DatabaseHandler db = new DatabaseHandler(getBaseContext());
        listComment = db.getCommnet(idStory);
        CommentAdapter commentAdapter = new CommentAdapter(this,listComment);
        listCmt.setAdapter(commentAdapter);
    }
}