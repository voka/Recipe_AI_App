package com.example.recipe;

import android.app.Application;
import android.graphics.Bitmap;

public  class store_response extends Application {
    public static String response;
    public static Bitmap img;
    public static record[] r_list;
    public static int num;
    @Override
    public void onCreate(){
        String response= "";
        img = null;
        num = 0;
        r_list = new record[50];
        super.onCreate();
    }
    public static void plus(Bitmap image,String name){
        r_list[num] = new record(image,name);
        r_list[num].bmp = image;
        r_list[num].food_name = name;
        num = num + 1;
    }
    @Override
    public void onTerminate(){
        super.onTerminate();
    }
    public void setResponse(String answer){
        this.response = answer;
        System.out.println(response);
    }
    public String getResponse() {
        return this.response;
    }
}
