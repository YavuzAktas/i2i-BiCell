package com.example.i2isystems;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubscriberRequest {
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("surname")
    @Expose
    String surname;
    @SerializedName("email")
    @Expose
    String email;
    @SerializedName("msisdn")
    @Expose
    String msisdn;
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

    public void setEmail(String email) { this.email = email; }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

}
