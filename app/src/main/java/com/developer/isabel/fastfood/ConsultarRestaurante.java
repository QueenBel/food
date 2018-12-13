package com.developer.isabel.fastfood;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.developer.isabel.fastfood.DetallesRestaurante.ItemR;
import com.developer.isabel.fastfood.utils.BitmapStruct;
import com.developer.isabel.fastfood.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ConsultarRestaurante extends AppCompatActivity {
    public String idRe;
    private EditText editname;
    private EditText editnit;
    private EditText editproperty;
    private EditText editstreet;
    private EditText editphone;
    private Button PATCH;
    protected  DetaildRestaurant root;
    protected com.developer.isabel.fastfood.DetallesRestaurante.ItemR DATA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        idRe =this.getIntent().getExtras().getString("id");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_restaurante);
        loadServiceRest();
        loadAsyncRES();

    }
    private void loadServiceRest() {
        AsyncHttpClient client = new AsyncHttpClient();
        //client.get("http://192.168.43.197:7070/", new JsonHttpResponseHandler(){
        client.get(Data.GET_RESTAURANT +this.idRe, new JsonHttpResponseHandler(){
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

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                editname.setText(DATA.getTitle1());
                editnit.setText(DATA.getNit1());
                editproperty.setText(DATA.getProperty1());
                editphone.setText(DATA.getPhone1());
                editstreet.setText(DATA.getStreet1());

            }

        });
    }

    private void loadAsyncRES() {
        editname=findViewById(R.id.editname);
        editnit=findViewById(R.id.editnit);
        editproperty=findViewById(R.id.editproperty);
        editphone=findViewById(R.id.editphone);
        editstreet=findViewById(R.id.editstreet);

        PATCH=findViewById(R.id.btnActualizar);
        PATCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateRestaurant();
            }
        });
    }

    private void UpdateRestaurant() {

        String nombre=editname.getText().toString();
        String nit=editnit.getText().toString();
        String duenio=editproperty.getText().toString();
        String celular=editphone.getText().toString();
        String calles=editstreet.getText().toString();

        //idRe =this.getIntent().getExtras().getString("id");

        AsyncHttpClient client=new AsyncHttpClient();
        client.addHeader("authorization", Data.TOKEN);

        RequestParams params=new RequestParams();
        params.put("NombreRest",nombre);
        params.put("NitRest",nit);
        params.put("PropietarioRest",duenio);
        params.put("TelefonoRest",celular);
        params.put("CalleRest",calles);

        /*params.put("NombreRest","actualizado");
        params.put("NitRest","actualizado");
        params.put("PropietarioRest","actuizado");
        params.put("TelefonoRest","acrtualizado");
        params.put("CalleRest","actualizado");*/

        client.patch(Data.UPDATE_RESTAURANT+"?id=" +this.idRe, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                /*AlertDialog alertDialog= new AlertDialog.Builder(ConsultarRestaurante.this).create();
                try {
                    String msn=response.getString("msn");
                    alertDialog.setTitle("ACTUALIZADO ");
                    alertDialog.setMessage(msn);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });
                    alertDialog.show();*/
                    Intent restaurante = new Intent(ConsultarRestaurante.this, ViewRestaurant.class);
                    ConsultarRestaurante.this.startActivity(restaurante);
                /*}catch (JSONException e){
                    e.printStackTrace();
                }*/




                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONObject) was not overriden, but callback was received");
            }
        });
    }

}
