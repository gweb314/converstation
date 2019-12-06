package com.example.converstationv01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        if(UserManager.hasExchanged(partner.getName()))
        {
            exchangeEmail(false);
        }
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

    public void requestEmail(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Confirmation");
        builder.setMessage("Are you sure you want to request an email address exchange with " + partnerName.getText().toString() + "?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        exchangeEmail(true);
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void exchangeEmail(boolean showToast)
    {
        LinearLayout layout = findViewById(R.id.emailLayout);
        layout.removeAllViews();
        TextView newView = new TextView(this);
        newView.setText(partner.getEmail());
        newView.setTextSize(24);
        layout.addView(newView);
        UserManager.exchangeEmail(partner.getName());

        if(showToast)
        {
            Toast toast = Toast.makeText(this, partner.getName() + " accepted your email exchange request", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
