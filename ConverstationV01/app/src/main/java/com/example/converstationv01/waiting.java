package com.example.converstationv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class waiting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
    }
    /*
    public void cancel(View view) {
        finish();
    }
    */

    public void findSomeone(View view) {
        Intent intent = new Intent(this, FoundSomeoneActivity.class);

        startActivity(intent);
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}
