package com.example.converstationv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IndividualArrivedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_arrived);
        partner = new User(getIntent().getStringExtra("partner"));
    }

    User partner;

    public void foundPartner(View view) {
        Intent intent = new Intent(this, IndividualConversationActivity.class);
        intent.putExtra("partner", partner.toString());

        startActivity(intent);
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}
