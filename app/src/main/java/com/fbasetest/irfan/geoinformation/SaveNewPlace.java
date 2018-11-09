package com.fbasetest.irfan.geoinformation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.SearchView;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
//import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class SaveNewPlace extends AppCompatActivity {

    ListView listView;
  private   PlaceAdapter placeAdapter;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_new_place);


        listView = findViewById(R.id.list_view);
        dbHelper = new DBHelper(this);
        final ArrayList<PlaceModelClass> dataList = dbHelper.getAllPlaceData();
        placeAdapter =new PlaceAdapter(this,R.layout.place_raw_two,dataList);
        listView.setAdapter(placeAdapter);




//        for delete part


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {




                PlaceModelClass  modelClass = (PlaceModelClass) adapterView.getItemAtPosition(position);

                int id = modelClass.getId();
                String strID = String.valueOf(id);
                dbHelper.deletePlaceRow(strID);
                finish();

                Toast.makeText(getApplicationContext(), "Delete Successfully", Toast.LENGTH_LONG).show();
                return false;
            }
        });

        //for update part



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                PlaceModelClass modelClass = (PlaceModelClass) adapterView.getItemAtPosition(position);

                String id = String.valueOf(modelClass.getId());
                String name = modelClass.getPlaceName();
                String comment = modelClass.getComment();
                String date = modelClass.getDate();
                String time = modelClass.getTime();

                String latitude = modelClass.getLatitude();
                String longitude = modelClass.getLongitude();

                Intent intent = new Intent(SaveNewPlace.this, UpdateActivity.class);
                intent.putExtra("id", id);

                intent.putExtra("name",name );

                intent.putExtra("comment", comment);
                intent.putExtra("date", date);
                intent.putExtra("time", time);
                intent.putExtra("latitude", latitude);

                intent.putExtra("longitude",longitude);


                startActivity(intent);
                finish();

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)){
                    placeAdapter.filter("");
                    listView.clearTextFilter();

                }else {
                    placeAdapter.filter(newText);
                }
                return true;
            }
        });

        return true;
    }


//    AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
//            //set message, title, and icon
//            .setTitle("Delete")
//            .setMessage("Do you want to Delete")
//            .setIcon(R.drawable.delete)
//
//            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//
//                public void onClick(DialogInterface dialog, int whichButton) {
//                    //your deleting code
//                    dialog.dismiss();
//                }
//
//            })
//
//
//
//            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//
//                    dialog.dismiss();
//
//                }
//            })
//            .create();
////        return myQuittingDialogBox;
//
//}


}
