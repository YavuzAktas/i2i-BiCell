package org.bicell.rest.api.entity;

import java.sql.Date;

public class Balance {
    private int subs_id;
    private int package_id;
    private int lvl_voice;
    private int lvl_data;
    private int lvl_sms;
    private int price;
    private Date sdate;
    private Date edate;

    public Balance(int subs_id, int package_id, int lvl_voice, int lvl_data, int lvl_sms, int price) {
        this.subs_id = subs_id;
        this.package_id = package_id;
        this.lvl_voice = lvl_voice;
        this.lvl_data = lvl_data;
        this.lvl_sms = lvl_sms;
        this.price = price;
    }

    public Balance() {
    }

    public int getSubs_id() {
        return subs_id;
    }

    public int getPackage_id() {
        return package_id;
    }

    public int getLvl_voice() {
        return lvl_voice;
    }

    public void setLvl_voice(int lvl_voice) {
        this.lvl_voice = lvl_voice;
    }

    public int getLvl_data() {
        return lvl_data;
    }

    public void setLvl_data(int lvl_data) {
        this.lvl_data = lvl_data;
    }

    public int getLvl_sms() {
        return lvl_sms;
    }

    public void setLvl_sms(int lvl_sms) {
        this.lvl_sms = lvl_sms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
}
