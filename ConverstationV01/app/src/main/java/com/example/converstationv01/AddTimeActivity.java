package com.example.converstationv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class AddTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_time);

        daySpinner = findViewById(R.id.day);
        ArrayAdapter<CharSequence> dayAdapter = ArrayAdapter.createFromResource(this, R.array.weekdays, R.layout.weekday_spinner_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);

        dayLayout = findViewById(R.id.dayLayout);
        startTimeLayout = findViewById(R.id.startTimeLayout);
        durrationLayout = findViewById(R.id.durrationLayout);
    }

    Spinner daySpinner;
    TextInputLayout dayLayout, startTimeLayout, durrationLayout;

    public void addTimeToPlan(View view)
    {
        Intent intent = new Intent(this, schedule.class);
        int day = Plan.dayStringToInt(daySpinner.getSelectedItem().toString());
        TimePicker timePicker = findViewById(R.id.startTime);
        int start = timePicker.getCurrentHour() * 60 + timePicker.getCurrentMinute();
        EditText text = findViewById(R.id.duration);

        boolean valid = true;

        if(text.getText().toString().equals(""))
        {
            durrationLayout.setErrorEnabled(true);
            durrationLayout.setError("Please specify a duration");
            System.out.println("Please specify a duration");
            valid = false;
        }

        else if(Integer.parseInt(text.getText().toString()) < 15)
        {
            durrationLayout.setErrorEnabled(true);
            durrationLayout.setError("Duration must be at least 15 minutes");
            System.out.println("Duration must be at least 15 minutes");
            valid = false;
        }

        else
        {
            durrationLayout.setErrorEnabled(false);
        }

        if(Plan.MAXTIME - start < 15)
        {
            startTimeLayout.setErrorEnabled(true);
            startTimeLayout.setError("Start time must be at least 15 minutes before midnight");
            System.out.println("Start time must be at least 15 minutes before midnight");
            valid = false;
        }
        else
        {
            startTimeLayout.setErrorEnabled(false);
        }

        if(!valid) return;

        int duration = Integer.parseInt(text.getText().toString());

        if(start + duration > 1440)
        {
            duration = 1440 - start;
        }

        intent.putExtra("day", day);
        intent.putExtra("start", start);
        intent.putExtra("duration", duration);
        Toast toast = Toast.makeText(this, "New Time Added", Toast.LENGTH_LONG);
        toast.show();
        startActivity(intent);
    }
}
