package com.example.converstationv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IndividualConversationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_conversation);
    }

    public void viewProfile(View view) {
        Intent intent = new Intent(this, PartnerProfile.class);

        startActivity(intent);
    }

    public void makeGroup(View view) {
        Intent intent = new Intent(this, PartnerProfile.class);

        //startActivity(intent);
    }

    public void endConvo(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}
