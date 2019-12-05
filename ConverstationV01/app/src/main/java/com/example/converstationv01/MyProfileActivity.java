package com.example.converstationv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        user = UserManager.getUser();

        profilePic = findViewById(R.id.myProfilePic);

        myName = findViewById(R.id.myName);
        myPronouns = findViewById(R.id.myPronouns);
        myMajorTitle = findViewById(R.id.myMajorTitle);
        myMajor = findViewById(R.id.myMajor);
        myMinorTitle = findViewById(R.id.myMinorTitle);
        myMinor = findViewById(R.id.myMinor);
        myInterests = findViewById(R.id.myInterests);

        fillInUserInfo();
    }

    ProfilePicView profilePic;
    TextView myName, myPronouns, myMajorTitle, myMajor, myMinorTitle, myMinor;
    TagListView myInterests;
    User user;

    public void editProfile(View view) {
        Intent intent = new Intent(this, EditProfileActivity.class);

        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

    private void fillInUserInfo()
    {
        profilePic.setImage(user.getImage());

        myName.setText(user.getName());
        myPronouns.setText(user.getPronouns());
        if(user.getMajor().equals(""))
        {
            myMajorTitle.setVisibility(View.INVISIBLE);
        }
        else
        {
            myMajorTitle.setVisibility(View.VISIBLE);
        }
        myMajor.setText(user.getMajor());
        if(user.getMinor().equals(""))
        {
            myMinorTitle.setVisibility(View.INVISIBLE);
        }
        else
        {
            myMinorTitle.setVisibility(View.VISIBLE);
        }
        myMinor.setText(user.getMinor());

        myInterests.setTags(user.getInterests());
    }
}
