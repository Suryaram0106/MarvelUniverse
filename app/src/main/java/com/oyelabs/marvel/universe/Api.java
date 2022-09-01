package com.oyelabs.marvel.universe;

import com.google.gson.JsonObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {


    /*String BASE_URL ="https://simplifiedcoding.net/demos/";
    @GET("marvel")
    Call<List<Results>> getsuperHeroes();*/

    String BASE_URL ="http://gateway.marvel.com/v1/public/";
    String key="330b9843edb8724cc0a6d871b88c567c&hash=2360f513871ccdef9836a9940a561f13";
    @GET("characters?ts=010162&apikey="+key)
        Call<JsonObject> getHero();

    }
