package com.i2i.bicell.kafka.src.main.java.org.example.modul;

import java.io.Serializable;
import java.util.Date;

public class UsageMessage implements Serializable {

    private int subsc_id;
    private int package_id;
    private int blc_voice;
    private int blc_sms;
    private int blc_data;
    private int price;
    private Date sdate;
    private Date edate;

    @Override
    public String toString() {
        return "subsc_id : " + getSubsc_id() +  ", package_id : " + getPackage_id() + "blc_voice : " + getBlc_voice() + "blc_sms:" +
                getBlc_sms() + "blc_data :" + getBlc_data() + "price :" + getPrice() + "sdate" + getSdate() + "edate" + getEdate();
    }

    public int getSubsc_id() {
        return subsc_id;
    }

    public void setSubsc_id(int subsc_id) {
        this.subsc_id = subsc_id;
    }

    public int getPackage_id() {
        return package_id;
    }

    public void setPackage_id(int package_id) {
        this.package_id = package_id;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    public int getBlc_voice() {
        return blc_voice;
    }

    public void setBlc_voice(int blc_voice) {
        this.blc_voice = blc_voice;
    }

    public int getBlc_sms() {
        return blc_sms;
    }

    public void setBlc_sms(int blc_sms) {
        this.blc_sms = blc_sms;
    }

    public int getBlc_data() {
        return blc_data;
    }

    public void setBlc_data(int blc_data) {
        this.blc_data = blc_data;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

