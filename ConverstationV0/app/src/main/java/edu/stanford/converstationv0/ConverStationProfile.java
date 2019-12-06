package edu.stanford.converstationv0;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConverStationProfile extends AppCompatActivity {

    public String locationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conver_station_profile);

        locationName = (String) getIntent().getStringExtra("LocationName");
        TextView locationNameView = findViewById(R.id.converstation_TextView);
        ImageView locationImageView = findViewById(R.id.converstation_ImageView);
        ArrayList<GroupConversation> conversationsList;
        ArrayList<String> names = new ArrayList<String>() {{
            add("Alex");
            add("Alicia");
            add("Ben");
            add("Brian");
            add("Eric");
            add("Hope");
            add("Jay");
            add("Josh");
            add("Julia");
            add("Milen");
            add("Neil");
            add("Nick");
            add("Sofia");
            add("Sophie");
        }};

        locationNameView.setText(locationName);

        Random rand = new Random();
        int nameIndex;
        String currentName;
        String number;

        conversationsList = new ArrayList<GroupConversation>();

        switch(locationName) {
            case "The Oval":
                locationImageView.setImageDrawable((Drawable)getResources().getDrawable(R.drawable.stanford_oval_profile));
                conversationsList = new ArrayList<GroupConversation>();
                break;
            case "Garden by MemChu":
                locationImageView.setImageDrawable((Drawable)getResources().getDrawable(R.drawable.memorial_church_garden_profile));
                conversationsList = new ArrayList<GroupConversation>();
                break;
            case "Roble Arts Gym Courtyard":
                locationImageView.setImageDrawable((Drawable)getResources().getDrawable(R.drawable.roble_court_profile));

                nameIndex = rand.nextInt(names.size());

                currentName = names.get(nameIndex);
                names.remove(nameIndex);

                rand = new Random();

                number = String.valueOf(rand.nextInt(6));

                conversationsList = new ArrayList<GroupConversation>();
                conversationsList.add(
                        new GroupConversation(
                                currentName,
                                number,
                                new ArrayList<String>() {{
                                    add("Musicals");
                                    add("DanceHistory");
                                }}));

                break;
            case "Tresidder Union":
                locationImageView.setImageDrawable((Drawable)getResources().getDrawable(R.drawable.tresidder_union_profile));
                conversationsList = new ArrayList<GroupConversation>();
                ArrayList<ArrayList<String>> topics = new ArrayList<ArrayList<String>>() {{
                    add(new ArrayList<String>() {{
                        add("Chess");
                        add("AI");
                    }});
                    add(new ArrayList<String>() {{
                        add("Baking");
                        add("TGBBS");
                    }});
                    add(new ArrayList<String>() {{
                        add("HarryPotter");
                        add("Movies");
                    }});
                }};
                for (int i = 0; i < 3; i++) {
                    rand = new Random();
                    nameIndex = rand.nextInt(names.size());

                    currentName = names.get(nameIndex);
                    names.remove(nameIndex);

                    rand = new Random();

                    number = String.valueOf(rand.nextInt(6));
                    conversationsList.add(new GroupConversation(currentName, number, topics.get(i)));
                }
                break;
            case "The Clocktower":
                locationImageView.setImageDrawable((Drawable)getResources().getDrawable(R.drawable.clock_tower_profile));
                break;
            case "Stanford D.School":
                locationImageView.setImageDrawable((Drawable)getResources().getDrawable(R.drawable.clock_tower_profile));
                conversationsList = new ArrayList<GroupConversation>();
                conversationsList.add(
                        new GroupConversation(
                                "Fiona",
                                "1",
                                new ArrayList<String>() {{
                                    add("HCI");
                                    add("AndroidDev");
                                }}));

                break;
            default:
                locationImageView.setImageDrawable((Drawable)getResources().getDrawable(R.drawable.stanford_oval_profile));
                conversationsList = new ArrayList<GroupConversation>();
        }

        ArrayAdapter<GroupConversation> groupConvoAdapter = new ConverStationProfile.groupConvoArrayAdapter(this, 0, conversationsList);

        ListView groupConvoContainer = findViewById(R.id.converstation_profile_container);
        groupConvoContainer.setAdapter(groupConvoAdapter);
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

    class groupConvoArrayAdapter extends ArrayAdapter<GroupConversation> {

        private Context context;
        private List<GroupConversation> groupConvos;

        //constructor, call on creation
        public groupConvoArrayAdapter(Context context, int resource, ArrayList<GroupConversation> objects) {
            super(context, resource, objects);

            this.context = context;
            this.groupConvos = objects;
        }

        //called when rendering the list
        public View getView(int position, View convertView, ViewGroup parent) {

            //get the property we are displaying
            GroupConversation currentConvo = groupConvos.get(position);

            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.group_conversation, null);

            ProfilePicView profileView = view.findViewById(R.id.host_profile);
            profileView.setImage(currentConvo.hostName);

            TextView participantsView = view.findViewById(R.id.participants_TextView);
            participantsView.setText(currentConvo.hostName);

            TextView numParticipantsView = view.findViewById(R.id.num_participants_TextView);
            numParticipantsView.setText(" + " + currentConvo.number + " others are talking about");
            if (currentConvo.number == "1") {
                numParticipantsView.setText(" + " + currentConvo.number + " other are talking about");
            }

            TextView topic1View = view.findViewById(R.id.topic1_textView);
            topic1View.setText(currentConvo.topics.get(0));

            TextView topic2View = view.findViewById(R.id.topic2_textView);
            topic2View.setText(currentConvo.topics.get(1));

            return view;
        }
    }

    public void viewLocationInfo(View v) {
        Intent myIntent = new Intent(ConverStationProfile.this, ConverStationInfo.class);
        myIntent.putExtra("LocationName", locationName);
        ConverStationProfile.this.startActivity(myIntent);
    }

    public void viewDirections(View v) {
        Intent myIntent = new Intent(ConverStationProfile.this, MapsActivity.class);
        myIntent.putExtra("LocationName", locationName);
        ConverStationProfile.this.startActivity(myIntent);
    }
}
