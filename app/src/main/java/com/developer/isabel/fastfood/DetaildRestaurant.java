package com.developer.isabel.fastfood;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.developer.isabel.fastfood.Collection.OnLoadImgService;
import com.developer.isabel.fastfood.Collection.ServiceImg;
import com.developer.isabel.fastfood.DetallesRestaurante.ItemR;;
import com.developer.isabel.fastfood.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class DetaildRestaurant extends AppCompatActivity {
   public String idRes;
   private Button MENU,UPDATE, DELETE;
   protected TextView title1,nit1,property1,street1, phone1, idrest1;
   //private ArrayList<ItemR> RESTAU;
   protected ImageView picture;
   protected  DetaildRestaurant root;
   protected com.developer.isabel.fastfood.DetallesRestaurante.ItemR DATA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root=this;
        idRes =this.getIntent().getExtras().getString("id");
        //RESTAU=new ArrayList<ItemR>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaild_restaurant);
        MENU=findViewById(R.id.menu_add);
        MENU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registermenu = new Intent(DetaildRestaurant.this, RegisterMenu.class);
                registermenu.putExtra("id",idRes);
                DetaildRestaurant.this.startActivity(registermenu);
            }
        });

        UPDATE=findViewById(R.id.id_actualizar);
        UPDATE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editrestaurante = new Intent(DetaildRestaurant.this, ConsultarRestaurante.class);
                editrestaurante.putExtra("id",idRes);
                DetaildRestaurant.this.startActivity(editrestaurante);
            }
        });

        loadServiceRest1();
        loadAsyncData();
        DELETE=findViewById(R.id.id_eliminar);
        DELETE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteRestaurant();
            }
        });

    }


    private void loadServiceRest1() {
        AsyncHttpClient client = new AsyncHttpClient();
        //client.get("http://192.168.43.197:7070/", new JsonHttpResponseHandler(){
        client.get(Data.GET_RESTAURANT +this.idRes, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String title=response.getString("NombreRest");
                    String street=response.getString("CalleRest");
                    String phone=response.getString("TelefonoRest");
                    String nit=response.getString("NitRest");
                    String property=response.getString("PropietarioRest");
                    String urllist=response.getString("LogoRest");
                    String latitude=response.getString("LatRest");
                    String longitude=response.getString("LonRest");
                    String idrest= response.getString("_id");

                    DATA=new ItemR(title, street, phone, nit, property,urllist, latitude, longitude, idrest);


                    root.LoadComponents1();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });
    }

    private void LoadComponents1() {
        this.title1.setText(DATA.getTitle1());
        this.nit1.setText(DATA.getNit1());
        this.property1.setText(DATA.getProperty1());
        this.street1.setText(DATA.getStreet1());
        this.phone1.setText(DATA.getPhone1());

        ServiceImg imgupload= new ServiceImg();
        imgupload.execute(DATA.getUrl());
        imgupload.setLoadImage(this.picture, new OnLoadImgService() {
            @Override
            public void setLoadImg(ImageView container, Bitmap img) {
                container.setImageBitmap(img);
            }
        });




    }
    private void loadAsyncData() {
        this.title1=this.findViewById(R.id.nombre1);
         this.nit1=this.findViewById(R.id.nit1);
        this.property1=this.findViewById(R.id.propietario1);
        this.street1=this.findViewById(R.id.calle1);
        this.phone1=this.findViewById(R.id.telefono1);
        //this.gallery1=this.findViewById(R.id.imagesource1);
        this.picture = this.findViewById(R.id.imagesource1);
        //this.idrest1=this.findViewById(R.id.idRestorant);
    }

    private void DeleteRestaurant() {
        AsyncHttpClient client=new AsyncHttpClient();
        if(idRes!=null){
            client.delete(Data.DELETE_RESTAURANT+"?id="+this.idRes, new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        String msn=response.getString("msn");
                        //  String id=response.getString("id");
                        //BitmapStruct.ID=id;
                        if(msn!=null){
                            Intent restaurante = new Intent(DetaildRestaurant.this, ViewRestaurant.class);
                            DetaildRestaurant.this.startActivity(restaurante);
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                    //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONObject) was not overriden, but callback was received");
                }
            });
        }
    }

}
