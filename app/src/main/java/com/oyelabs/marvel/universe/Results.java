package com.oyelabs.marvel.universe;

import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("name")
    private String superName;
    @SerializedName("id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSuperName() {
        return superName;
    }

    public void setSuperName(String superName) {
        this.superName = superName;
    }

    public Results(String name) {
        this.superName = name;
        this.id=id;
    }




}

