package com.example.truyenol.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.truyenol.R;
import com.example.truyenol.adapter.RankingAdapter;
import com.example.truyenol.model.Story;
import com.example.truyenol.model.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;



public class RankingFragment extends Fragment {
    int idUser;
    String fullname, position,email,linkAva;
    private ArrayList<Story> stories;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ranking_fragment, container, false);
        Bundle bundle = getArguments();
        if(bundle!=null){
            idUser = bundle.getInt("idUser");
            fullname = bundle.getString("fullname");
            position = bundle.getString("position");
            email = bundle.getString("email");
            linkAva = bundle.getString("linkAva");
        }
        User user = new User(idUser,null,null,fullname,email,linkAva,position);
        ListView lv1 = (ListView) view.findViewById(R.id.listTopStory);
        RankingAdapter adapter = new RankingAdapter(getContext(), stories,user);
        lv1.setAdapter(adapter);
        return view;
    }
    public RankingFragment(ArrayList<Story> stories){
        this.stories=stories;
    }

}
