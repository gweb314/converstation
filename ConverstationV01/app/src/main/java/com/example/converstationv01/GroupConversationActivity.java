package com.example.converstationv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GroupConversationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_conversation);
    }

    public void viewGroupMembers(View view) {
        Intent intent = new Intent(this, GroupMembersActivity.class);

        startActivity(intent);
    }

    public void leaveConvo(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}
