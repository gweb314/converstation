package com.example.converstationv01;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openMyProfile(View view) {
        Intent intent = new Intent(this, MyProfileActivity.class);

        startActivity(intent);
    }
    public void openSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);

        startActivity(intent);
    }
    public void openSchedule(View view) {
        Intent intent = new Intent(this, schedule.class);

        startActivity(intent);
    }
    public void openMap(View view) {
        Intent intent = new Intent(this, GroupMapActivity.class);

        startActivity(intent);
    }
    public void meetNow(View view) {
        Intent intent = new Intent(this, waiting.class);

        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

    }
}