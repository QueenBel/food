package com.developer.isabel.fastfood.DetallesRestaurante;

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

public class ListAdapterR extends BaseAdapter implements OnLoadImgServiceR{
    private ArrayList<ItemR> list;
    private ArrayList<TextView> counter;
    private Context context;
    public ListAdapterR(Context context, ArrayList<ItemR>list){
        this.list=list;
        this.context=context;
        counter = new ArrayList<TextView>();
    }
    public TextView getCounter(int position) {
        return this.counter.get(position);
    }
    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_detaild_restaurant, null);
        }

        ImageView gallery1=convertView.findViewById(R.id.imagesource1);
        //TextView latitude1=convertView.findViewById(R.id.nombre1);
        //TextView longitude1=convertView.findViewById(R.id.nombre1);

        //latitude1.setText(this.list1.get(position).getLatitude());
        //longitude1.setText(this.list1.get(position).getLongitude());
        if(this.list.get(position).getImg()==null){
            ServiceImgR loader= new ServiceImgR();
            loader.setLoadCompleteImg(gallery1, position, this);
            loader.execute(this.list.get(position).getUrlimg().get(0));
        }else {
            gallery1.setImageBitmap(this.list.get(position).getImg().get(0));
        }

        return convertView;
    }
    @Override
    public void setLoadCompleteImgResult(ImageView img, int position, Bitmap imgsourceimg) {
        ArrayList<Bitmap> source = new ArrayList<Bitmap>();
        source.add(imgsourceimg);
        this.list.get(position).setImg(source);
        img.setImageBitmap(imgsourceimg);
    }
}
