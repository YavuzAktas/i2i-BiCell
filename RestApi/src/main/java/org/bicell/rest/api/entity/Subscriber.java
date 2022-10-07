package org.bicell.rest.api.entity;

import java.sql.Date;

public class Subscriber {
    private int subsc_id;
    private String msisdn;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String sdate;
    private String status;
    private int package_id;

    public Subscriber() {
    }

    public Subscriber(int subsc_id, String msisdn, String name, String surname, String email, String password, String sdate, String status) {
        this.subsc_id=subsc_id;
        this.msisdn = msisdn;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.sdate = sdate;
        this.status = status;
    }

    public int getSubsc_id() {
        return subsc_id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPackage_id() {
        return package_id;
    }

    public void setPackage_id(int package_id) {
        this.package_id = package_id;
    }

}
