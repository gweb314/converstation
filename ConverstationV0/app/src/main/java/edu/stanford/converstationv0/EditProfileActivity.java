package edu.stanford.converstationv0;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

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
        picLayout = findViewById(R.id.picLayout);

        if(UserManager.userExists())
        {
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
        else
        {
            editProfilePic.setImage("userPic");
            cancel =  findViewById(R.id.Cancel);
            cancel.setClickable(false);
            cancel.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            cancel.setTextColor(getResources().getColor(android.R.color.secondary_text_dark));
        }

        if(getIntent().getStringExtra("pic") != null)
        {
            ResourceManager.addNewProfilePic("userPic", ResourceManager.getProfilePic(getIntent().getStringExtra("pic")));
        }

        if(interests == null) interests = new ArrayList<String>();
        else
        {
            editName.setText(name);
            editPronouns.setText(pronouns);
            editMajor.setText(major);
            editMinor.setText(minor);
            editEmail.setText(email);
            editInterests.setTags(interests);
        }
    }

    static String name, pronouns, major, minor, email;
    static ArrayList<String> interests;
    EditText editName, editPronouns, editMajor, editMinor, editEmail;
    ProfilePicView editProfilePic;
    TagEditView editInterests;
    String image = "userPic";
    TextInputLayout nameLayout, picLayout;
    Button cancel;

    public void saveProfile(View view) {
        boolean firstTime = !UserManager.userExists();
        boolean invalid = false;
        if(!validName(editName.getText().toString()))
        {
            nameLayout.setErrorEnabled(true);
            nameLayout.setError("Name is Required");
            invalid = true;
        }
        else
        {
            nameLayout.setErrorEnabled(false);
        }

        if(!ResourceManager.picExists("userPic"))
        {
            picLayout.setErrorEnabled(true);
            picLayout.setError("Profile Pic is Required");
            invalid = true;
        }
        else
        {
            picLayout.setErrorEnabled(false);
        }

        if(invalid) return;

        User user = new User(editName.getText().toString(),
                editPronouns.getText().toString(),
                editMajor.getText().toString(),
                editMinor.getText().toString(),
                editEmail.getText().toString(),
                image,
                editInterests.getTags());
        UserManager.setUser(user);

        interests = null;

        Intent intent;
        if(firstTime)
        {
            intent = new Intent(this, MainActivity.class);
        }
        else
        {
            intent = new Intent(this, MyProfileActivity.class);
        }

        Toast toast = Toast.makeText(this, "Profile Saved", Toast.LENGTH_LONG);
        toast.show();
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

    private static int RESULT_LOAD_IMAGE = 1;

    public void changePic(View view)
    {
        name = editName.getText().toString();
        pronouns = editPronouns.getText().toString();
        major = editMajor.getText().toString();
        minor = editMinor.getText().toString();
        email = editEmail.getText().toString();
        interests = editInterests.getTags();

        Intent intent = new Intent(this, PhotoPickerActivity.class);

        startActivity(intent);

        /*
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
        } else {
            accessCamera();
        }
         */
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    accessCamera();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    private void accessCamera()
    {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Bitmap newImage = (BitmapFactory.decodeFile(picturePath));
            ResourceManager.addNewProfilePic("userPic", newImage);
            editProfilePic.setImage("userPic");
        }


    }
}
