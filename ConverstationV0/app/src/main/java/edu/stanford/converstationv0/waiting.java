package edu.stanford.converstationv0;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
