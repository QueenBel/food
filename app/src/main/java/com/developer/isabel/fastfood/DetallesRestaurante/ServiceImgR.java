package com.developer.isabel.fastfood.DetallesRestaurante;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ServiceImgR extends AsyncTask<String, Bitmap, Bitmap> {
    private OnLoadImgServiceR event;
    private ImageView containerimg;
    private int position;
    public void setLoadCompleteImg(ImageView container, int position , OnLoadImgServiceR event){
        this.event=event;
        this.containerimg=container;
        this.position=position;
    }
    @Override
    protected Bitmap doInBackground(String... strings) {
        String url =strings[0];
        try {
            URL imgurl = new URL(url);
            InputStream file = imgurl.openConnection().getInputStream();
            Bitmap img = BitmapFactory.decodeStream(file);
            return img;
        }catch (MalformedURLException exp){
            Log.i("ERROR", exp.getMessage());
        }catch (IOException exp){
            Log.i("ERRORRR", exp.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        //super.onPostExecute(bitmap);
        this.event.setLoadCompleteImgResult(this.containerimg, this.position, bitmap);
    }
}
