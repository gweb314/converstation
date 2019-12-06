package com.example.converstationv01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupConversationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_conversation);
        tagString = getIntent().getStringExtra("tags");
        ArrayList<String> al = new ArrayList<String>();
        for(String s : tagString.split(" ")) {
            al.add(s);
        }
        tagList = findViewById(R.id.tagListView);
        tagList.setTags(al);

    }

    TagListView tagList;
    String tagString;

    public void viewGroupMembers(View view) {
        Intent intent = new Intent(this, GroupMembersActivity.class);

        startActivity(intent);
    }

    public void leaveConvo(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to leave?");
        LayoutInflater inflater = getLayoutInflater();
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(GroupConversationActivity.this, MainActivity.class);
                        Toast.makeText(GroupConversationActivity.this, "Conversation Ended",Toast.LENGTH_LONG).show();
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
