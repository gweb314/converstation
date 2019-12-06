package edu.stanford.converstationv0;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class GroupMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_map);
    }

    public void pickAConverStation(View view) {
        Intent intent = new Intent(this, ConverStationInfoActivity.class);

        startActivity(intent);
    }
}
