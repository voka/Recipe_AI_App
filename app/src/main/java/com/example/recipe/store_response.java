package com.example.recipe;

import android.app.Application;

public  class store_response extends Application {
    public static String response;
    @Override
    public void onCreate(){
        String response= "";
        super.onCreate();
    }
    @Override
    public void onTerminate(){
        super.onTerminate();
    }

    public void setResponse(String answer){
        System.out.println("이거 실행 되나?");
        this.response = answer;
        System.out.println(response);
    }
    public String getResponse() {
        return this.response;
    }
}
