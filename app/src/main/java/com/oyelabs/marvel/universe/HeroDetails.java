package com.oyelabs.marvel.universe;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeroDetails extends AppCompatActivity {

    TextView textView1,textView2,textView3,textView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_details);

        textView1 = findViewById(R.id.textview1);
        textView2 = findViewById(R.id.textview2);
        textView3 = findViewById(R.id.textview3);



       int id = Integer.parseInt(getIntent().getStringExtra("hero_id"));
       String name = getIntent().getStringExtra("hero_name");
       String image = getIntent().getStringExtra("hero_image");

       System.out.println("Hero id"+id);
       textView1.setText("Id : "+ id);
        textView2.setText("Marvel Name : "+ name);
        textView3.setText("Marvel Url : "+ image);

    }
}
