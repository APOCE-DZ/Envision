package com.example.envision.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.envision.R;
import com.example.envision.brestcancer.model.Patient;
import com.example.envision.impl.KNNAlgorithm;

import java.util.ArrayList;

public class PatientDetailActivity extends AppCompatActivity {


    TextView name, bloodGp,patientID;
    Patient patient;
    ArrayList<Double> testData;
    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);

        Intent intent = getIntent();
        patient = (Patient) intent.getSerializableExtra("PATIENT");

        init();
        extractData();
    }

    public void init(){
        name = findViewById(R.id.activity_patient_detal_name_tv);
        bloodGp = findViewById(R.id.activity_patient_bloodGp_tv);
        patientID = findViewById(R.id.activity_patient_id_tv);

        name.setText(patient.getName());
        bloodGp.setText(patient.getBlood_group());
        patientID.setText("" + patient.getId());
        cardView = findViewById(R.id.activity_patient_detail_predict_cardview);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        KNNAlgorithm knnAlgorithm = new KNNAlgorithm(PatientDetailActivity.this);
                        boolean returnValue = knnAlgorithm.getPrediction(testData,2);
                        String result;
                        if(returnValue)
                            result = "The person is likely to have cancer";
                        else
                            result = "The person is not likely to have cancer";
                        new AlertDialog.Builder(PatientDetailActivity.this)
                                .setTitle("Result")
                                .setMessage(result)
                                .setPositiveButton("Ok",null)
                                .create().show();
                    }
                },1000);
            }
        });
    }

    public void extractData(){
            testData = new ArrayList<Double>();
            testData.add(patient.getRadius_mean());
            testData.add(patient.getTexture_mean());
            testData.add(patient.getPerimeter_mean());
            testData.add(patient.getArea_mean());
            testData.add(patient.getSmoothness_mean());
            testData.add(patient.getCompactness_mean());
            testData.add(patient.getConcavity_mean());
            testData.add(patient.getConcave_points_mean());
            testData.add(patient.getSymmetry_mean());
            testData.add(patient.getFractal_dimension_mean());
            testData.add(patient.getRadius_se());
            testData.add(patient.getTexture_se());
            testData.add(patient.getPerimeter_se());
            testData.add(patient.getArea_se());
            testData.add(patient.getSmoothness_se());
            testData.add(patient.getCompactness_se());
            testData.add(patient.getConcavity_se());
            testData.add(patient.getConcave_points_se());
            testData.add(patient.getSymmetry_se());
            testData.add(patient.getFractal_dimension_se());
            testData.add(patient.getRadius_worst());
            testData.add(patient.getTexture_worst());
            testData.add(patient.getPerimeter_worst());
            testData.add(patient.getArea_worst());
            testData.add(patient.getSmoothness_worst());
            testData.add(patient.getCompactness_worst());
            testData.add(patient.getConcavity_worst());
            testData.add(patient.getConcave_points_worst());
            testData.add(patient.getSymmetry_worst());
            testData.add(patient.getFractal_dimension_worst());


    }

}
