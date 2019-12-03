package com.example.converstationv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }

    public void saveProfile(View view) {
        Intent intent = new Intent(this, MyProfileActivity.class);

        startActivity(intent);
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, MyProfileActivity.class);

        startActivity(intent);
    }
}
