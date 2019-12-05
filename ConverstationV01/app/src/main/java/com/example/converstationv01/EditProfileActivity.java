package com.example.converstationv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editProfilePic = findViewById(R.id.editProfilePic);
        editName = findViewById(R.id.nameEdit);
        editPronouns = findViewById(R.id.pronounsEdit);
        editMajor = findViewById(R.id.majorEdit);
        editMinor = findViewById(R.id.minorEdit);
        editEmail = findViewById(R.id.emailEdit);
        editInterests = findViewById(R.id.interestsEdit);

        nameLayout = findViewById(R.id.nameLayout);

        User user = UserManager.getUser();

        editName.setText(user.getName());
        editPronouns.setText(user.getPronouns());
        editMajor.setText(user.getMajor());
        editMinor.setText(user.getMinor());
        editEmail.setText(user.getEmail());
        image = user.getImage();
        editProfilePic.setImage(image);
        editInterests.setTags(user.getInterests());

    }

    EditText editName, editPronouns, editMajor, editMinor, editEmail;
    ProfilePicView editProfilePic;
    TagEditView editInterests;
    String image = "userpic0";
    TextInputLayout nameLayout;

    public void saveProfile(View view) {
        if(!validName(editName.getText().toString()))
        {
            nameLayout.setErrorEnabled(true);
            nameLayout.setError("You need to enter a name");
            return;
        }

        User user = new User(editName.getText().toString(),
                editPronouns.getText().toString(),
                editMajor.getText().toString(),
                editMinor.getText().toString(),
                editEmail.getText().toString(),
                image,
                editInterests.getTags());
        UserManager.setUser(user);

        Intent intent = new Intent(this, MyProfileActivity.class);

        startActivity(intent);
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, MyProfileActivity.class);

        startActivity(intent);
    }

    private boolean validName(String name)
    {
        if(name.length() == 0) return false;
        for(int i = 0; i < name.length(); i++)
        {
            if(name.charAt(i) != ' ') return true;
        }
        return false;
    }
}
