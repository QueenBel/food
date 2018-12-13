package com.developer.isabel.fastfood;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.developer.isabel.fastfood.CollectionMenu.ItemMenu;
import com.developer.isabel.fastfood.CollectionMenu.ListAdapterMenu;
import com.developer.isabel.fastfood.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ViewMenus extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private Context ROOT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ROOT = this;
        Data.LIST_MENU=new ArrayList<ItemMenu>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_menus);

        loadServiceMenus();
    }

    private void loadServiceMenus() {
        AsyncHttpClient client = new AsyncHttpClient();
        //client.get("http://192.168.43.197:7070/", new JsonHttpResponseHandler(){
        client.get(Data.GET_MENUS, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    for (int i = 0; i<data.length(); i++){
                        JSONObject obj = data.getJSONObject(i);
                        String id= obj.getString("_id");
                        String nombre = obj.getString("NombreMen");
                        String precio = obj.getString("PrecioMen");
                        String codigo = obj.getString("CodigoMen");
                        String url = obj.getString("ProductoMen");

                        ItemMenu item=new ItemMenu(nombre, precio, codigo, url, id);
                        Data.LIST_MENU.add(item);
                    }
                    loadComponents();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });
    }

    private void loadComponents() {
        ListView list = (ListView)this.findViewById(R.id.list_main_M);
        list.setOnItemClickListener(this);

        ListAdapterMenu adapter = new ListAdapterMenu(this, Data.LIST_MENU);
        list.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String idMen=Data.LIST_MENU.get(position).getId();

        Intent detailds= new Intent(this, DetaildMenus.class);
        detailds.putExtra("id", idMen);
        this.startActivity(detailds);

    }
}
