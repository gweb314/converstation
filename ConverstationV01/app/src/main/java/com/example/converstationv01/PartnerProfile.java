package com.example.converstationv01;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PartnerProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_profile);

        partner = new User(getIntent().getStringExtra("partner"));

        profilePic = findViewById(R.id.partnerProfilePic);

        partnerName = findViewById(R.id.partnerName);
        partnerPronouns = findViewById(R.id.partnerPronouns);
        partnerMajorTitle = findViewById(R.id.partnerMajorTitle);
        partnerMajor = findViewById(R.id.partnerMajor);
        partnerMinorTitle = findViewById(R.id.partnerMinorTitle);
        partnerMinor = findViewById(R.id.partnerMinor);
        partnerInterests = findViewById(R.id.partnerInterests);

        fillInPartnerInfo();
    }

    ProfilePicView profilePic;
    TextView partnerName, partnerPronouns, partnerMajorTitle, partnerMajor, partnerMinorTitle, partnerMinor;
    TagListView partnerInterests;
    User partner;

    private void fillInPartnerInfo()
    {
        profilePic.setImage(partner.getImage());

        partnerName.setText(partner.getName());
        partnerPronouns.setText(partner.getPronouns());
        if(partner.getMajor().equals(""))
        {
            partnerMajorTitle.setVisibility(View.INVISIBLE);
        }
        else
        {
            partnerMajorTitle.setVisibility(View.VISIBLE);
        }
        partnerMajor.setText(partner.getMajor());
        if(partner.getMinor().equals(""))
        {
            partnerMinorTitle.setVisibility(View.INVISIBLE);
        }
        else
        {
            partnerMinorTitle.setVisibility(View.VISIBLE);
        }
        partnerMinor.setText(partner.getMinor());

        partnerInterests.setTags(partner.getInterests());
    }
}
