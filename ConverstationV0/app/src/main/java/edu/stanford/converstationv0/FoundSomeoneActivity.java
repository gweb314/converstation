package edu.stanford.converstationv0;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class FoundSomeoneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_someone);


        partner = new User((int)(Math.random() * 4), UserManager.getUser());

        profilePic = findViewById(R.id.partnerProfilePic);

        partnerName = findViewById(R.id.partnerName);
        partnerInterests = findViewById(R.id.partnerInterests);
        profilePic.setImage(partner.getImage());

        partnerName.setText(partner.getName());
        partnerInterests.setTags(partner.getInterests());
    }

    User partner;
    ProfilePicView profilePic;
    TextView partnerName;
    TagListView partnerInterests;

    public void viewProfile(View view) {
        Intent intent = new Intent(this, PartnerProfile.class);
        intent.putExtra("partner", partner.toString());

        startActivity(intent);
    }

    public void startNavigation(View view) {
        Intent intent = new Intent(this, ArrivedAtConversation.class);
        intent.putExtra("partner", partner.toString());
        intent.putExtra("individual", "true");
        startActivity(intent);
    }

    public void cancel(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to cancel?");
        LayoutInflater inflater = getLayoutInflater();
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(FoundSomeoneActivity.this, MainActivity.class);
                        Toast.makeText(FoundSomeoneActivity.this, "Conversation Canceled", Toast.LENGTH_LONG).show();
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
