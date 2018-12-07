package com.developer.isabel.fastfood.Collection;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.isabel.fastfood.R;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter  extends BaseAdapter implements OnLoadImgService {
    private Context context;
    private List<Item> items;

    public ListAdapter(Context context, ArrayList<Item> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int i) {
        return this.items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflate = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflate.inflate(R.layout.item_ui, null);

        }
        TextView titulo = (TextView)view.findViewById(R.id.nombre);
        TextView calle = (TextView)view.findViewById(R.id.calle);
        TextView telefono = (TextView)view.findViewById(R.id.telefono);
        ImageView image = (ImageView)view.findViewById(R.id.imagesource);

        titulo.setText(this.items.get(i).getTitle());
        calle.setText(this.items.get(i).getStreet());
        telefono.setText(this.items.get(i).getPhone());

        ServiceImg hilo = new ServiceImg();
        hilo.setLoadImage(image, this);
        hilo.execute(this.items.get(i).getUrl());

        return view;
    }

    @Override
    public void setLoadImg(ImageView container, Bitmap img) {
        container.setImageBitmap(img);
    }
}
