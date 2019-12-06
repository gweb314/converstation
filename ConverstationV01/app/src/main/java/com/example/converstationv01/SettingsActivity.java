package com.example.converstationv01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        help_text =  findViewById(R.id.help_text);
        help_text.setVisibility(View.GONE);
        about_text =  findViewById(R.id.about_text);
        about_text.setVisibility(View.GONE);
    }

    TextView help_text;
    TextView about_text;

    protected void resetPrototype(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to delete your profile?");
        builder.setMessage("This cannot be undone.");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(SettingsActivity.this, WelcomeActivity.class);
                        Toast.makeText(SettingsActivity.this, "Profile Deleted",Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }
                });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }


    public void toggle_contents_help(View v){
        help_text.setVisibility( help_text.isShown()
                ? View.GONE
                : View.VISIBLE );
    }

    public void toggle_contents_about(View v){
        about_text.setVisibility( about_text.isShown()
                ? View.GONE
                : View.VISIBLE );
    }
}
