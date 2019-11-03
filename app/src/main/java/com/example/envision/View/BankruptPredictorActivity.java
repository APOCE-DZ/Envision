package com.example.envision.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

import com.example.envision.R;
import com.example.envision.impl.KNNAlgorithm;

import java.util.ArrayList;

public class BankruptPredictorActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener {


    ArrayList<Double> valuesList;
    RatingBar industrial,management,financial,credit,compete,operate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankrupt_predictor);

        getSupportActionBar().setTitle("Bankruptcy Predictor");
        init();
        initList();
        initCardView();
    }


    public void initCardView(){
        findViewById(R.id.activity_bankrupt_predict_cardview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double item =0;
               for(int i =0;i<valuesList.size();i++){
                   item = valuesList.get(i);
                   if(item <= 1)
                       valuesList.set(i,0.0);
                   else if(item == 2)
                       valuesList.set(i,5.0);
                   else
                       valuesList.set(i,10.0);
               }

                KNNAlgorithm knnAlgorithm = new KNNAlgorithm(BankruptPredictorActivity.this);
                boolean returnValue = knnAlgorithm.getPrediction(valuesList,3);
                String result;
                if(returnValue)
                    result = "Bank is more likely to be bankrupted";
                else
                    result = "Bank is not likely to get bankrupted";
                new AlertDialog.Builder(BankruptPredictorActivity.this)
                        .setTitle("Result")
                        .setMessage(result)
                        .setPositiveButton("Ok",null)
                        .create().show();

            }
        });
    }
    public void initList(){
        valuesList = new ArrayList<>();

        for(int i = 0;i<6;i++)
            valuesList.add(1.0);

    }
    public void init(){
        industrial = findViewById(R.id.industrial_risk);
        management = findViewById(R.id.management_risk);
        financial = findViewById(R.id.financial_risk);
        credit = findViewById(R.id.credibility_risk);
        compete = findViewById(R.id.competitive_risk);
        operate = findViewById(R.id.operating_risk);

        industrial.setOnRatingBarChangeListener(this);
        management.setOnRatingBarChangeListener(this);
        financial.setOnRatingBarChangeListener(this);
        credit.setOnRatingBarChangeListener(this);
        compete.setOnRatingBarChangeListener(this);
        operate.setOnRatingBarChangeListener(this);
    }


    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

        if( ratingBar == industrial)
            valuesList.set(0,(double)(rating));
        if( ratingBar == management)
            valuesList.set(1,(double)(rating));
        if( ratingBar == financial)
            valuesList.set(2,(double)(rating));
        if( ratingBar == credit)
            valuesList.set(3,(double)(rating));
        if( ratingBar == compete)
            valuesList.set(4,(double)(rating));
        if( ratingBar == operate)
            valuesList.set(5,(double)(rating));
    }
}
