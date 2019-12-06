package edu.stanford.converstationv0;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
}