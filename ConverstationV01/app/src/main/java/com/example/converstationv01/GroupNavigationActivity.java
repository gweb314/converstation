package com.example.converstationv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GroupNavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_navigation);
    }

    public void viewLocationInfo(View view) {
        Intent intent = new Intent(this, LocationInfoActivity.class);

        startActivity(intent);
    }

    public void arrive(View view) {
        Intent intent = new Intent(this, GroupArrivedActivity.class);

        startActivity(intent);
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}
