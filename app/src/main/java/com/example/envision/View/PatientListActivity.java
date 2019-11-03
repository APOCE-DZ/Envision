package com.example.envision.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.envision.Adapter.PatientListRecyclerView;
import com.example.envision.R;
import com.example.envision.brestcancer.model.Patient;
import com.example.envision.brestcancer.service.ExtractTestData;
import com.example.envision.impl.KNNAlgorithm;

import java.util.ArrayList;

public class PatientListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Patient> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_page);

        init();
        initRecyclerView();
    }

    public void init(){
        ExtractTestData data = new ExtractTestData(this);
        list = data.extractData();
    }

    public void initRecyclerView(){
        recyclerView = findViewById(R.id.listpage_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(new PatientListRecyclerView(this,list));
    }
}
