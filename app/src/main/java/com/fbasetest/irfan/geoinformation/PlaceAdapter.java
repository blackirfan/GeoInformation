package com.fbasetest.irfan.geoinformation;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PlaceAdapter extends ArrayAdapter {


    public Context context;
    public  int resID;
    ArrayList<PlaceModelClass> dataList;

    ArrayList<PlaceModelClass> searchList;


    public PlaceAdapter(@NonNull Context context, int resource,  ArrayList<PlaceModelClass> dataList) {
        super(context, resource, dataList);
        this.context = context;
        this.resID = resource;
        this.dataList = dataList;




        //for search in list


        this.searchList = new ArrayList<>();
        this.searchList.addAll(dataList);

    }



    @Override
    public int getCount() {
        return dataList.size();
    }

    public  class ViewHolder{
        TextView tvName, tvComment, tvDate,tvTime,tvLatitude,tvLongitude;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        final ViewHolder viewHolder;

        if (view == null){

            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(resID, null);
            viewHolder = new ViewHolder();

            viewHolder.tvName =  view.findViewById(R.id.tv_name);
            viewHolder.tvComment =  view.findViewById(R.id.tv_comment);
            viewHolder.tvDate =  view.findViewById(R.id.tv_date);
            viewHolder.tvTime = view.findViewById(R.id.tv_time);
            viewHolder.tvLatitude = view.findViewById(R.id.tv_latitude);
            viewHolder.tvLongitude =view.findViewById(R.id.tv_longitude);

            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();

        }

        final PlaceModelClass dataModel = dataList.get(position);

        viewHolder.tvName.setText("Place Name: "+dataModel.getPlaceName());
        viewHolder.tvComment.setText("Place Description: "+dataModel.getComment());
        viewHolder.tvDate.setText("Date: "+dataModel.getDate());
        viewHolder.tvTime.setText("Time: "+dataModel.getTime());
        viewHolder.tvLatitude.setText("Latitude: "+dataModel.getLatitude());
        viewHolder.tvLongitude.setText("Longitude: "+dataModel.getLongitude());








            viewHolder.tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context,PlaceLocation.class);


                    intent.putExtra("latitude",dataModel.latitude);
                    intent.putExtra("longitude",dataModel.longitude);

                    intent.putExtra("name",dataModel.placeName);
//                    intent.putExtra("latitude", dataModel.getLatitude());
//                    intent.putExtra("longitude",dataModel.getLongitude());

                    context.startActivity(intent);

//                    Toast.makeText(context, " value "+dataModel.placeName, Toast.LENGTH_SHORT).show();
                }
            });


        return view;

    }




    public void filter(String queryText){
        queryText = queryText.toLowerCase();
        dataList.clear();

        if (queryText.length() == 0){
            dataList.addAll(searchList);
        }else {
            for (PlaceModelClass placeDataModel : searchList){
                if (placeDataModel.getPlaceName().toLowerCase().contains(queryText)){
                    dataList.add(placeDataModel);
                }
            }

        }
        notifyDataSetChanged();
    }


}
