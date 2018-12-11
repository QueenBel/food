package com.developer.isabel.fastfood.Collection;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Item {
    private String title;
    private String street;
    private String phone;
    private String url;
    private  String id;
    public Item(String title, String street, String phone,  String url, String id){
        this.title=title;
        this.street=street;
        this.phone=phone;
        this.url=url;
        this.id=id;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getStreet() {
        return this.street;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getUrl() {
        return this.url;
    }

    public String getId() {
        return this.id;
    }
}
