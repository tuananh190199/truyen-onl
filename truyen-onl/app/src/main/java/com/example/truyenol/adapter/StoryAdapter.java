package com.example.truyenol.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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
import com.example.truyenol.database.DatabaseHandler;
import com.example.truyenol.model.Story;
import com.example.truyenol.model.User;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


import java.util.ArrayList;
import java.util.List;

public class StoryAdapter extends BaseAdapter {
    private final Context context;
    private List<Story> list = new ArrayList<>();
    public StoryAdapter(Context context, ArrayList<Story> list) {
        this.context = context;
        this.list = list;

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
        convertView =inflater.inflate(R.layout.show1story,null);

        TextView nameStoryTxt = convertView.findViewById(R.id.nameHomeTxt);
        nameStoryTxt.setText(list.get(position).getNameStory());
        ImageView imgStory = convertView.findViewById(R.id.imgHome);
        Glide.with(context).load(list.get(position).getLinkImg()).into(imgStory);

        imgStory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoStoryActivity.class);
                Bundle bundle = new Bundle();
                //Truyền data
                bundle.putInt("idStory", list.get(position).getId());
                bundle.putString("linkImg",list.get(position).getLinkImg());
                bundle.putString("nameStory",list.get(position).getNameStory());
                bundle.putBoolean("status",list.get(position).getStatus());
                bundle.putString("author",list.get(position).getAuthor());
                bundle.putString("type",list.get(position).getType());
                bundle.putString("description",list.get(position).getDescription());
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });


        return convertView;
    }

}
