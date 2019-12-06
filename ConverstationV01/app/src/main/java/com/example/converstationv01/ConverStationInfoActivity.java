package com.example.converstationv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ConverStationInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conver_station_info);
    }

    public void createGroup(View view) {
        Intent intent = new Intent(this, GroupConversationActivity.class);

        startActivity(intent);
        finish();
    }

    public void joinGroup(View view) {
        Intent intent = new Intent(this, GroupNavigationActivity.class);

        startActivity(intent);
        finish();
    }
}
