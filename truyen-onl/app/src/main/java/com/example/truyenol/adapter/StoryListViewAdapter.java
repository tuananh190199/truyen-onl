package com.example.truyenol.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.truyenol.R;
import com.example.truyenol.model.Story;

import java.util.ArrayList;

public class StoryListViewAdapter extends BaseAdapter {
    final ArrayList<Story> storyList;

    public StoryListViewAdapter(ArrayList<Story> storyList) {
        this.storyList = storyList;
    }


    @Override
    public int getCount() {
        return storyList.size();
    }

    @Override
    public Object getItem(int position) {
        return storyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return storyList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View storyView;
        if(convertView==null){
            storyView=View.inflate(parent.getContext(), R.layout.story_view,null);
        }else storyView=convertView;

        Story story= (Story) getItem(position);
        ((TextView) storyView.findViewById(R.id.idStory)).setText(String.format("ID : %d",story.getId()));
        ((TextView) storyView.findViewById(R.id.nameStory)).setText(String.format("Tên truyện : %s",story.getNameStory()));
        ((TextView) storyView.findViewById(R.id.status)).setText(String.format("Status : %b",story.getStatus()));



        return storyView;
    }
}
