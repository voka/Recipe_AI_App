package com.example.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.OkHttpClient;

public class Result2Activity extends BaseActivity {

    ImageView imageView;
    Button button,btnImageSend;
    File tempSelectFile;
    private ProgressDialog mProgressDialog;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);
        btnImageSend = findViewById(R.id.btnImageSend);
        mHandler = new Handler();
        btnImageSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(tempSelectFile != null){
                    mProgressDialog = ProgressDialog.show(Result2Activity.this,"","잠시만 기다려 주세요!!" , true);
                    mProgressDialog.setCancelable(true);
                    FileUploadUtils.goSend(tempSelectFile);
                    mHandler.postDelayed(new Runnable(){
                        @Override
                        public void run(){
                            Intent intent = new Intent(getApplicationContext(), select5.class);
                            startActivity(intent);
                        }
                    },10000);
                }
                else{
                    Toast.makeText(getApplicationContext(),"사진을 선택해 주세요", Toast.LENGTH_SHORT).show();
                }

            }
        });

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });
        imageView = (ImageView)findViewById(R.id.image);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    // 이미지 표시
                    imageView.setImageBitmap(img);
                    store_response.img = img;
                    String date = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
                    tempSelectFile = new File(Environment.getExternalStorageDirectory()+"/Pictures/Test/", "temp_" + date + ".jpeg");
                    OutputStream out = new FileOutputStream(tempSelectFile);
                    img.compress(Bitmap.CompressFormat.JPEG, 100, out);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

}