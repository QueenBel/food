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

import com.developer.isabel.fastfood.utils.BitmapStruct;
import com.developer.isabel.fastfood.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;

import cz.msebera.android.httpclient.Header;

public class ConsultarRestaurante extends AppCompatActivity {
    public String idRe;
    private EditText nombre1,nit1,property1,street1, phone1;
    private Button PATCH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_restaurante);
        loadComponet();

    }

    private void loadComponet() {
        nombre1=findViewById(R.id.editname);
        nit1=findViewById(R.id.editnit);
        property1=findViewById(R.id.editproperty);
        phone1=findViewById(R.id.editphone);
        street1=findViewById(R.id.editstreet);
        idRe =this.getIntent().getExtras().getString("id");
        PATCH=findViewById(R.id.btnActualizar);
        PATCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateRestaurant();
            }
        });

    }

    private void UpdateRestaurant() {
        String nombre=nombre1.getText().toString();
        String nit=nit1.getText().toString();
        String duenio=property1.getText().toString();
        String celular=phone1.getText().toString();
        String calles=street1.getText().toString();

        //idRe =this.getIntent().getExtras().getString("id");

        AsyncHttpClient client=new AsyncHttpClient();

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
