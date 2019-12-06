package edu.stanford.converstationv0;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ArrivedAtConversation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrived_at_group_conversation);

        //If joining a group conversation from the converstation profile:

        ProfilePicView profileView = findViewById(R.id.partnerProfilePic);
        profileView.setImage("Fiona");

        TextView nameView = findViewById(R.id.partnerName);
        nameView.setText("Fiona");

        TextView otherParticipantsView = findViewById(R.id.num_participants);
        otherParticipantsView.setText("+ 1 other");
    }
    
}
