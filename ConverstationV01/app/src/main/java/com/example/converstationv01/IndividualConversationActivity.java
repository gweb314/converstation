package com.example.converstationv01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        partnerInterests.setTags(partner.getInterests());
}

    public void viewProfile(View view) {
        Intent intent = new Intent(this, PartnerProfile.class);
        intent.putExtra("partner", partner.toString());

        startActivity(intent);
    }

    public void makeGroup(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Set Conversation Topic");
        builder.setMessage("Type a few things you would like to talk about in your group conversation");
        LayoutInflater inflater = getLayoutInflater();
        final View dialogLayout = inflater.inflate(R.layout.topic_input_dialog, null);
        builder.setView(dialogLayout);
        builder.setPositiveButton("Create",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(IndividualConversationActivity.this, GroupConversationActivity.class);
                        Toast.makeText(IndividualConversationActivity.this, "Conversation Created",Toast.LENGTH_LONG).show();
                        intent.putExtra("partner", partner.toString());
                        TagEditView tags = dialogLayout.findViewById(R.id.tagEditView);
                        intent.putExtra("tags", tags.getTagsString());
                        startActivity(intent);
                    }
                });
        builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

    public void endConvo(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to end?");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(IndividualConversationActivity.this, MainActivity.class);
                        Toast.makeText(IndividualConversationActivity.this, "Conversation Ended",Toast.LENGTH_LONG).show();
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
