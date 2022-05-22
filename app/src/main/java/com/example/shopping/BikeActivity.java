package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class BikeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike);

        getSupportFragmentManager().beginTransaction().replace(R.id.list_view, new BikeFragment()).commit();


    }
}