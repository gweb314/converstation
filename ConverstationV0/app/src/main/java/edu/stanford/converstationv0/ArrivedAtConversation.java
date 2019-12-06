package edu.stanford.converstationv0;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ArrivedAtConversation extends AppCompatActivity {

    ArrayList<String> partners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrived_at_group_conversation);

        //If joining a group conversation from the converstation profile:

        User partner = new User(getIntent().getStringExtra("partner"));

        ProfilePicView profileView = findViewById(R.id.partnerProfilePic);
        profileView.setImage(partner.getImage());

        TextView nameView = findViewById(R.id.partnerName);
        nameView.setText(partner.getName());

        TextView otherParticipantsView = findViewById(R.id.num_participants);
        otherParticipantsView.setText("+ 1 other");

        User secondPartner;

        if (partner.getName().equals("Eli")) {
            secondPartner = new User(1);
        } else {
            secondPartner = new User(0);
        }

        partners.add(partner.toString());
        partners.add(secondPartner.toString());
    }

    public void leaveConvo(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to cancel the conversation?");
        LayoutInflater inflater = getLayoutInflater();
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(ArrivedAtConversation.this, MainActivity.class);
                        Toast.makeText(ArrivedAtConversation.this, "Conversation Ended", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }
                });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void startConvo(View v) {
        if (partners.size() > 1) {
            Intent myIntent = new Intent(ArrivedAtConversation.this, GroupConversationActivity.class);
            myIntent.putExtra("partners", partners);
            ArrivedAtConversation.this.startActivity(myIntent);
        } else {
            Intent myIntent = new Intent(ArrivedAtConversation.this, IndividualConversationActivity.class);
            myIntent.putExtra("partners", partners.get(0));
            ArrivedAtConversation.this.startActivity(myIntent);
        }
    }
}
