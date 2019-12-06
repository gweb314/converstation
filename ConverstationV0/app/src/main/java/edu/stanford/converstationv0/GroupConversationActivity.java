package edu.stanford.converstationv0;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupConversationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_conversation);
        tagList = findViewById(R.id.tagListView);
        tagString = getIntent().getStringExtra("tags");
        ArrayList<String> al = new ArrayList<String>();

        ArrayList<String> usersStringList = getIntent().getStringArrayListExtra("partners");
        ArrayList<User> usersList = new ArrayList<User>();

        for (String userString : usersStringList ) {
            usersList.add(new User(userString));
        }

        ArrayAdapter<User> groupConvoAdapter = new GroupConversationActivity.userArrayAdapter(this, 0, usersList);

        ListView groupConvoContainer = findViewById(R.id.group_members_container);
        groupConvoContainer.setAdapter(groupConvoAdapter);

        if (tagString != null) {
            for (String s : tagString.split(" ")) {
                al.add(s);
            }
            tagList.setTags(al);
        } else {
            for (int i = 0; i < 2; i++) {
                String interest = usersList.get(0).getInterests().get(i);
                al.add(interest);
            }
        }

        tagList.setTags(al);
    }

    TagListView tagList;
    String tagString;

    public void viewGroupMembers(View view) {
        Intent intent = new Intent(this, GroupMembersActivity.class);

        startActivity(intent);
    }

    public void leaveConvo(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to leave the conversation?");
        LayoutInflater inflater = getLayoutInflater();
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(GroupConversationActivity.this, MainActivity.class);
                        Toast.makeText(GroupConversationActivity.this, "Conversation Left", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        finish();
                    }
                });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to leave the conversation?");
        LayoutInflater inflater = getLayoutInflater();
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(GroupConversationActivity.this, MainActivity.class);
                        Toast.makeText(GroupConversationActivity.this, "Conversation Left", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        finish();
                    }
                });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    class userArrayAdapter extends ArrayAdapter<User> {

        private Context context;
        private List<User> users;

        //constructor, call on creation
        public userArrayAdapter(Context context, int resource, ArrayList<User> objects) {
            super(context, resource, objects);

            this.context = context;
            this.users = objects;
        }

        //called when rendering the list
        public View getView(int position, View convertView, ViewGroup parent) {

            //get the property we are displaying
            User currentUser = users.get(position);

            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.group_member, null);

            TextView nameView = (TextView) view.findViewById(R.id.name_view);
            ProfilePicView picView = view.findViewById(R.id.pic_view);

            nameView.setText(currentUser.getName());
            picView.setImage(currentUser.getImage());

            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(GroupConversationActivity.this, PartnerProfile.class);
                    myIntent.putExtra("partner", currentUser.toString());
                    startActivity(myIntent);
                }
            });

            return view;
        }
    }
}
