package com.developer.isabel.fastfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.developer.isabel.fastfood.utils.BitmapStruct;
import com.developer.isabel.fastfood.utils.BitmapStructMenu;
import com.developer.isabel.fastfood.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class RegisterMenu extends AppCompatActivity {
    public String idRest;
    private Button nextmenu;
    protected DetaildRestaurant root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // idRest =this.getIntent().getExtras().getString("id");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_menu);
        //idRestaurante=BitmapStruct.ID;
        nextmenu=findViewById(R.id.nextM);
        nextmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataM();
            }
        });
    }

    private void sendDataM() {
        TextView nameM=findViewById(R.id.nameM);
        TextView priceM=findViewById(R.id.priceM);
        TextView descriptionM=findViewById(R.id.descriptionM);
        idRest =this.getIntent().getExtras().getString("id");
        //if(BitmapStruct.ID!=null){
            //restaurante= BitmapStruct.ID;
            RequestParams params=new RequestParams();
            params.add("name","producto1");
            params.add("price","10");
            params.add("description","todo lo q pueda comer de comer del producto1");
           // params.add("restaurant","5c1080b0061b6f26f4770756");
            params.add("restaurant",idRest);

            AsyncHttpClient client=new AsyncHttpClient();
            client.post(Data.REGISTER_MENUS, params, new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        String msn=response.getString("msn");
                        String id=response.getString("id");
                        BitmapStructMenu.ID_M=id;
                        if(msn!=null){
                            Intent camera = new Intent(RegisterMenu.this, PhotoMenu.class);
                            RegisterMenu.this.startActivity(camera);
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                }
            });
        }
    //}
}
