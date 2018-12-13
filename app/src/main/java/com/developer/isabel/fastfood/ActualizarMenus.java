package com.developer.isabel.fastfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.developer.isabel.fastfood.CollectionMenu.ItemMenuDetaild;
import com.developer.isabel.fastfood.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ActualizarMenus extends AppCompatActivity {
    public String idMe;
    private EditText editnameM;
    private EditText editpriceM;
    private EditText editdescritionM;
    private Button PATCH_M;
    protected DetaildMenus root;
    protected com.developer.isabel.fastfood.CollectionMenu.ItemMenuDetaild DATAM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        idMe =this.getIntent().getExtras().getString("id");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_menus);
        loadServiceMenu();
        loadAsyncMenu();
    }
    private void loadServiceMenu() {
        AsyncHttpClient client = new AsyncHttpClient();
        //client.get("http://192.168.43.197:7070/", new JsonHttpResponseHandler(){
        client.get(Data.GET_MENUS +this.idMe, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {

                    String nombre = response.getString("NombreMen");
                    String precio = response.getString("PrecioMen");
                    String codigo = response.getString("CodigoMen");
                    String url = response.getString("ProductoMen");
                    String idm = response.getString("_id");
                    String descripcion=response.getString("DescripcionMen");
                    String restaurante=response.getString("idrestaurant");

                    DATAM=new ItemMenuDetaild(nombre, precio,codigo,url,idm,descripcion,restaurante);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                editnameM.setText(DATAM.getNamemenu());
                editpriceM.setText(DATAM.getPricemenu());
                editdescritionM.setText(DATAM.getDescriptionmenu());

            }

        });
    }

    private void loadAsyncMenu() {
        editnameM=findViewById(R.id.edit_name);
        editpriceM=findViewById(R.id.edit_price);
        editdescritionM=findViewById(R.id.edit_description);

        PATCH_M=findViewById(R.id.btn_update);
        PATCH_M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateMenu();
            }
        });

    }

    private void UpdateMenu() {
        String nombrem=editnameM.getText().toString();
        String precim=editpriceM.getText().toString();
        String descripcionm=editdescritionM.getText().toString();

        AsyncHttpClient client=new AsyncHttpClient();

        RequestParams params=new RequestParams();
        params.put("NombreMen",nombrem);
        params.put("PrecioMen",precim);
        params.put("DescripcionMen",descripcionm);

        client.patch(Data.UPDATE_MENUS+"?id=" +this.idMe, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Intent menuss = new Intent(ActualizarMenus.this, ViewMenus.class);
                ActualizarMenus.this.startActivity(menuss);
            }
        });
    }


}
