package edu.stanford.converstationv0;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.stanford.converstationv0.FoundSomeoneActivity;
import edu.stanford.converstationv0.R;

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
