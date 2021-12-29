package com.example.truyenol.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.truyenol.R;
import com.example.truyenol.activity.MainCovid;
import com.example.truyenol.activity.MainDangNhap;
import com.example.truyenol.activity.ModifyStory.AdminActivity;
import com.example.truyenol.activity.UserInfoActivity;

import org.jetbrains.annotations.NotNull;

import static android.view.View.GONE;

public class AccountFragment extends Fragment {
    int idUser;
    String fullname, position,email,linkAva;

    @SuppressLint("SdCardPath")
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_fragment,container,false);

        idUser = getContext().getSharedPreferences("User", Context.MODE_PRIVATE).getInt("idUser",0);
        fullname = getContext().getSharedPreferences("User",Context.MODE_PRIVATE).getString("fullname",null);
        position = getContext().getSharedPreferences("User",Context.MODE_PRIVATE).getString("position",null);
        email = getContext().getSharedPreferences("User",Context.MODE_PRIVATE).getString("email",null);
        linkAva = getContext().getSharedPreferences("User",Context.MODE_PRIVATE).getString("linkAva",null);

        ImageView avaUser = (ImageView)view.findViewById(R.id.avaUser);
        Glide.with(getContext()).load(linkAva).into(avaUser);
        TextView nameUser = (TextView)view.findViewById(R.id.nameUser);
        nameUser.setText(fullname);
        TextView roleTxt = (TextView)view.findViewById(R.id.roleTxt);
        roleTxt.setText(position);

        TextView userInfo = (TextView) view.findViewById(R.id.userInfo);
        TextView btncovid = (TextView) view.findViewById(R.id.btncovid);
        TextView editStory = (TextView) view.findViewById(R.id.editStory);
        TextView backTxt = (TextView) view.findViewById(R.id.backTxt);
        if(position.equals("Admin")) editStory.setVisibility(View.VISIBLE);
        else editStory.setVisibility(GONE);
        editStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AdminActivity.class);
                startActivity(intent);
            }
        });

        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UserInfoActivity.class);
                startActivity(intent);
            }
        });

        btncovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),MainCovid.class);
                startActivity(i);
            }
        });

        backTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getContext(),MainDangNhap.class);
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("User",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                startActivity(a);
            }
        });

        return view;
    }

}