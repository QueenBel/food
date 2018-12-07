package com.developer.isabel.fastfood;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.developer.isabel.fastfood.Collection.Item;
import com.developer.isabel.fastfood.Collection.ListAdapter;
import com.developer.isabel.fastfood.Collection.OnLoadImgService;
import com.developer.isabel.fastfood.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ViewRestaurant extends AppCompatActivity {
    //private ListView list;

   // private ListAdapter adapter;
   // private Context root;
    private OnLoadImgService event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_restaurant);

        //
       // root = this;
        Data.list_data= new ArrayList<Item>();

        loadServiceHouse();

    }
    private void loadServiceHouse() {



        AsyncHttpClient client = new AsyncHttpClient();
        //client.get("http://192.168.43.197:7070/", new JsonHttpResponseHandler(){
           client.get("http://192.168.1.110:7070/api/v1.0/restaurant", new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    for (int i = 0; i<data.length(); i++){
                        //Item p = new Item();
                        JSONObject obj = data.getJSONObject(i);
                        String id= obj.getString("_id");
                        String title = obj.getString("NombreRest");
                        String street = obj.getString("CalleRest");
                        String phone = obj.getString("TelefonoRest");
                        String url = obj.getString("LogoRest");

                        Item item=new Item(title, street, phone, url, id);
                        Data.list_data.add(item);
                    }
                    loadComponents();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                //Toast.makeText(this, "Fail", Toast.LENGTH_LONG).show();

            }


        });
    }

    public void setOnloadCompleteData(OnLoadImgService event) {
        this.event = event;
    }
    private void loadComponents() {
        ListView list = (ListView)this.findViewById(R.id.list_main);
        ListAdapter adapter = new ListAdapter(this, Data.list_data);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String idRes=Data.list_data.get(position).getId();
                //Intent dtailds=new Intent(root, DetaildRestaurant.class);
                //dtailds.putExtra("id", idRes);
                //root.startActivity(dtailds);

                Intent detailds= new Intent(ViewRestaurant.this, DetaildRestaurant.class);
                detailds.putExtra("id", idRes);
                ViewRestaurant.this.startActivity(detailds);
                //;
                //root.startActivity(dtailds);
            }
        });

    }
}
//08:40:00 apirestMovie