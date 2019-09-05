package com.arynomous.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    static ArrayList<String> dataBMI = new ArrayList<>();
    static ArrayList<Integer> idBMI = new ArrayList<>();
    static ArrayAdapter adapter;
    DataDbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //Setting up the database
        db = new DataDbHelper(this);

        // show back button on action bar
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        idBMI.addAll(db.getID());
        dataBMI.addAll(db.getData());

        adapter = new ArrayAdapter(getApplicationContext(), R.layout.custom_textview, dataBMI);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String cek = dataBMI.get(i);
                String cek2 = cek.substring(28).trim();

                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("bmi", Double.valueOf(cek2));
                intent.putExtra("isFromHistory", 1);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(HistoryActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                dataBMI.remove(i);
                db.deleteData(idBMI.get(i));
                adapter.notifyDataSetChanged();
                return false;

            }
        });

        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
