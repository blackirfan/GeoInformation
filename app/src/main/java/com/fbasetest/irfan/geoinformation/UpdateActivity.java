package com.fbasetest.irfan.geoinformation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText etName, etComment, etDate, etTime,etLatitude,etLongitude;
    DBHelper dbHelper;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        etName =findViewById(R.id.et_name);
        etComment =findViewById(R.id.et_comment);
        etDate =findViewById(R.id.et_date);
        etTime =findViewById(R.id.et_time);
        etLatitude =findViewById(R.id.et_latitude);
        etLongitude =findViewById(R.id.et_longitude);


        dbHelper  = new DBHelper(this);
        id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");
        String comment = getIntent().getStringExtra("comment");
        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");
        String latitude = getIntent().getStringExtra("latitude");
        String longitude = getIntent().getStringExtra("longitude");

        etName.setText(name);
        etComment.setText(comment);
        etDate.setText(date);
        etTime.setText(time);
        etLatitude.setText(latitude);
        etLongitude.setText(longitude);


    }

    public void update(View view) {


        String name = etName.getText().toString();
        String comment = etComment.getText().toString();
        String date = etDate.getText().toString();
        String time = etTime.getText().toString();
        String latitude = etLatitude.getText().toString();
        String longitude = etLongitude.getText().toString();




        dbHelper.updatePlaceData(name,comment,date,time,latitude,longitude,id);
        finish();
        Intent intent = new Intent(getApplicationContext(), SaveNewPlace.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_LONG).show();
    }
}
