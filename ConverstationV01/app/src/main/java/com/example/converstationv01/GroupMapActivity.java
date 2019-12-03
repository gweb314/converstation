package com.example.converstationv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GroupMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_map);
    }

    public void pickAConverStation(View view) {
        Intent intent = new Intent(this, ConverStationInfoActivity.class);

        startActivity(intent);
    }
}
