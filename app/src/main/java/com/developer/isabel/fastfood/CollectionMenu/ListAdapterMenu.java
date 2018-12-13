package com.developer.isabel.fastfood.CollectionMenu;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.isabel.fastfood.Collection.Item;
import com.developer.isabel.fastfood.R;

import java.util.ArrayList;
import java.util.List;

public class ListAdapterMenu extends BaseAdapter implements OnLoadImgServiceMenu {
    private Context contextM;
    private List<ItemMenu>itemsM;

    public ListAdapterMenu(Context contextM, ArrayList<ItemMenu>itemsM){
        this.contextM=contextM;
        this.itemsM=itemsM;
    }
    @Override
    public int getCount() {
        return this.itemsM.size();
    }

    @Override
    public Object getItem(int position) {
        return this.itemsM.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflate = (LayoutInflater)this.contextM.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.item_menu, null);
        }
        TextView nombre = (TextView)convertView.findViewById(R.id.nombreMenu);
        TextView precio = (TextView)convertView.findViewById(R.id.precioMenu);
        TextView codigo = (TextView)convertView.findViewById(R.id.codigoMenu);
        ImageView image = (ImageView)convertView.findViewById(R.id.imagesourceMenu);

        nombre.setText(this.itemsM.get(position).getNameM());
        precio.setText(this.itemsM.get(position).getPriceM());
        codigo.setText(this.itemsM.get(position).getCodigoM());

        ServiceImgMenu menuimg=new ServiceImgMenu();
        menuimg.setLoadImgMenu(image, this);
        menuimg.execute(this.itemsM.get(position).getUrl());

        return convertView;
    }

    @Override
    public void setLoadImgMenu(ImageView container, Bitmap img) {
        container.setImageBitmap(img);

    }
}
