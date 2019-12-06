package edu.stanford.converstationv0;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class ConverStationInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conver_station_info);

        String locationName = (String) getIntent().getStringExtra("LocationName");
        TextView locationNameView = findViewById(R.id.converstation_TextView);
        ImageView locationImageView = findViewById(R.id.converstation_ImageView);
        ArrayList<String> featuresList;
        ArrayList<Drawable> featuresIcons;

        locationNameView.setText(locationName);

        Random rand = new Random();
        int nameIndex;
        String currentName;
        String number;

        switch (locationName) {
            case "The Oval":
                locationImageView.setImageDrawable((Drawable) getResources().getDrawable(R.drawable.stanford_oval_profile));
                featuresList = new ArrayList<String>() {{
                    add("Outdoors");
                    add("Scenic");
                }};
                featuresIcons = new ArrayList<Drawable>() {{
                    add(getResources().getDrawable(R.drawable.outdoors_icon));
                    add(getResources().getDrawable(R.drawable.scenic_icon));
                }};
                break;
            case "Garden by MemChu":
                locationImageView.setImageDrawable((Drawable) getResources().getDrawable(R.drawable.memorial_church_garden_profile));
                featuresList = new ArrayList<String>() {{
                    add("Outdoors");
                    add("Nearby Restrooms");
                    add("Nearby Waterfountain");
                    add("Scenic");
                }};
                featuresIcons = new ArrayList<Drawable>() {{
                    add(getResources().getDrawable(R.drawable.outdoors_icon));
                    add(getResources().getDrawable(R.drawable.restrooms_icon));
                    add(getResources().getDrawable(R.drawable.waterfountains_icon));
                    add(getResources().getDrawable(R.drawable.scenic_icon));
                }};
                break;
            case "Roble Arts Gym Courtyard":
                locationImageView.setImageDrawable((Drawable) getResources().getDrawable(R.drawable.roble_court_profile));
                featuresList = new ArrayList<String>() {{
                    add("Outdoors");
                    add("Nearby Restrooms");
                    add("Nearby Waterfountain");
                    add("Scenic");
                }};
                featuresIcons = new ArrayList<Drawable>() {{
                    add(getResources().getDrawable(R.drawable.outdoors_icon));
                    add(getResources().getDrawable(R.drawable.restrooms_icon));
                    add(getResources().getDrawable(R.drawable.waterfountains_icon));
                    add(getResources().getDrawable(R.drawable.scenic_icon));
                }};
                break;
            case "Tresidder Union":
                locationImageView.setImageDrawable((Drawable) getResources().getDrawable(R.drawable.tresidder_union_profile));
                featuresList = new ArrayList<String>() {{
                    add("Indoor/Outdoor");
                    add("Nearby Restrooms");
                    add("Nearby Dining");
                }};
                featuresIcons = new ArrayList<Drawable>() {{
                    add(getResources().getDrawable(R.drawable.indoors_outdoors_icon));
                    add(getResources().getDrawable(R.drawable.restrooms_icon));
                    add(getResources().getDrawable(R.drawable.dining_icon));
                }};
                break;
            case "The Clocktower":
                locationImageView.setImageDrawable((Drawable) getResources().getDrawable(R.drawable.clock_tower_profile));
                featuresList = new ArrayList<String>() {{
                    add("Outdoors");
                    add("Nearby Restrooms");
                }};
                featuresIcons = new ArrayList<Drawable>() {{
                    add(getResources().getDrawable(R.drawable.outdoors_icon));
                    add(getResources().getDrawable(R.drawable.restrooms_icon));
                }};
                break;
            case "Stanford D.School":
                locationImageView.setImageDrawable((Drawable) getResources().getDrawable(R.drawable.clock_tower_profile));
                featuresList = new ArrayList<String>() {{
                    add("Indoors");
                    add("Nearby Restrooms");
                    add("Nearby Waterfountain");
                }};
                featuresIcons = new ArrayList<Drawable>() {{
                    add(getResources().getDrawable(R.drawable.indoors_icon));
                    add(getResources().getDrawable(R.drawable.restrooms_icon));
                    add(getResources().getDrawable(R.drawable.waterfountains_icon));
                }};
                break;
            default:
                locationImageView.setImageDrawable((Drawable) getResources().getDrawable(R.drawable.stanford_oval_profile));
                featuresList = new ArrayList<String>();
                featuresIcons = new ArrayList<Drawable>();
        }

        ArrayList<Feature> featureObjects = new ArrayList<Feature>();

        for (int i = 0; i < featuresList.size(); i++) {
            Feature currentFeature = new Feature(featuresList.get(i), featuresIcons.get(i));
            featureObjects.add(currentFeature);
        }

        ArrayAdapter<Feature> adapter = new ConverStationInfo.featureArrayAdapter(this, 0, featureObjects);

        ListView featureContainer = findViewById(R.id.converstation_feature_container);
        featureContainer.setAdapter(adapter);
    }

    public class GroupConversation {

        String hostName;
        String number;
        ArrayList<String> topics;

        public GroupConversation(String name, String num, ArrayList<String> t) {
            hostName = name;
            number = num;
            topics = t;
        }
    }

    public class Feature {

        Drawable featureIcon;
        String featureName;

        public Feature(String name, Drawable icon) {
            featureIcon = icon;
            featureName = name;
        }
    }


    class featureArrayAdapter extends ArrayAdapter<Feature> {

        private Context context;
        private List<Feature> features;

        //constructor, call on creation
        public featureArrayAdapter(Context context, int resource, ArrayList<Feature> objects) {
            super(context, resource, objects);

            this.context = context;
            this.features = objects;
        }

        //called when rendering the list
        public View getView(int position, View convertView, ViewGroup parent) {

            //get the property we are displaying
            Feature currentFeature = features.get(position);

            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.converstation_feature, null);

            TextView featureView = (TextView) view.findViewById(R.id.feature_description_TextView);
            ImageView featureIconView = (ImageView) view.findViewById(R.id.feature_icon_view);

            featureView.setText(currentFeature.featureName);
            featureIconView.setImageDrawable(currentFeature.featureIcon);

            return view;
        }
    }
}
