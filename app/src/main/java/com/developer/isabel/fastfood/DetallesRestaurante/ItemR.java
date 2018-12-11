package com.developer.isabel.fastfood.DetallesRestaurante;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class ItemR {
    private String title1;
    private String street1;
    private String phone1;
    private String nit1;
    private String property1;
    private ArrayList<Bitmap>img;
    private ArrayList<String>url;
    private String latitude;
    private  String longitude;
    public ItemR(String title1, String street1, String phone1, String nit1, String property1, ArrayList<String>urlimg, String latitude, String longitude){
        this.title1=title1;
        this.street1=street1;
        this.phone1=phone1;
        this.nit1=nit1;
        this.property1=property1;
        this.url=urlimg;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public String getTitle1() {
        return this.title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getStreet1() {
        return this.street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getPhone1() {
        return this.phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getNit1() {
        return this.nit1;
    }

    public void setNit1(String nit1) {
        this.nit1 = nit1;
    }

    public String getProperty1() {
        return this.property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public void setImg(ArrayList<Bitmap> img) {
        this.img = img;
    }
    public ArrayList<Bitmap> getImg() {
        return this.img;
    }
    public ArrayList<String> getUrlimg() {
        return this.url;
    }
    public ArrayList<Bitmap> getBitmap() {
        return this.img;
    }
    /********/


    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
