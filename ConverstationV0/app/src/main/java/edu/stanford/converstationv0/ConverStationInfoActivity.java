package edu.stanford.converstationv0;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ConverStationInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conver_station_info);
    }

    public void createGroup(View view) {
        Intent intent = new Intent(this, GroupConversationActivity.class);

        startActivity(intent);
    }

    public void joinGroup(View view) {
        Intent intent = new Intent(this, GroupNavigationActivity.class);

        startActivity(intent);
    }
}
