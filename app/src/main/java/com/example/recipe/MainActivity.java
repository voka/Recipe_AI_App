package com.example.recipe;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends BaseActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File Folder = new File(Environment.getExternalStorageDirectory()+"/Pictures/Test");
        if(!Folder.exists()){
            try{
                Folder.mkdir();
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }
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
    protected void onDestroy() {
        super.onDestroy();
        setDirEmpty("/Pictures/Test");
    }

    //사진 촬영하기
    public void camerabutton(View v) {
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        startActivity(intent);
    }
    //갤러리 선택하는 버튼
    public void gallerybutton(View v) {
        Intent intent = new Intent(getApplicationContext(), Result2Activity.class);
        startActivity(intent);
    }
    //최근 검색기록 버튼

    public void memorybutton(View v) {
        Intent intent = new Intent(getApplicationContext(), recent.class);
        startActivity(intent);
    }

}

