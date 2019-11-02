package com.example.envision;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<Integer> cardViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();
        initRecycler();
    }


    public void initList(){
        cardViews = new ArrayList<>();
        cardViews.add(R.mipmap.ic_launcher);
        cardViews.add(R.mipmap.ic_launcher);
        cardViews.add(R.mipmap.ic_launcher);
        cardViews.add(R.mipmap.ic_launcher);
        cardViews.add(R.mipmap.ic_launcher);
        cardViews.add(R.mipmap.ic_launcher);
        cardViews.add(R.mipmap.ic_launcher);
        cardViews.add(R.mipmap.ic_launcher);
        cardViews.add(R.mipmap.ic_launcher);
        cardViews.add(R.mipmap.ic_launcher);
    }

    public void initRecycler(){
        RecyclerView recyclerView = findViewById(R.id.main_recyclerview);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(new MainRecyclerView(this,cardViews));
    }

    @Override
    public void onClick(View v) {


    }
}
