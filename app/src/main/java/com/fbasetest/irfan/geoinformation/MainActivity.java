package com.fbasetest.irfan.geoinformation;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.Calendar;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    EditText etName, etComment, etDate, etTime;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_name);
        etComment = findViewById(R.id.et_comment);
//        etDate = findViewById(R.id.et_date);
//        etTime = findViewById(R.id.et_time);
        dbHelper = new DBHelper(getApplicationContext());


    }

    public void SaveNewPlace(View view) {

        gpsTracer g = new gpsTracer(getApplicationContext());
        Location l = g.getLocation();
        if (l != null) {
            double lat = l.getLatitude();
            double lon = l.getLongitude();


            //date part

            final Calendar calendar = Calendar.getInstance();


            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);

            int mmN = mm+1;


            int dd = calendar.get(Calendar.DAY_OF_MONTH);

            String yyS = Integer.toString(yy);
            String mmS = Integer.toString(mmN);
            String ddS = Integer.toString(dd);
            String date = yyS+"/"+mmS+"/"+ddS;



//            part time

            int hourR = calendar.get(Calendar.HOUR_OF_DAY);
            int minuteR = calendar.get(Calendar.MINUTE);



           int hour = hourR;
          int  minutes = minuteR;
            String timeSet = "";
            if (hour > 12) {
                hour -= 12;
                timeSet = "PM";
            } else if (hour == 0) {
                hour += 12;
                timeSet = "AM";
            } else if (hour == 12){
                timeSet = "PM";
            }else{
                timeSet = "AM";
            }

            String min = "";
            if (minutes < 10)
                min = "0" + minutes ;
            else
                min = String.valueOf(minutes);

            // Append in a StringBuilder
            String time = new StringBuilder().append(hour).append(':')
                    .append(min ).append(" ").append(timeSet).toString();







            String placeName = etName.getText().toString();
            String comment = etComment.getText().toString();

//            String time = etTime.getText().toString();
            String latitude = Double.toString(lat);
            String longitude = Double.toString(lon);


            dbHelper.insertLocationData(placeName, comment, date, time, latitude, longitude);

            Toast.makeText(getApplicationContext(), "data is insert in database", Toast.LENGTH_LONG).show();


        }

//    public void GPSvalue(View view) {
//            gpsTracer g = new gpsTracer(getApplicationContext());
//            Location l = g.getLocation();
//            if (l != null) {
//                double lat = l.getLatitude();
//                double lon = l.getLongitude();
//
//
////            String strLongitude = Location.convert(l.getLongitude(), l.FORMAT_DEGREES);
////            String strLatitude = Location.convert(l.getLatitude(), l.FORMAT_DEGREES);
//
//
//                Toast.makeText(getApplicationContext(), "Lat: " + lat + " \n lon: " + lon, Toast.LENGTH_LONG).show();
//
//
////            Toast.makeText(getApplicationContext(), "Lat: " + strLatitude + " \n lon: " + strLongitude, Toast.LENGTH_LONG).show();
//
//            }
//        }



    }


    public void showAllData(View view) {

        startActivity(new Intent(getApplicationContext(), SaveNewPlace.class));
    }





}

