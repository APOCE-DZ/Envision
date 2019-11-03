package com.example.envision.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.envision.Adapter.MainRecyclerView;
import com.example.envision.R;

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
