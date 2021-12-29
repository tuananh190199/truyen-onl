package com.example.truyenol.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.truyenol.R;
import com.example.truyenol.activity.InfoStoryActivity;
import com.example.truyenol.model.Chapter;
import com.example.truyenol.model.Story;
import com.example.truyenol.model.User;

import java.util.ArrayList;
import java.util.List;

public class RankingAdapter extends BaseAdapter {
    private final Context context;
    private List<Story> list = new ArrayList<>();
    User user = new User();

    public RankingAdapter(Context context, ArrayList<Story> list,User user) {
        this.context = context;
        this.list = list;
        this.user = user;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView =inflater.inflate(R.layout.list_top_story,null);
        TextView txtTop = (TextView)convertView.findViewById(R.id.txtTop);
        txtTop.setText(list.get(position).getNameStory());
        ImageView imgTop = (ImageView)convertView.findViewById(R.id.imgTop);
        Glide.with(context).load(list.get(position).getLinkImg()).into(imgTop);
        txtTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoStoryActivity.class);
                Bundle bundle = new Bundle();
                //Truyền data
                bundle.putInt("idUser", user.getId());
                bundle.putString("linkAva",user.getLinkAva());
                bundle.putString("fullname",user.getFullName());
                bundle.putString("email",user.getEmail());
                bundle.putString("position",user.getPosition());
                bundle.putInt("id", list.get(position).getId());
                bundle.putString("linkImg",list.get(position).getLinkImg());
                bundle.putString("nameStory",list.get(position).getNameStory());
                bundle.putString("author",list.get(position).getAuthor());
                bundle.putString("type",list.get(position).getType());
                bundle.putString("description",list.get(position).getDescription());
                bundle.putBoolean("status",list.get(position).getStatus());
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
