package com.example.truyenol.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.truyenol.R;
import com.example.truyenol.database.DatabaseHandler;
import com.example.truyenol.model.Comment;
import com.example.truyenol.model.User;

public class InfoStoryActivity extends AppCompatActivity {
    int idStory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infostory);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ImageView imgStory = (ImageView)findViewById(R.id.storyInfoImg);
        TextView nameStoryTxt = (TextView)findViewById(R.id.nameTxt);
        TextView authorTxt = (TextView)findViewById(R.id.authorTxt);
        TextView statusTxt = (TextView)findViewById(R.id.statusTxt);
        TextView descripTxt = (TextView)findViewById(R.id.descripTxt);
        Button type = (Button)findViewById(R.id.type);
        if(bundle!=null){
            idStory = bundle.getInt("idStory");
            String linkImg = bundle.getString("linkImg");
            Glide.with(getApplicationContext()).load(linkImg).into(imgStory);
            nameStoryTxt.setText(bundle.getString("nameStory"));
            authorTxt.setText(bundle.getString("author"));
            if(bundle.getBoolean("status")==true)
                statusTxt.setText("Còn tiếp");
            else statusTxt.setText("Hoàn thành");
            type.setText(bundle.getString("type"));
            descripTxt.setText(bundle.getString("description"));
        }

        Button readBtn = (Button)findViewById(R.id.readBtn);
        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), ChapterActivity.class);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });

        ImageView ava = (ImageView)findViewById(R.id.ava);
        String linkAva = getSharedPreferences("User",MODE_PRIVATE).getString("linkAva",null);
        Glide.with((getApplicationContext())).load(linkAva).into(ava);

        EditText commentTxt = (EditText)findViewById(R.id.commentTxt);
        Button commentBtn = (Button)findViewById(R.id.commentBtn);
        Button showCommentBtn = (Button)findViewById(R.id.showCommentBtn);
        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idUser = getSharedPreferences("User",MODE_PRIVATE).getInt("idUser",0);
                String email = getSharedPreferences("User",MODE_PRIVATE).getString("email",null);
                String position = getSharedPreferences("User",MODE_PRIVATE).getString("position",null);
                String fullname = getSharedPreferences("User",MODE_PRIVATE).getString("fullname",null);
                String comment = commentTxt.getText().toString();

                User user = new User(idUser,null,null,fullname,email,linkAva,position);
                Comment comment1 = new Comment();
                comment1.setComment(comment);
                comment1.setUser(user);

                DatabaseHandler db = new DatabaseHandler(getBaseContext());
                db.addComment(comment1,idStory);
                db.close();
                Toast.makeText(InfoStoryActivity.this,"Đã thêm bình luận",Toast.LENGTH_LONG).show();
            }
        });
        showCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CommentActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


}