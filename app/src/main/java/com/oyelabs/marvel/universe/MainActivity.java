package com.oyelabs.marvel.universe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


        ListView superListView;
        RecyclerView marvel_recyclerview;
        ImageAdapter imageList_adapter;
        ArrayList<String> imageList,nameList;
        ArrayList<Integer> idList;
        SearchView searchView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //superListView = findViewById(R.id.superListView);


            searchView = findViewById(R.id.searchView);
            marvel_recyclerview = findViewById(R.id.recyclerview_marvel);
            imageList=new ArrayList<>();
            nameList=new ArrayList<>();
            idList = new ArrayList<>();
            imageList_adapter=new ImageAdapter(imageList,nameList,idList,this);
            marvel_recyclerview.setLayoutManager(new GridLayoutManager(this, 2));

            getHero();

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    if(nameList.contains(query)){
                        //marvel_recyclerview.setAdapter(imageList_adapter);
                    }else{
                        Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                    }
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    //adapter.getFilter().filter(newText);
                    filterText(newText);
                    return false;
                }
            });


        }

    private void filterText(String newText) {

        ArrayList<String> filteredList_name = new ArrayList<>();
        ArrayList<String>  filteredList_image= new ArrayList<>();
        ArrayList<Integer>  filteredList_id= new ArrayList<>();

        for(int i=0;i<nameList.size();i++)
        {
            if(nameList.get(i).toLowerCase().contains(newText.toLowerCase()))
            {
                filteredList_name.add(nameList.get(i));
                filteredList_image.add(imageList.get(i));
                filteredList_id.add(idList.get(i));

            }
        }
        if(filteredList_name.isEmpty())
        {
            Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
        }
        else
        {
         imageList_adapter.setFilteredList(filteredList_image,filteredList_name,filteredList_id,this);
        }

    }

    private void getHero() {
            Call<JsonObject> call = RetrofitClient.getInstance().getMyApi().getHero();
            call.enqueue(new Callback<JsonObject>() {

                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                    System.out.println("response code: "+ response.code());
                    //System.out.println("response 1: "+ response.body().get("attributionText"));
                    //System.out.println("response 2: "+ response.body().get("attributionHTML"));
                    System.out.println("response 3: "+ response.body().get("data"));
                    JsonObject data = (JsonObject) response.body().get("data");
                    JsonArray results = (JsonArray) data.get("results");
                    System.out.println("response 4  : "+ results.size());

                    for (int i = 0; i < results.size(); ++i) {
                        JsonElement rec = results.get(i);
                        JsonObject loc = rec.getAsJsonObject();
                        System.out.println("response 7"+loc.get("name").toString());
                        //System.out.println("response 5"+loc.get("thumbnail").getAsJsonObject().get("path").getAsString()+".jpg");
                        System.out.println("response 6"+loc.get("thumbnail").getAsJsonObject().get("path").toString()+".jpg");
                        String str = loc.get("thumbnail").getAsJsonObject().get("path").getAsString()+".jpg";


                        URL url = null;
                        try {
                            url = new URL(str);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        imageList.add(String.valueOf(url));
                        nameList.add(loc.get("name").toString());
                        idList.add(loc.get("id").getAsInt());

                    }

                    marvel_recyclerview.setAdapter(imageList_adapter);

                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    System.out.println("response failure: "+ t.toString());
                    Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
                }

            });
        }
}