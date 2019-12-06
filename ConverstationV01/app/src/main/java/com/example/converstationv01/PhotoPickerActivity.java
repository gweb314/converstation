package com.example.converstationv01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PhotoPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_picker);

        photoPicker = findViewById(R.id.photoPicker);
        photoPicker.setPickButton((Button) findViewById(R.id.selectPhoto));
    }

    PhotoPickerView photoPicker;

    public void selectPhoto(View view)
    {
        Intent intent = new Intent(this, EditProfileActivity.class);
        intent.putExtra("pic", photoPicker.getSelected());

        startActivity(intent);
    }
}
