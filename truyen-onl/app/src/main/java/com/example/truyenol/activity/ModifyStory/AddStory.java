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

public class AddStory extends AppCompatActivity {
    private EditText storyTxt,authorTxt,desTxt,chapNumberTxt;
    private TextView linkAvaTxt;
    private Button linkAvaBtn,saveBtn;
    private Spinner typeSpinner;
    private String[] type = {"Tiên Hiệp","Ngôn Tình","Kinh Dị","Huyền Huyễn"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Gán biến View
        setContentView(R.layout.activity_add_story);

        storyTxt=findViewById(R.id.nameStoryTxt2);
        chapNumberTxt=findViewById(R.id.chapterNumberTxt);
        typeSpinner=findViewById(R.id.typeSpinner);
        authorTxt=findViewById(R.id.authorTxt);
        desTxt=findViewById(R.id.desTxt);
        linkAvaTxt=findViewById(R.id.linkTxt1);
        linkAvaBtn=findViewById(R.id.linkAvaBtn);
        saveBtn=findViewById(R.id.saveBtn);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,type);
        typeSpinner.setAdapter(arrayAdapter);
        //Set linkAvaBtn
        linkAvaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSetLinkAva();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStory();
            }
        });
    }
    public void doSetLinkAva(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                | Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        startActivityForResult(intent, 1);

    }
    public void saveStory(){
        Story story=new Story();
        DatabaseHandler db=new DatabaseHandler(getBaseContext());
        story.setAuthor(authorTxt.getText().toString());
        story.setChapters(new ArrayList<>());
        story.setDescription(desTxt.getText().toString());
        story.setType(typeSpinner.getSelectedItem().toString());
        story.setLinkImg(linkAvaTxt.getText().toString());
        story.setNumberChapter(Integer.parseInt(chapNumberTxt.getText().toString()));
        story.setNameStory(storyTxt.getText().toString());
        db.addStory(story);
        db.close();
        Toast.makeText(this,"Save data success!",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, AdminActivity.class);
        finish();
        startActivity(intent);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            getContentResolver().takePersistableUriPermission(data.getData(), Intent.FLAG_GRANT_READ_URI_PERMISSION);
            linkAvaTxt.setText(uri.toString());
        }
    }
}