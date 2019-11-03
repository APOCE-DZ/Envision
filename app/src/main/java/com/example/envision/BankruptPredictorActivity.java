package com.example.envision;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

public class BankruptPredictorActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener {


    ArrayList<Integer> valuesList;
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
                int item =0;
               for(int i =0;i<valuesList.size();i++){
                   item = valuesList.get(i);
                   if(item <= 1)
                       valuesList.set(i,0);
                   else if(item == 2)
                       valuesList.set(i,5);
                   else
                       valuesList.set(i,10);
               }

                Toast.makeText(BankruptPredictorActivity.this, "" + valuesList.toString(), Toast.LENGTH_SHORT).show();

                int returnValue = 0;
                String result;
                if(returnValue == 0)
                    result = "Successful !! No chance of Bankruptcy";
                else
                    result = "Problem Bankruptcy imminent";
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
            valuesList.add(1);

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
            valuesList.set(0,(int)(rating));
        if( ratingBar == management)
            valuesList.set(1,(int)(rating));
        if( ratingBar == financial)
            valuesList.set(2,(int)(rating));
        if( ratingBar == credit)
            valuesList.set(3,(int)(rating));
        if( ratingBar == compete)
            valuesList.set(4,(int)(rating));
        if( ratingBar == operate)
            valuesList.set(5,(int)(rating));
    }
}
