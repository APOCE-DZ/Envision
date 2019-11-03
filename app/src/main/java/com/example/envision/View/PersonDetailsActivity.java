package com.example.envision.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.envision.Adapter.PersonDetailRecyclerView;
import com.example.envision.R;

import java.util.ArrayList;

public class PersonDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    CardView cardView;
    TextView name, age, school, work, income,accountId;
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);

        init();
        initTextView();
        initRecyclerView();
    }

    public void init() {
        list = new ArrayList<>();

        for(int i=0;i<3;i++){
            list.add("Elemetalaallall " + i);
        }
    }

    public void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.activity_person_detail_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new PersonDetailRecyclerView(list));
    }

    public void initTextView() {
        name = findViewById(R.id.activity_person_detal_name_tv);
        age = findViewById(R.id.activity_person_detail_age_tv);
        school = findViewById(R.id.activity_person_detail_school_tv);
        work =  findViewById(R.id.activity_person_detail_work_tv);
        income = findViewById(R.id.activity_person_detail_income_tv);
        accountId = findViewById(R.id.activity_person_detail_account_id);
        cardView = findViewById(R.id.activity_person_detail_predict_cardview);

        cardView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if( v == cardView){

        }
    }
}
