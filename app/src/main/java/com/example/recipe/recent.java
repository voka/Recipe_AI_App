package com.example.recipe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class recent extends BaseActivity {

    public class CustomData {
        private Bitmap image;
        private String food;
        private String time;
        public CustomData() { }
        public Bitmap GetImageData(){ return image; }
        public Bitmap getImageData(){ return this.image; }
        public void setImageData(Bitmap imageData){ this.image = imageData; }
        public String getFood(){ return this.food; }
        public void setFood(String food){ this.food = food; }
        public void setTime(String time){this.time = time;}
        public String getTime(){return this.time;}
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        Bitmap image;
        image = null;
        List<CustomData> objects = new ArrayList<CustomData>();
        Random rnd = new Random();

        for (int i = 0; i < store_response.num; i++) {
            CustomData item = new CustomData();
            item.setImageData(store_response.r_list[i].bmp);
            item.setFood(store_response.r_list[i].food_name);
            item.setTime(store_response.r_list[i].time);
            objects.add(item);
        }
        CustomAdapter customAdapter = new CustomAdapter(this, 0, objects);
        ListView listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomData item = (CustomData) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), ResultPage.class);
                intent.putExtra("food",item.getFood()); /*송신*/
                startActivity(intent);
            }
        });
    }
    protected void onResume(Bundle savedInstanceState){
        super.onResume();
        setContentView(R.layout.listview);
        Bitmap image;
        image = null;
        List<CustomData> objects = new ArrayList<CustomData>();
        Random rnd = new Random();

        for (int i = 0; i < store_response.num; i++) {
            CustomData item = new CustomData();
            item.setImageData(store_response.r_list[i].bmp);
            item.setFood(store_response.r_list[i].food_name);
            objects.add(item);
        }
        CustomAdapter customAdapter = new CustomAdapter(this, 0, objects);
        ListView listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomData item = (CustomData) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), ResultPage.class);
                intent.putExtra("food",item.getFood()); /*송신*/
                startActivity(intent);
            }
        });
    }

    private class CustomAdapter extends ArrayAdapter<CustomData> {
        private LayoutInflater mLayoutInflater;
        public CustomAdapter(Context context, int resource, List<CustomData> objects) {
            super(context, resource, objects);
            mLayoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CustomData item = (CustomData) getItem(position);
            if (null == convertView) {
                convertView = mLayoutInflater.inflate(R.layout.list_item, null);
            }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
            imageView.setImageBitmap(item.getImageData());
            TextView textView1 = (TextView) convertView.findViewById(R.id.text1);
            textView1.setText(item.getFood());
            TextView textView2 = (TextView) convertView.findViewById(R.id.text3);
            textView2.setText(item.getTime());
            return convertView;
        }
    }
}