package com.example.converstationv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GroupMembersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_members);
    }

    public void viewProfile(View view) {
        Intent intent = new Intent(this, PartnerProfile.class);

        startActivity(intent);
    }
}
