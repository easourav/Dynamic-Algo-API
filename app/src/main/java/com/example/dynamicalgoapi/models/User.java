package com.example.dynamicalgoapi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @Expose
    private String Name;
    @SerializedName("Email")
    @Expose
    private String Email;
    @SerializedName("AboutMe")
    @Expose
    private String AboutMe;
    @SerializedName("CitizenRate")
    @Expose
    private String CitizenRate;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getAboutMe() {
        return AboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.AboutMe = aboutMe;
    }

    public Object getCitizenRate() {
        return CitizenRate;
    }

    public void setCitizenRate(String citizenRate) {
        this.CitizenRate = citizenRate;
    }

}