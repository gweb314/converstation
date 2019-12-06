package com.example.converstationv01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ResourceManager.loadProfilePics(this);
    }

    public void openCreateProfile(View view) {
        Intent intent = new Intent(this, EditProfileActivity.class);

        startActivity(intent);
    }
}
