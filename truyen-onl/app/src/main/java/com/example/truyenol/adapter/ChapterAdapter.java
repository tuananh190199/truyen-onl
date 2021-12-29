package com.example.truyenol.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.truyenol.R;
import com.example.truyenol.activity.ContentActivity;
import com.example.truyenol.model.Chapter;

import java.util.ArrayList;
import java.util.List;

public class ChapterAdapter extends BaseAdapter {
    private final Context context;
    private List<Chapter> list = new ArrayList<>();

    public ChapterAdapter(Context context, List<Chapter> list) {
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
        convertView =inflater.inflate(R.layout.list_chapter,null);
        TextView nameChaptxt = convertView.findViewById(R.id.nameChapterTxt);
        nameChaptxt.setText(list.get(position).getNameChapter());

        nameChaptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ContentActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("nameChapter", list.get(position).getNameChapter());
                bundle.putString("content", list.get(position).getContent());
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
