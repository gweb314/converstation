package edu.stanford.converstationv0;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class IndividualNavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_navigation);
        partner = new User(getIntent().getStringExtra("partner"));
    }
    User partner;

    public void viewLocationInfo(View view) {
        Intent intent = new Intent(this, LocationInfoActivity.class);

        startActivity(intent);
    }

    public void arrive(View view) {
        Intent intent = new Intent(this, IndividualArrivedActivity.class);
        intent.putExtra("partner", partner.toString());

        startActivity(intent);
    }

    public void cancel(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to end?");
        LayoutInflater inflater = getLayoutInflater();
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(IndividualNavigationActivity.this, MainActivity.class);
                        Toast.makeText(IndividualNavigationActivity.this, "Conversation Ended", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        finish();
                    }
                });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
