
package com.example.dynamicalgoapi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileRequest {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("NID")
    @Expose
    private String nID;
    @SerializedName("AboutMe")
    @Expose
    private String aboutMe;
    @SerializedName("CitizenRate")
    @Expose
    private Object citizenRate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNID() {
        return nID;
    }

    public void setNID(String nID) {
        this.nID = nID;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Object getCitizenRate() {
        return citizenRate;
    }

    public void setCitizenRate(Object citizenRate) {
        this.citizenRate = citizenRate;
    }

}