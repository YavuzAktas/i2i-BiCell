package org.bicell.rest.api.entity;

public class Package {
    private int package_id;
    private String package_name;
    private int amount_voice;
    private int amount_data;
    private int amount_sms;
    private int duration;

    public Package(int package_id, String package_name, int amount_voice, int amount_data, int amount_sms, int duration) {
        this.package_id = package_id;
        this.package_name = package_name;
        this.amount_voice = amount_voice;
        this.amount_data = amount_data;
        this.amount_sms = amount_sms;
        this.duration = duration;
    }


    public Package() {
    }

    public int getPackage_id() {
        return package_id;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public int getAmount_voice() {
        return amount_voice;
    }

    public void setAmount_voice(int amount_voice) {
        this.amount_voice = amount_voice;
    }

    public int getAmount_data() {
        return amount_data;
    }

    public void setAmount_data(int amount_data) {
        this.amount_data = amount_data;
    }

    public int getAmount_sms() {
        return amount_sms;
    }

    public void setAmount_sms(int amount_sms) {
        this.amount_sms = amount_sms;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
