package edu.stanford.converstationv0;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        Button start_convo_button = findViewById(R.id.start_convo_button);
        locationNameView.setText(locationName);

        ArrayList<User> usersList = new ArrayList<User>();

        switch(locationName) {
            case "The Oval":
                locationImageView.setImageDrawable((Drawable)getResources().getDrawable(R.drawable.stanford_oval_profile));
                start_convo_button.setText("Get Directions");
                break;
            case "Garden by MemChu":
                locationImageView.setImageDrawable((Drawable)getResources().getDrawable(R.drawable.memorial_church_garden_profile));
                start_convo_button.setText("Get Directions");
                break;
            case "Roble Arts Gym Courtyard":
                locationImageView.setImageDrawable((Drawable)getResources().getDrawable(R.drawable.roble_court_profile));
                usersList.add(new User("Ben", "He/him", "TAPS", "", "ben@stanford.edu", "random_user_ben", new ArrayList<String>() {{add("Musicals");add("DanceHistory"); }}));
                start_convo_button.setText("Get Directions");
                break;
            case "Tresidder Union":
                locationImageView.setImageDrawable((Drawable)getResources().getDrawable(R.drawable.tresidder_union_profile));
                usersList.add(new User("Jay", "They/Then", "Computer Science", "Econ", "jay@stanford.edu", "random_user_jay", new ArrayList<String>() {{add("Chess");add("AI"); }}));
                usersList.add(new User("Hope", "She/Her", "HumBio", "", "hope@stanford.edu", "random_user_hope", new ArrayList<String>() {{add("Baking");add("TGBBS"); }}));
                usersList.add(new User("Neil", "He/him", "English", "German Studies", "neil@stanford.edu", "random_user_neil", new ArrayList<String>() {{add("HarryPotter");add("Movies"); }}));
                start_convo_button.setText("Get Directions");
                break;
            case "The Clocktower":
                locationImageView.setImageDrawable((Drawable)getResources().getDrawable(R.drawable.clock_tower_profile));
                start_convo_button.setText("Get Directions");
                break;
            case "Stanford D.School":
                locationImageView.setImageDrawable((Drawable)getResources().getDrawable(R.drawable.stanford_d_school_profile));
                usersList.add(new User(1));
                start_convo_button.setText("Start Group Conversation");
                break;
            default:
                locationImageView.setImageDrawable((Drawable)getResources().getDrawable(R.drawable.stanford_oval_profile));
                start_convo_button.setText("Get Directions");
        }

        ArrayAdapter<User> groupConvoAdapter = new ConverStationProfile.groupConvoArrayAdapter(this, 0, usersList);

        ListView groupConvoContainer = findViewById(R.id.converstation_profile_container);
        groupConvoContainer.setAdapter(groupConvoAdapter);
    }

    Button start_convo_button;

    class groupConvoArrayAdapter extends ArrayAdapter<User> {

        private Context context;
        private List<User> groupConvos;

        //constructor, call on creation
        public groupConvoArrayAdapter(Context context, int resource, ArrayList<User> objects) {
            super(context, resource, objects);

            this.context = context;
            this.groupConvos = objects;
        }

        //called when rendering the list
        public View getView(int position, View convertView, ViewGroup parent) {

            //get the property we are displaying
            User currentUser = groupConvos.get(position);

            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.group_conversation, null);

            ProfilePicView profileView = view.findViewById(R.id.host_profile);
            profileView.setImage(currentUser.getImage());

            TextView participantsView = view.findViewById(R.id.participants_TextView);
            participantsView.setText(currentUser.getName());

            Random rand = new Random();
            rand = new Random();
            String number = String.valueOf(rand.nextInt(6));

            if (locationName.equals("Stanford D.School")) {
                number = "1";
            }

            TextView numParticipantsView = view.findViewById(R.id.num_participants_TextView);
            numParticipantsView.setText(" + " + number + " others are talking about");
            if (number.equals("1")) {
                numParticipantsView.setText(" + " + number + " other are talking about");
            }

            TextView topic1View = view.findViewById(R.id.topic1_textView);
            topic1View.setText(currentUser.getInterests().get(0));

            TextView topic2View = view.findViewById(R.id.topic2_textView);
            topic2View.setText(currentUser.getInterests().get(currentUser.getInterests().size()-1));

            return view;
        }
    }

    public void viewLocationInfo(View v) {
        Intent myIntent = new Intent(ConverStationProfile.this, ConverStationInfo.class);
        myIntent.putExtra("LocationName", locationName);
        ConverStationProfile.this.startActivity(myIntent);
    }

    public void viewDirections(View v) {
        if (locationName.equals("Stanford D.School")) {


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Set Conversation Topic");
            builder.setMessage("Type a few things you would like to talk about in your group conversation");
            LayoutInflater inflater = getLayoutInflater();
            final View dialogLayout = inflater.inflate(R.layout.topic_input_dialog, null);
            builder.setView(dialogLayout);
            builder.setPositiveButton("Create",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent intent = new Intent(ConverStationProfile.this, GroupConversationActivity.class);
                            Toast.makeText(ConverStationProfile.this, "Conversation Created", Toast.LENGTH_LONG).show();
                            ArrayList<String> partners = new ArrayList<String>();
                            partners.add(new User(0).toString());
                            partners.add(new User(2).toString());
                            intent.putExtra("partners", partners);
                            intent.putExtra("individual", "false");
                            TagEditView tags = dialogLayout.findViewById(R.id.tagEditView);
                            intent.putExtra("tags", tags.getTagsString());
                            ConverStationProfile.this.startActivity(intent);
                        }
                    });
            builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        else {
            Intent myIntent = new Intent(ConverStationProfile.this, MapsActivity.class);
            myIntent.putExtra("LocationName", locationName);
            ConverStationProfile.this.startActivity(myIntent);
        }
    }

    public void joinExisting(View v) {
        if (locationName.equals("Stanford D.School")) {
            Intent myIntent = new Intent(ConverStationProfile.this, ArrivedAtConversation.class);
            myIntent.putExtra("partner", new User(1).toString());
            myIntent.putExtra("individual", "false");
            ConverStationProfile.this.startActivity(myIntent);
        }
        else {
            Intent myIntent = new Intent(ConverStationProfile.this, MapsActivity.class);
            myIntent.putExtra("LocationName", locationName);
            ConverStationProfile.this.startActivity(myIntent);
        }
    }
}
