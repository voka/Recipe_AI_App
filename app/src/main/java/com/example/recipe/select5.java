package com.example.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.List;

public class select5 extends AppCompatActivity {
    public List<User> userList ;
    String food;
    private void initLoadDB() {
        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();
        User temp;
        userList = mDbHelper.getTableData();
        mDbHelper.close();
    }

    public void btn1(View v){
        Button bt1 = (Button)findViewById(R.id.button1);
        food = (String) bt1.getText();
        goResultPage();
    }
    public void btn2(View v){
        Button bt2 = (Button)findViewById(R.id.button2);
        food = (String) bt2.getText();
        goResultPage();
    }public void btn3(View v){
        Button bt3 = (Button)findViewById(R.id.button3);
        food = (String) bt3.getText();
        goResultPage();
    }public void btn4(View v){
        Button bt4 = (Button)findViewById(R.id.button4);
        food = (String) bt4.getText();
        goResultPage();
    }public void btn5(View v){
        Button bt5 = (Button)findViewById(R.id.button5);
        food = (String) bt5.getText();
        goResultPage();
    }
    public void goResultPage(){
        Intent intent = new Intent(getApplicationContext(), ResultPage.class);
        intent.putExtra("food",food); /*송신*/
        startActivity(intent);
    }
    public void setDirEmpty(String dirName){
        String path = Environment.getExternalStorageDirectory().toString() + dirName;
        File dir = new File(path);
        File[] childFileList = dir.listFiles();
        if (dir.exists()) {
            for (File childFile : childFileList) {
                if (childFile.isDirectory()) {
                    setDirEmpty(childFile.getAbsolutePath());
                } else {
                    childFile.delete();
                }
            }// dir.delete();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoadDB();
        setContentView(R.layout.select_page);
        String temp = store_response.response;
        temp = temp.substring(1,temp.length()-1);
        String[] temp2 = temp.split(", ");

        Button bt1 = (Button)findViewById(R.id.button1);
        Button bt2 = (Button)findViewById(R.id.button2);
        Button bt3 = (Button)findViewById(R.id.button3);
        Button bt4 = (Button)findViewById(R.id.button4);
        Button bt5 = (Button)findViewById(R.id.button5);
        for(User u: userList){
            if(u.getId()-1 == Integer.parseInt(temp2[0]))
                bt1.setText(u.getLink());
            if(u.getId()-1 == Integer.parseInt(temp2[1]))
                bt2.setText(u.getLink());
            if(u.getId()-1 == Integer.parseInt(temp2[2]))
                bt3.setText(u.getLink());
            if(u.getId()-1 == Integer.parseInt(temp2[3]))
                bt4.setText(u.getLink());
            if(u.getId()-1 == Integer.parseInt(temp2[4]))
                bt5.setText(u.getLink());
        }
        //setDirEmpty("/Pictures/Test");
    }
    //다시 할떄 실행되는 함수
    @Override
    public void onResume(){
        super.onResume();
        setContentView(R.layout.select_page);
        String temp = store_response.response;
        temp = temp.substring(1,temp.length()-1);
        String[] temp2 = temp.split(", ");
        Button bt1 = (Button)findViewById(R.id.button1);
        Button bt2 = (Button)findViewById(R.id.button2);
        Button bt3 = (Button)findViewById(R.id.button3);
        Button bt4 = (Button)findViewById(R.id.button4);
        Button bt5 = (Button)findViewById(R.id.button5);

        for(User u: userList){
            if(u.getId()-1 == Integer.parseInt(temp2[0]))
                bt1.setText(u.getLink());
            if(u.getId()-1 == Integer.parseInt(temp2[1]))
                bt2.setText(u.getLink());
            if(u.getId()-1 == Integer.parseInt(temp2[2]))
                bt3.setText(u.getLink());
            if(u.getId()-1 == Integer.parseInt(temp2[3]))
                bt4.setText(u.getLink());
            if(u.getId()-1 == Integer.parseInt(temp2[4]))
                bt5.setText(u.getLink());
        }
        //setDirEmpty("/Pictures/Test");
    }

}

