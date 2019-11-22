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

public class AddTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_time);

        daySpinner = findViewById(R.id.day);
        ArrayAdapter<CharSequence> dayAdapter = ArrayAdapter.createFromResource(this, R.array.weekdays, R.layout.weekday_spinner_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);
    }

    Spinner daySpinner;

    public void addTimeToPlan(View view)
    {
        Intent intent = new Intent(this, schedule.class);
        int day = Plan.dayStringToInt(daySpinner.getSelectedItem().toString());
        TimePicker timePicker = findViewById(R.id.startTime);
        int start = timePicker.getCurrentHour() * 60 + timePicker.getCurrentMinute();
        EditText text = findViewById(R.id.duration);

        findViewById(R.id.startTimeText).setBackgroundColor(Color.TRANSPARENT);
        findViewById(R.id.durationText).setBackgroundColor(Color.TRANSPARENT);

        if(text.getText().toString().equals(""))
        {
            Toast toast = Toast.makeText(this, "Please specify a duration", Toast.LENGTH_LONG);
            toast.show();
            findViewById(R.id.durationText).setBackgroundColor(Color.parseColor("#ff3232"));
            return;
        }
        int duration = Integer.parseInt(text.getText().toString());

        if(duration < 15)
        {
            Toast toast = Toast.makeText(this, "Duration must be at least 15 minutes", Toast.LENGTH_LONG);
            toast.show();
            findViewById(R.id.durationText).setBackgroundColor(Color.parseColor("#ff3232"));
            return;
        }

        if(Plan.MAXTIME - start < 15)
        {
            Toast toast = Toast.makeText(this, "Start time must be at least 15 minutes before midnight", Toast.LENGTH_LONG);
            toast.show();
            findViewById(R.id.startTimeText).setBackgroundColor(Color.parseColor("#ff3232"));
            return;
        }

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
