package com.example.recipe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ResultPage extends AppCompatActivity {
    public String url ;
    String food;
    ImageView imageView;

    private void initLoadDB() {
        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();
        url = mDbHelper.get_blog_url(food);
        mDbHelper.close();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_page);
        food = getIntent().getStringExtra("food");
        TextView Title = findViewById(R.id.titles);
        Title.setText(food);
        initLoadDB();
        String date = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
        String file_path = Environment.getExternalStorageDirectory()+"/Pictures/Test/temp_"+ date + ".jpeg";
        Bitmap bmp = BitmapFactory.decodeFile(file_path);
        if(bmp == null){
            Log.i("error","null img ㅜㅜㅜ");
        }
        Log.i("aa",food);
        Log.i("aa",file_path);
        Log.i("bb",food);
        //imageView.setImageBitmap(bmp);
    }



    public void gotoInfo(View v){
        String link = "https://www.myfitnesspal.com/ko/food/search?page=1&search="+food;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);
    }

    public void gotoBlog(View v){
        Log.i("aa","========================");
        Log.i("aa",url);
        Log.i("aa","========================");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    public void gotoYoutube(View v){
        String link = "https://www.youtube.com/results?search_query="+food+"+레시피";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);
    }


    public void onResume(){
        super.onResume();
        setContentView(R.layout.result_page);
        food = getIntent().getStringExtra("food");
        TextView Title = findViewById(R.id.titles);
        Title.setText(food);
        String date = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
        String file_path = Environment.getExternalStorageDirectory()+"/Pictures/Test/temp_"+ date + ".jpeg";
        Bitmap bmp = BitmapFactory.decodeFile(file_path);
        //imageView.setImageBitmap(bmp);

    }
}
