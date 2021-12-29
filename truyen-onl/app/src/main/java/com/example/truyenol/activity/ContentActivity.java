package com.example.truyenol.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.truyenol.R;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Intent intent =getIntent();
        Bundle bundle = intent.getExtras();
        TextView nameChapTxt2 = (TextView)findViewById(R.id.nameChapTxt2);
        TextView content2 =(TextView)findViewById(R.id.contentTxt2);

        nameChapTxt2.setText(bundle.getString("nameChapter"));
        content2.setText(bundle.getString("content"));
    }
}