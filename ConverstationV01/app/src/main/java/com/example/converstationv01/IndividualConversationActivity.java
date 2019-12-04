package com.example.converstationv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class IndividualConversationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_conversation);

        partner = new User(getIntent().getStringExtra("partner"));

        profilePic = findViewById(R.id.partnerProfilePic);

        partnerName = findViewById(R.id.partnerName);
        partnerPronouns = findViewById(R.id.partnerPronouns);
        partnerMajorTitle = findViewById(R.id.partnerMajorTitle);
        partnerMajor = findViewById(R.id.partnerMajor);
        partnerMinorTitle = findViewById(R.id.partnerMinorTitle);
        partnerMinor = findViewById(R.id.partnerMinor);

        profilePic.setImage(partner.getImage());
    }

    ProfilePicView profilePic;
    TextView partnerName, partnerPronouns, partnerMajorTitle, partnerMajor, partnerMinorTitle, partnerMinor;
    User partner;

    public void viewProfile(View view) {
        Intent intent = new Intent(this, PartnerProfile.class);
        intent.putExtra("partner", partner.toString());

        startActivity(intent);
    }

    public void makeGroup(View view) {
        Intent intent = new Intent(this, GroupConversationActivity.class);
        intent.putExtra("partner", partner.toString());

        startActivity(intent);
    }

    public void endConvo(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
    }
}
