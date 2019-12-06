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

import java.util.ArrayList;

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
                        Toast.makeText(IndividualConversationActivity.this, "Conversation Created", Toast.LENGTH_LONG).show();
                        ArrayList<String> partners = new ArrayList<String>();
                        partners.add(partner.toString());
                        partners.add(new User(0).toString());
                        intent.putExtra("partners", partners);
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
        builder.setTitle("Are you sure you want to end the conversation?");
        LayoutInflater inflater = getLayoutInflater();
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(IndividualConversationActivity.this, MainActivity.class);
                        Toast.makeText(IndividualConversationActivity.this, "Conversation Ended", Toast.LENGTH_LONG).show();
                        startActivity(intent);
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

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to end the conversation?");
        LayoutInflater inflater = getLayoutInflater();
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(IndividualConversationActivity.this, MainActivity.class);
                        Toast.makeText(IndividualConversationActivity.this, "Conversation Ended", Toast.LENGTH_LONG).show();
                        startActivity(intent);
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
