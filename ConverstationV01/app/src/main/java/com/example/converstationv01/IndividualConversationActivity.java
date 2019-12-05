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
        partnerInterests = findViewById(R.id.partnerInterests);

        fillInPartnerInfo();
    }

    ProfilePicView profilePic;
    TextView partnerName;
    TagListView partnerInterests;
    User partner;

    private void fillInPartnerInfo()
    {
        profilePic.setImage(partner.getImage());

        partnerName.setText(partner.getName());
}

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
