package com.oyelabs.marvel.universe;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MarvelHero {

    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("attributionText")
    @Expose
    private String atrributionText;
    @SerializedName("data")
    @Expose
    private List<Data> data = null;

    public MarvelHero(int code, String atrributionText,List<Data> data) {
        this.code = code;
        this.atrributionText = atrributionText;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }



    public String getAtrributionText() {
        return atrributionText;
    }

    public void setAtrributionText(String atrributionText) {
        this.atrributionText = atrributionText;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }


}
