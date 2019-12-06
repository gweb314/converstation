package edu.stanford.converstationv0;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
