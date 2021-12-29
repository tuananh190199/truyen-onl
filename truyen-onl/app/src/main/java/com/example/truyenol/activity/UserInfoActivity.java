package com.example.truyenol.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.truyenol.R;

public class UserInfoActivity extends AppCompatActivity {
    int idUser;
    String fullname, position,email,linkAva;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        idUser = getSharedPreferences("User",MODE_PRIVATE).getInt("idUser",0);
        fullname = getSharedPreferences("User",MODE_PRIVATE).getString("fullname",null);
        position = getSharedPreferences("User",MODE_PRIVATE).getString("position",null);
        email = getSharedPreferences("User",MODE_PRIVATE).getString("email",null);
        linkAva = getSharedPreferences("User",MODE_PRIVATE).getString("linkAva",null);

        ImageView avaInfo = (ImageView)findViewById(R.id.avaInfo);
        Glide.with(getApplicationContext()).load(linkAva).into(avaInfo);
        TextView fullnameTxt = (TextView)findViewById(R.id.fullnameTxt);
        fullnameTxt.setText(fullname);
        TextView emailTxt = (TextView)findViewById(R.id.emailTxt);
        emailTxt.setText(email);
    }
}