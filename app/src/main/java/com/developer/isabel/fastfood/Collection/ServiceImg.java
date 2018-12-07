package com.developer.isabel.fastfood.Collection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ServiceImg extends AsyncTask<String, String, Bitmap> {
    private OnLoadImgService IMG;
    private ImageView imgC;
    private int position;
    public void setLoadImage(ImageView container, OnLoadImgService img){
        IMG = img;
        imgC = container;
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
            exp.printStackTrace();
            //Log.i("error", exp.getMessage());
        } catch (IOException exp){
            exp.printStackTrace();
            //Log.i("error", exp.getMessage());
        }
        return null;
    }
    protected void onPostExecute(Bitmap result){
        IMG.setLoadImg(imgC, result);
    }
}

