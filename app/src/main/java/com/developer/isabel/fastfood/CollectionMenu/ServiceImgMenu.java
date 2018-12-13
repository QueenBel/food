package com.developer.isabel.fastfood.CollectionMenu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ServiceImgMenu extends AsyncTask<String, String, Bitmap> {
    private OnLoadImgServiceMenu IMGm;
    private ImageView imgCm;

    public void setLoadImgMenu(ImageView container, OnLoadImgServiceMenu img){
        IMGm = img;
        imgCm = container;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {

        //String url = strings[0];
        try{
            URL url = new URL(strings [0]);
            InputStream stream = url.openConnection().getInputStream();
            Bitmap imgBit = BitmapFactory.decodeStream(stream);
            return imgBit;

        }catch (MalformedURLException exp){
              Log.i("error", exp.getMessage());
        } catch (IOException exp){
              Log.i("error", exp.getMessage());
        }
        return null;
    }
    protected void onPostExecute(Bitmap result){
        IMGm.setLoadImgMenu(imgCm, result);
    }
}
