package com.example.converstationv01;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class schedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

       plans = new ArrayList<Plan>();

        FileInputStream fis = null;
        System.out.println("step 1");
        try {
            fis = openFileInput("ConverStationPlans");
            Scanner scanner = new Scanner(fis);
            scanner.useDelimiter(",");
            System.out.println("step 2");
            while(scanner.hasNext())
            {
                System.out.println("step 3");
                int day = Integer.parseInt(scanner.next());
                int start = Integer.parseInt(scanner.next());
                int duration = Integer.parseInt(scanner.next());
                plans.add(new Plan(day, start, duration));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

       int day = getIntent().getIntExtra("day", -1);
       if(day >= 0)
       {
           int start = getIntent().getIntExtra("start", -1);
           int duration = getIntent().getIntExtra("duration", -1);
           plans.add(new Plan(day, start, duration));
           savePlans();
       }

       String[] planStrings = new String[plans.size()];
        for(int i = 0; i < plans.size(); i++)
        {
            Plan plan = plans.get(i);
            planStrings[i] = plan.getDayString() + " " + plan.getStartString() + " to " + plan.getEndString();
        }

       planList = findViewById(R.id.planList);
        ListAdapter planListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, planStrings);
        planList.setAdapter(planListAdapter);


    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    ArrayList<Plan> plans;
    ListView planList;


    public void openAddTime(View view)
    {
        Intent intent = new Intent(this, AddTimeActivity.class);
        startActivity(intent);
    }

    public void savePlans()
    {
        System.out.println("Saving . . .");
        String saveData = "";

        for(Plan plan: plans)
        {
            saveData+=plan.getDayInt()+","+plan.getStartInt()+","+plan.getDuration()+",";
        }

        try {
            FileOutputStream fos = openFileOutput("ConverStationPlans", MODE_PRIVATE);
            fos.write(saveData.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
