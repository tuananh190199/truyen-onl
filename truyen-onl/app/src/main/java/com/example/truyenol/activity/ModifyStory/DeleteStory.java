package com.example.truyenol.activity.ModifyStory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.truyenol.R;
import com.example.truyenol.database.DatabaseHandler;

public class DeleteStory extends AppCompatActivity {
    private TextView text;
    private Button yesBtn,noBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_story);
        text = findViewById(R.id.textView2);
        yesBtn = findViewById(R.id.yesBtn);
        noBtn = findViewById(R.id.noBtn);
        Intent intent = this.getIntent();
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler db= new DatabaseHandler(getBaseContext());
                db.deleteStory(Integer.parseInt(intent.getStringExtra("id")));
                Toast.makeText(DeleteStory.this,"Xóa truyện thành công",Toast.LENGTH_SHORT).show();
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DeleteStory.this, AdminActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}