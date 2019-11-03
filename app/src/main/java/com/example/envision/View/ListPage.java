package com.example.envision.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.envision.Adapter.ListPageRecyclerView;
import com.example.envision.R;

import java.util.ArrayList;

public class ListPage extends AppCompatActivity {

    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_page);

        initList();
        initRecyclerView();
    }

    public void initList(){
        list = new ArrayList<>();
        for(int i= 300;i<310;i++){
            list.add("Name  "+  i);
        }
    }


    public void initRecyclerView(){

        RecyclerView recyclerView = findViewById(R.id.listpage_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(new ListPageRecyclerView(this,list));
    }
}
