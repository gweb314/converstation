package com.example.converstationv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FoundSomeoneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_someone);
    }

    public void viewProfile(View view) {
        Intent intent = new Intent(this, PartnerProfile.class);

        startActivity(intent);
    }

    public void startNavigation(View view) {
        Intent intent = new Intent(this, IndividualNavigationActivity.class);

        startActivity(intent);
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}