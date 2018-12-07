package com.developer.isabel.fastfood.utils;

import com.developer.isabel.fastfood.Collection.Item;

import java.util.ArrayList;

public class Data {
    public static ArrayList<Item>list_data;

    public static String TOKEN="Data eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiY2xpZW50ZTFAZ21haWwuY29tIiwicGFzc3dvcmQiOiJjbGllbnRlMSIsImlhdCI6MTU0MzQxNjQyM30.gdJaT0YHJXWmS8JLjmf5saWdhf9JI3z6N9vNrX7NcMY";
    public static String HOST= "http://192.168.1.110:7070/api/v1.0";
    public static String REGISTER_RESTAURANT= HOST + "/restaurant";
    public static String UPLOAD_RESTAURANT= HOST + "/uploadrestaurant";
}
