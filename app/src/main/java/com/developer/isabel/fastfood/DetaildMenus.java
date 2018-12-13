package com.developer.isabel.fastfood;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.isabel.fastfood.CollectionMenu.ItemMenuDetaild;
import com.developer.isabel.fastfood.CollectionMenu.OnLoadImgServiceMenu;
import com.developer.isabel.fastfood.CollectionMenu.ServiceImgMenu;
import com.developer.isabel.fastfood.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class DetaildMenus extends AppCompatActivity {
    public String idMenu;
    private Button ATRAS,UPDATE, DELETE;
    protected TextView namem1,pricem1,codigom1,descriptionm1,restaruantem1,idm1;
    protected ImageView picturem;
    protected DetaildMenus root;
    protected com.developer.isabel.fastfood.CollectionMenu.ItemMenuDetaild DATAM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root=this;
        idMenu =this.getIntent().getExtras().getString("id");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaild_menus);
        loadServiceMen1();
        loadAsyncDataM();
    }
    private void loadServiceMen1() {
        AsyncHttpClient client = new AsyncHttpClient();
        //client.get("http://192.168.43.197:7070/", new JsonHttpResponseHandler(){
        client.get(Data.GET_MENUS +this.idMenu, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {

                    String nombre = response.getString("NombreMen");
                    String precio = response.getString("PrecioMen");
                    String codigo = response.getString("CodigoMen");
                    String url = response.getString("ProductoMen");
                    String idm= response.getString("_id");
                    String descripcion=response.getString("DescripcionMen");
                    String restaurante=response.getString("idrestaurant");

                    DATAM=new ItemMenuDetaild(nombre, precio,codigo,url,idm,descripcion,restaurante);

                    root.LoadComponentsM();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });
    }

    private void LoadComponentsM() {
        this.namem1.setText(DATAM.getNamemenu());
        this.pricem1.setText(DATAM.getPricemenu());
        this.codigom1.setText(DATAM.getCodigomenu());
        this.descriptionm1.setText(DATAM.getDescriptionmenu());
        this.restaruantem1.setText(DATAM.getRestaurantemenu());

        ServiceImgMenu imgUpload=new ServiceImgMenu();
        imgUpload.execute(DATAM.getUrlmenu());
        imgUpload.setLoadImgMenu(this.picturem, new OnLoadImgServiceMenu() {
            @Override
            public void setLoadImgMenu(ImageView container, Bitmap img) {
                container.setImageBitmap(img);
            }
        });

    }

    private void loadAsyncDataM() {
        this.namem1=this.findViewById(R.id.nombremenu);
        this.pricem1=this.findViewById(R.id.preciomenu);
        this.codigom1=this.findViewById(R.id.codigomenu);
        this.descriptionm1=this.findViewById(R.id.descripcionmenu);
        this.restaruantem1=this.findViewById(R.id.restaurantemenu);
        this.picturem = this.findViewById(R.id.imagenmenu);
    }


}
