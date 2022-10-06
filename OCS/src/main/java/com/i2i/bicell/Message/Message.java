package com.i2i.bicell.Message;

import java.util.Date;
import java.io.Serializable;

public class Message{
    public static class usage implements Serializable{
        private int subsc_id;
        private int package_id;
        private Date sdate;
        private Date edate;
        private int blc_voice;
        private int blc_sms;
        private int blc_data;
        private int price;

        public int getSubsc_id() { return subsc_id; }
        public int getPackage_id() { return package_id; }
        public Date getSdate() { return sdate; }
        public Date getEdate() { return edate; }
        public int getBlc_voice() { return blc_voice; }
        public int getBlc_sms() { return blc_sms; }
        public int getBlc_data() { return blc_data; }
        public int get_Price() { return price; }

        public void setSubsc_id(int subsc_id) { this.subsc_id = subsc_id; }
        public void setPackage_id(int package_id) { this.package_id = package_id; }
        public void setSdate(Date sdate) { this.sdate = sdate; }
        public void setEdate(Date edate) { this.edate = edate; }
        public void setBlc_voice(int blc_voice) { this.blc_voice = blc_voice; }
        public void setBlc_sms(int blc_sms) { this.blc_sms = blc_sms; }
        public void setBlc_data(int blc_data) { this.blc_data = blc_data; }
        public void set_Price(int price){ this.price = price; }
    }

    public static class service implements Serializable{
        private String pck_name, regex;
        private int round;
        private double price;

        public String get_Pck_name(){ return pck_name; }
        public String get_regex(){ return regex; }
        public int get_round(){ return round; }
        public double get_price(){ return price; }

        public void set_Pck_name(String pck_name){ this.pck_name = pck_name; }
        public void set_regex(String regex){ this.regex = regex; }
        public void set_round(int round){ this.round = round; }
        public void set_price(double price){ this.price = price; }


    }
}
