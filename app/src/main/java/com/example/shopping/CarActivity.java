package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        getSupportFragmentManager().beginTransaction().replace(R.id.list_view_car, new BikeFragment()).commit();
    }
}