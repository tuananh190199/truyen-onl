package com.example.truyenol.activity.ModifyStory;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.truyenol.R;
import com.example.truyenol.database.DatabaseHandler;
import com.example.truyenol.model.Story;

import java.util.ArrayList;

public class ModifyStory extends AppCompatActivity {
    private EditText nameStoryTxt, desTxt, authorTxt, chapterNumberTxt;
    private TextView linkTxt;
    private Button linkImg, saveBtn;
    private Story story;
    private Spinner typeSpinner;
    private ArrayAdapter arrayAdapter;
    private String[] type = {"Tiên Hiệp","Ngôn Tình","Kinh Dị","Huyền Huyễn"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_story);
        linkTxt = findViewById(R.id.linkTxt);
        typeSpinner = findViewById(R.id.typeSpinner);
        nameStoryTxt = findViewById(R.id.nameStoryTxt2);
        desTxt = findViewById(R.id.desTxt);
        authorTxt = findViewById(R.id.authorTxt);
        chapterNumberTxt = findViewById(R.id.chapterNumberTxt);
        linkImg = findViewById(R.id.linkAvaBtn);
        saveBtn = findViewById(R.id.saveBtn);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,type);
        typeSpinner.setAdapter(arrayAdapter);
        getStory();
        linkImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageLink();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStory();
            }
        });
    }

    public void getImageLink() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                | Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        startActivityForResult(intent, 1);
    }

    public void saveStory() {
        DatabaseHandler db = new DatabaseHandler(getBaseContext());
        story.setAuthor(authorTxt.getText().toString());
        story.setChapters(new ArrayList<>());
        story.setDescription(desTxt.getText().toString());
        story.setType(typeSpinner.getSelectedItem().toString());
        story.setLinkImg(linkTxt.getText().toString());
        story.setNumberChapter(Integer.parseInt(chapterNumberTxt.getText().toString()));
        story.setNameStory(nameStoryTxt.getText().toString());
        db.updateStory(story);
        db.close();
        Toast.makeText(this, "Save data success!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AdminActivity.class);
        finish();
        startActivity(intent);
    }

    public void getStory() {
        Intent intent = this.getIntent();
        DatabaseHandler db = new DatabaseHandler(getBaseContext());
        story = db.getStoriesById(Integer.parseInt(intent.getStringExtra("id")));
        typeSpinner.setSelection(arrayAdapter.getPosition(story.getType()));
        nameStoryTxt.setText(story.getNameStory());
        desTxt.setText(story.getDescription());
        authorTxt.setText(story.getAuthor());
        chapterNumberTxt.setText(String.valueOf(story.getNumberChapter()));
        linkTxt.setText(story.getLinkImg());
        db.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri uri=data.getData();
            getContentResolver().takePersistableUriPermission(data.getData(),Intent.FLAG_GRANT_READ_URI_PERMISSION);
            linkTxt.setText(uri.toString());
        }
    }
}