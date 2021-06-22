package com.example.recipe;

import android.app.Application;
import android.graphics.Bitmap;

import java.text.SimpleDateFormat;
import java.util.Date;

public class record extends Application {
    Bitmap bmp;
    String food_name;
    String time;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    record(Bitmap image,String food){
        this.food_name = food;
        this.bmp = image;
        long mNow = System.currentTimeMillis();
        Date mDate = new Date(mNow);
        time =  mFormat.format(mDate);
    }
}
