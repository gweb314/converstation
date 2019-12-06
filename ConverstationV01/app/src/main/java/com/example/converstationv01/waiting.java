package com.example.converstationv01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class waiting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        findingTimer = new Timer();
        findingTimer.schedule(new TimerTask(){
            public void run() {
                waiting.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Intent intent = new Intent(waiting.this, FoundSomeoneActivity.class);

                        startActivity(intent);
                        finish();
                    }
                });
            }
        }, 4000);
    }

    Timer findingTimer;

    public void findSomeone(View view) {
        Intent intent = new Intent(this, FoundSomeoneActivity.class);

        startActivity(intent);
        finish();
    }

    public void cancel(View view) {
        finish();
    }

    @Override
    public void onBackPressed() {
        findingTimer.cancel();
        finish();
    }
}
