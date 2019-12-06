package edu.stanford.converstationv0;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NavigationSteps extends AppCompatActivity {

    StepSet mRouteInfo;
    ArrayList<Step> navSteps;
    List<String> distances;
    List<String> iconCodes;
    List<String> stepTexts;
    TextView converStationNameView;
    ImageView converStationPicView;
    TextView timeToDestView;
    TextView distToDestView;

    List<Drawable> directionIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_steps);

        mRouteInfo = (StepSet) getIntent().getSerializableExtra("StepSet");

        converStationNameView = findViewById(R.id.destination_TextView);
        converStationPicView = findViewById(R.id.destination_ImageView);
        timeToDestView = findViewById(R.id.eTT_TextView);
        distToDestView = findViewById(R.id.tD_TextView);

        converStationNameView.setText(mRouteInfo.converStationName);
        timeToDestView.setText(mRouteInfo.expectedTravelTime);
        distToDestView.setText("(" + mRouteInfo.travelDistance + ")");

        directionIcons = new ArrayList<Drawable>();

        switch(mRouteInfo.converStationName) {
            case "The Oval":
                converStationPicView.setImageDrawable((Drawable)getResources().getDrawable(R.drawable.stanford_oval_profile));
                stepTexts = new ArrayList<String>(){{
                    add("Head east toward Escondido Rd/Escondido Mall");
                    add("Turn right");
                    add("Turn left");
                    add("Turn left");
                    add("Turn right");
                    add("Turn left");
                    add("Turn right");
                    add("Turn left");
                    add("Turn left at Jane Stanford Way/Serra St");
                    add("Turn right");
                    add("Turn left");
                    add("Turn left");
                    add("Turn right (Destination will be on the left)");
                }};
                distances = new ArrayList<String>(){{
                    add("381 ft");
                    add("46 ft");
                    add("279 ft");
                    add("121 ft");
                    add("148 ft");
                    add("144 ft");
                    add("33 ft");
                    add("187 ft");
                    add("26 ft");
                    add("43 ft");
                    add("262 ft");
                    add("30 ft");
                    add("23 ft");
                }};
                iconCodes = new ArrayList<String>(){{
                    add("cs");
                    add("tr");
                    add("tl");
                    add("tl");
                    add("tr");
                    add("tl");
                    add("tr");
                    add("tl");
                    add("tl");
                    add("tr");
                    add("tl");
                    add("tl");
                    add("tr");
                }};
                break;
            case "Garden by MemChu":
                converStationPicView.setImageDrawable((Drawable)getResources().getDrawable(R.drawable.memorial_church_garden_profile));
                stepTexts = new ArrayList<String>() {{
                    add("Head east");
                    add("Slight left toward Escondido Mall");
                    add("Slight right onto Escondido Mall");
                    add("Turn left");
                    add("Turn right");
                }};
                distances = new ArrayList<String>() {{
                    add("240 ft");
                    add("79 ft");
                    add("33 ft");
                    add("89 ft");
                    add("157 ft");
                }};
                iconCodes = new ArrayList<String>() {{
                    add("cs");
                    add("vl");
                    add("vr");
                    add("tl");
                    add("tr");
                }};
                break;
            case "Roble Arts Gym Courtyard":
                converStationPicView.setImageDrawable((Drawable)getResources().getDrawable(R.drawable.roble_court_profile));
                stepTexts = new ArrayList<String>() {{
                    add("Head west");
                    add("Turn left toward Panama Mall");
                    add("Turn right onto Panama Mall");
                    add("Turn left onto Samuel Morris Way");
                    add("Turn right");
                    add("Turn right");
                }};
                distances = new ArrayList<String>() {{
                    add("108 ft");
                    add("33 ft");
                    add("0.1 mi");
                    add("266 ft");
                    add("138 ft");
                    add("52 ft");
                }};
                iconCodes = new ArrayList<String>() {{
                    add("cs");
                    add("tl");
                    add("tr");
                    add("tl");
                    add("tr");
                    add("tr");
                }};
                break;
            case "Tresidder Union":
                converStationPicView.setImageDrawable((Drawable)getResources().getDrawable(R.drawable.tresidder_union_profile));
                stepTexts = new ArrayList<String>() {{
                    add("Head east toward Duena St");
                    add("Turn right onto Duena St");
                    add("Turn left at Santa Teresa St");
                    add("Turn right");
                    add("Turn right");
                }};
                distances = new ArrayList<String>() {{
                    add("354 ft");
                    add("390 ft");
                    add("69 ft");
                    add("115 ft");
                    add("112 ft");
                }};
                iconCodes = new ArrayList<String>() {{
                    add("cs");
                    add("tr");
                    add("tl");
                    add("tr");
                    add("tr");
                }};
                break;
            case "The Clocktower":
                converStationPicView.setImageDrawable((Drawable)getResources().getDrawable(R.drawable.clock_tower_profile));
                stepTexts = new ArrayList<String>() {{
                    add("Head east");
                    add("Slight left toward Escondido Mall");
                    add("Slight right onto Escondido Mall");
                }};
                distances = new ArrayList<String>() {{
                    add("240 ft");
                    add("79 ft");
                    add("0.1 mi");
                }};
                iconCodes = new ArrayList<String>() {{
                    add("cs");
                    add("vl");
                    add("vr");
                }};
                break;
            default:
                converStationPicView.setImageDrawable((Drawable)getResources().getDrawable(R.drawable.stanford_oval_profile));
                stepTexts = new ArrayList<String>();
                distances = new ArrayList<String>();
                iconCodes = new ArrayList<String>();
        }

        for (int i = 0; i < iconCodes.size(); i++) {
            switch(iconCodes.get(i)) {
                case "cs":
                    directionIcons.add((Drawable)getResources().getDrawable(R.drawable.continue_straight));
                    break;
                case "vl":
                    directionIcons.add((Drawable)getResources().getDrawable(R.drawable.veer_left));
                    break;
                case "vr":
                    directionIcons.add((Drawable)getResources().getDrawable(R.drawable.veer_right));
                    break;
                case "tl":
                    directionIcons.add((Drawable)getResources().getDrawable(R.drawable.turn_left));
                    break;
                case "tr":
                    directionIcons.add((Drawable)getResources().getDrawable(R.drawable.turn_right));
                    break;
                default:
                    Log.d("Nav Debugging", "Something has gone horribly wrong.  (Mistyped icon code in nav step list)");
            }
        }

        navSteps = new ArrayList<Step>();

        for(int i = 0; i < distances.size(); i++) {
            Step currentStep = new Step(directionIcons.get(i), stepTexts.get(i), distances.get(i));
            navSteps.add(currentStep);
        }

        ArrayAdapter<Step> adapter = new stepArrayAdapter(this, 0, navSteps);

        ListView listView = (ListView) findViewById(R.id.nav_step_list_view);
        listView.setAdapter(adapter);

    }

    public class Step {
        public Drawable directionIcon;
        public String stepText;
        public String stepDistance;

        public Step(Drawable dI, String sT, String sD) {
            directionIcon = dI;
            stepText = sT;
            stepDistance = sD;
        }
    }

    class stepArrayAdapter extends ArrayAdapter<Step> {

        private Context context;
        private List<Step> steps;

        //constructor, call on creation
        public stepArrayAdapter(Context context, int resource, ArrayList<Step> objects) {
            super(context, resource, objects);

            this.context = context;
            this.steps = objects;
        }

        //called when rendering the list
        public View getView(int position, View convertView, ViewGroup parent) {

            //get the property we are displaying
            Step currentStep = steps.get(position);

            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.nav_step, null);

            TextView stepView = (TextView) view.findViewById(R.id.step_TextView);
            TextView distanceView = (TextView) view.findViewById(R.id.distance_TextView);
            ImageView directionIconView = (ImageView) view.findViewById(R.id.direction_icon_view);

            stepView.setText(currentStep.stepText);
            distanceView.setText(currentStep.stepDistance);
            directionIconView.setImageDrawable(currentStep.directionIcon);

            return view;
        }
    }

    public void backToMap(View v) {
        Intent myIntent = new Intent(NavigationSteps.this, MapsActivity.class);
        NavigationSteps.this.startActivity(myIntent);
    }

    public void viewLocationInfo(View v) {
        Intent myIntent = new Intent(NavigationSteps.this, ConverStationInfo.class);
        myIntent.putExtra("LocationName", mRouteInfo.converStationName);
        NavigationSteps.this.startActivity(myIntent);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

}
