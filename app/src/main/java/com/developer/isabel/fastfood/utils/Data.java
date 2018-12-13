package com.developer.isabel.fastfood.utils;

import com.developer.isabel.fastfood.Collection.Item;
import com.developer.isabel.fastfood.CollectionMenu.ItemMenu;

import java.util.ArrayList;

public class Data {
    public static ArrayList<Item>list_data;
    //public static ArrayList<ItemR>list_data1;
    public static String TOKEN=null;
    //public static String TOKEN="Data eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiY2xpZW50ZTFAZ21haWwuY29tIiwicGFzc3dvcmQiOiJjbGllbnRlMSIsImlhdCI6MTU0MzQxNjQyM30.gdJaT0YHJXWmS8JLjmf5saWdhf9JI3z6N9vNrX7NcMY";
    public static String HOST= "http://192.168.1.110:7070";
    public static String REGISTER_CLIENT=HOST+ "/api/v1.0/clients";
    public static String LOGIN= HOST+ "/api/v1.0/clients/login";

    public static String REGISTER_RESTAURANT= HOST + "/api/v1.0/restaurant";
    public static String UPLOAD_RESTAURANT= HOST + "/api/v1.0/uploadrestaurant";
    public static String GET_RESTAURANT= HOST + "/api/v1.0/restaurant/";
    public static String UPDATE_RESTAURANT= HOST + "/api/v1.0/restaurant";
    public static String DELETE_RESTAURANT= HOST + "/api/v1.0/restaurant";

    /*todo del menu */
    public static  ArrayList<ItemMenu>LIST_MENU;
    public static String REGISTER_MENUS= HOST + "/api/v1.0/menus";
    public static String UPLOAD_MENUS= HOST + "/api/v1.0/uploadmenus";
    public static String GET_MENUS= HOST + "/api/v1.0/menus/";
    public static String UPDATE_MENUS= HOST + "/api/v1.0/menus";
    public static String DELETE_MENUS= HOST + "/api/v1.0/menus";
}
