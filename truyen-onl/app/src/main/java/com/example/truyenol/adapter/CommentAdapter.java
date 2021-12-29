package com.example.truyenol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.truyenol.R;
import com.example.truyenol.model.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends BaseAdapter {
    private final Context context;
    private List<Comment> list = new ArrayList<>();

    public CommentAdapter(Context context, List<Comment> list) {
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
        convertView =inflater.inflate(R.layout.display_comment,null);

        ImageView avaCmt = (ImageView)convertView.findViewById(R.id.avaCmtImg);
        Glide.with(context).load(list.get(position).getUser().getLinkAva()).into(avaCmt);
        TextView usernameCmt = (TextView)convertView.findViewById(R.id.usernameCmtTxt);
        usernameCmt.setText(list.get(position).getUser().getUsername());
        TextView comment = (TextView)convertView.findViewById(R.id.commentTxt2);
        comment.setText(list.get(position).getComment());

        return convertView;
    }
}
