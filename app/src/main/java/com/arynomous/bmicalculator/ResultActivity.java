package com.arynomous.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ResultActivity extends AppCompatActivity {
    TextView tvBMI, tvResult;
    double bmi;
    DataDbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Setting up the database
        db = new DataDbHelper(this);

        // show back button on action bar
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvBMI = findViewById(R.id.tvBMI);
        tvResult = findViewById(R.id.tvResult);

        Intent intent = getIntent();
        bmi = intent.getDoubleExtra("bmi", 0);
        int isFromHistory = intent.getIntExtra("isFromHistory", 0);
        if(isFromHistory == 1){
            Button btSaveResult = findViewById(R.id.btSaveResult);
            btSaveResult.setVisibility(View.INVISIBLE);
        }
        tvBMI.setText(String.valueOf(bmi));

        if (bmi <= 18.5) {
            tvResult.setText("UNDERWEIGHT");
        } else if (bmi <= 24.9) {
            tvResult.setText("NORMAL");
        } else if (bmi <= 34.9) {
            tvResult.setText("OVER WEIGHT");
        } else {
            tvResult.setText("OBESITY");
        }

        Button btBack = findViewById(R.id.btBack);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void SaveResult(View view) {
        // getting current date to string
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
        String strDate = "Date : " + mdformat.format(calendar.getTime());
        Toast.makeText(this, "SAVED", Toast.LENGTH_SHORT).show();

        String toSave = strDate + " BMI : " + bmi;
        db.addData(toSave);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
