package com.fbasetest.irfan.geoinformation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "db_location";
    private static final int    DB_Version = 1;


    public String Location_SQL = "CREATE TABLE "+Helper.GeoInformation_Table_SQl+" ( "+Helper.Column_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ""+Helper.Column_Place_Name+" TEXT NOT NULL, "+Helper.Column_Place_Comment+" TEXT NOT NULL, "+Helper.Column_date+" TEXT NOT NULL, "+Helper.Column_TIME+" TEXT NOT NULL, "+Helper.Column_Latitude+" TEXT NOT NULL, "+Helper.Column_Longitude+" TEXT NOT NULL)";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Location_SQL);

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // Data insert Method

    public void insertLocationData(String place,String comment,String date, String time,String latitude,String longitude){


        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Helper.Column_Place_Name, place);
        contentValues.put(Helper.Column_Place_Comment, comment);
        contentValues.put(Helper.Column_date,date);
        contentValues.put(Helper.Column_TIME,time);
        contentValues.put(Helper.Column_Latitude,latitude);
        contentValues.put(Helper.Column_Longitude,longitude);

        db.insert(Helper.GeoInformation_Table_SQl,null,contentValues);




    }


    //Data Retrieve Method

    public ArrayList<PlaceModelClass> getAllPlaceData() {


        ArrayList<PlaceModelClass> placeList = new ArrayList<>();

        String qurey = "SELECT * FROM " + Helper.GeoInformation_Table_SQl;
        SQLiteDatabase db = getReadableDatabase();


        Cursor cursor = db.rawQuery(qurey, null);
        if (cursor.moveToFirst()) {

            do {
                PlaceModelClass dataModel = new PlaceModelClass();
                dataModel.setId(cursor.getInt(0));
                dataModel.setPlaceName(cursor.getString(1));
                dataModel.setComment(cursor.getString(2));
                dataModel.setDate(cursor.getString(3));
                dataModel.setTime(cursor.getString(4));
                dataModel.setLatitude(cursor.getString(5));
                dataModel.setLongitude(cursor.getString(6));


                placeList.add(dataModel);
            } while (cursor.moveToNext());
        }

        return placeList;


    }
        // Student  row delete method
        public  void  deletePlaceRow(String id){
            SQLiteDatabase  db = getWritableDatabase();
            String whereCluse = Helper.Column_ID+" LIKE ?";
            String[] argus = {id};
            db.delete(Helper.GeoInformation_Table_SQl, whereCluse, argus);
            db.close();
        }

        //for update method

        public  void updatePlaceData(String place,String comment,String date, String time,String latitude,String longitude,String id ){

            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            String whereCluse = Helper.Column_ID+" LIKE ?";
            String[] argus = {id};


            contentValues.put(Helper.Column_Place_Name, place);
            contentValues.put(Helper.Column_Place_Comment, comment);
            contentValues.put(Helper.Column_date,date);
            contentValues.put(Helper.Column_TIME,time);
            contentValues.put(Helper.Column_Latitude,latitude);
            contentValues.put(Helper.Column_Longitude,longitude);


            db.update(Helper.GeoInformation_Table_SQl,contentValues,whereCluse,argus);
            db.close();




        }







}
