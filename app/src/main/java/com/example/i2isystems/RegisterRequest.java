package com.example.i2isystems;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterRequest {
    @SerializedName("subsc_id")
    @Expose
    int subscId;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("surname")
    @Expose
    String surname;
    @SerializedName("email")
    @Expose
    String email;
    @SerializedName("password")
    @Expose
    String password;
    @SerializedName("msisdn")
    @Expose
    String msisdn;
    @SerializedName("package_id")
    @Expose
    int packadgeId;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getSurname() {

        return surname;
    }

    public void setSurname(String surname) {

        this.surname = surname;
    }

    public int getSubscId() {
        return subscId;
    }

    public void setSubscId(int subscId) {
        this.subscId = subscId;
    }

    public int getPackadgeId() {
        return packadgeId;
    }

    public void setPackadgeId(int packadgeId) {
        this.packadgeId = packadgeId;
    }
}
