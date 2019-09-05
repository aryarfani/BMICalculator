package com.arynomous.bmicalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.ShareActionProvider;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView tvWeight, tvAge, tvHeight, tvMale, tvFemale;
    Button btMinWeight, btAddWeight, btAddAge, btMinAge;
    int weight, age, height;
    SeekBar seekBar;
    ImageView ivMale, ivFemale;
    String sex = "";
    Handler handler = new Handler();
    Runnable runnable;

    // Navigation Drawer
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle mToogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        weight = 55;
        age = 19;
        height = 165;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Navigation Drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this); // adding listener to navigation item grammartically

        // Adding toggle for navigation drawer
        mToogle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(mToogle);
        mToogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvWeight = findViewById(R.id.tvWeight);
        tvAge = findViewById(R.id.tvAge);
        tvHeight = findViewById(R.id.tvHeight);
        tvMale = findViewById(R.id.tvMale);
        tvFemale = findViewById(R.id.tvFemale);
        ivMale = findViewById(R.id.ivMale);
        ivFemale = findViewById(R.id.ivFemale);

        // Male Female Gender
        ivMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sex = "male";
                ivMale.setImageResource(R.drawable.male_clicked);
                ivFemale.setImageResource(R.drawable.female);
                tvMale.setTextColor(getResources().getColor(R.color.lightText));
                tvFemale.setTextColor(getResources().getColor(R.color.darkText));
            }
        });

        ivFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sex = "female";
                ivFemale.setImageResource(R.drawable.female_clicked);
                ivMale.setImageResource(R.drawable.male);
                tvFemale.setTextColor(getResources().getColor(R.color.lightText));
                tvMale.setTextColor(getResources().getColor(R.color.darkText));
            }
        });


        // Seekbar to Height
        seekBar = findViewById(R.id.sbHeight);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                height = i;
                tvHeight.setText(String.valueOf(height));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // button weight and age
        btMinWeight = findViewById(R.id.btMinWeight);
        btMinWeight.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (!btMinWeight.isPressed()) return;
                        weight--;
                        tvWeight.setText(String.valueOf(weight));
                        handler.postDelayed(runnable, 100); //delay between each runnable
                    }
                };

                handler.postDelayed(runnable, 100); //trigger hold click
                return true;
            }
        });
        btMinWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight--;
                tvWeight.setText(String.valueOf(weight));
            }
        });

        btAddWeight = findViewById(R.id.btAddWeight);
        btAddWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight++;
                tvWeight.setText(String.valueOf(weight));
            }
        });
        btAddWeight.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (!btAddWeight.isPressed()) return;
                        weight++;
                        tvWeight.setText(String.valueOf(weight));
                        handler.postDelayed(runnable, 100); //delay between each runnable
                    }
                };

                handler.postDelayed(runnable, 100); //trigger hold click
                return true;
            }
        });

        btAddAge = findViewById(R.id.btAddAge);
        btAddAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age++;
                tvAge.setText(String.valueOf(age));
            }
        });
        btAddAge.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (!btAddAge.isPressed()) return;
                        age++;
                        tvAge.setText(String.valueOf(age));
                        handler.postDelayed(runnable, 100); //delay between each runnable
                    }
                };

                handler.postDelayed(runnable, 100); //trigger hold click
                return true;
            }
        });

        btMinAge = findViewById(R.id.btMinAge);
        btMinAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age--;
                tvAge.setText(String.valueOf(age));
            }
        });
        btMinAge.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (!btAddAge.isPressed()) return;
                        age--;
                        tvAge.setText(String.valueOf(age));
                        handler.postDelayed(runnable, 100); //delay between each runnable
                    }
                };

                handler.postDelayed(runnable, 100); //trigger hold click
                return true;
            }
        });
    }

    public void CalculateClick(View view) {
        double height = Double.valueOf(tvHeight.getText().toString());
        double heightMeter = height / 100;
        double weight = Double.valueOf(tvWeight.getText().toString());
        double bmi = weight / (heightMeter * heightMeter);
        double bmiFinal = Math.floor(bmi * 100) / 100;

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("bmi", bmiFinal);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mToogle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_history: {
                Intent intent = new Intent(this, HistoryActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_about: {
                Toast.makeText(this, "about", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        // penanda bahwa item telah di klik
//        item.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}
