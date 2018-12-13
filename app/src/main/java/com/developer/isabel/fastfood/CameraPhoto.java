package com.developer.isabel.fastfood;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.developer.isabel.fastfood.utils.BitmapStruct;
import com.developer.isabel.fastfood.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cz.msebera.android.httpclient.Header;

public class CameraPhoto extends AppCompatActivity {
    private final int CODE=100;
    private final int CODE_PERMISSION=101;
    private ImageView IMG;
    private ImageButton btn;
   // private final int RESOLUCION_IMG=100;
    private Button SEND;
    private BitmapStruct DATA_IMG;
    private Context root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_photo);
         btn=findViewById(R.id.camera);
        SEND=findViewById(R.id.register);
        IMG=findViewById(R.id.image);

        //MENU=findViewById(R.id.add_menu);

        btn.setVisibility(View.INVISIBLE);
        if(reviewPermissions()){
            btn.setVisibility(View.VISIBLE);
        }
      /*  MENU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registermenu = new Intent(CameraPhoto.this, RegisterMenu.class);
                CameraPhoto.this.startActivity(registermenu);
            }
        });*/

        SEND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(DATA_IMG!=null){
                 AsyncHttpClient client=new AsyncHttpClient();
                 client.addHeader("authorization", Data.TOKEN);
                 File img=new File(DATA_IMG.path);
                 RequestParams params = new RequestParams();
                 try {
                     //client.addHeader("authorization", Data.TOKEN);
                     params.put("img1", img);
                     if(BitmapStruct.ID!=null){
                         client.post(Data.UPLOAD_RESTAURANT +"?id="+ BitmapStruct.ID, params, new JsonHttpResponseHandler(){
                             public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                         Intent restaurante = new Intent(CameraPhoto.this, ViewRestaurant.class);
                                         CameraPhoto.this.startActivity(restaurante);


                                 //Toast.makeText(CameraPhoto.this, "EXITO", Toast.LENGTH_LONG).show();

                                 //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONObject) was not overriden, but callback was received");
                             }
                         });
                     }
                 } catch(FileNotFoundException e) {
                     e.printStackTrace();
                 }
             }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                CameraPhoto.this.startActivityForResult(camera, CODE);
            }
        });
    }

    private boolean reviewPermissions() {
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return true;
        }
        if(this.checkSelfPermission(Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
            return true;
        }
        requestPermissions(new String [] {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_PERMISSION);
        return false;
    }
    //video cambio de String a Bitmap es desde 1:30:00
    //private Bitmap saveToInternalStorage(Bitmap bitmapImage){
    private BitmapStruct saveToInternalStorage(Bitmap bitmapImage){
    //private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw=new ContextWrapper(getApplicationContext());
        //path to /data/data/yourapp/ap_data/imageDir
        File directory= cw.getDir("imageDir", Context.MODE_PRIVATE);
        //create imageDir
        File mypath=new File(directory,"profile.jpg");
        FileOutputStream fos=null;
        try{
            fos= new FileOutputStream(mypath);
            //use the compress method on the bitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        String path=directory.getAbsolutePath()+"/profile.jpg";
        BitmapStruct p= new BitmapStruct();
        p.img=BitmapFactory.decodeFile(path);
        p.path=path;
        return p;
        //return BitmapFactory.decodeFile(path);
        //return directory.getAbsolutePath();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(CODE_PERMISSION==requestCode){
            if(permissions.length==3){
                btn.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CODE){
            Bitmap img= (Bitmap)data.getExtras().get("data");
            //IMG.setImageBitmap(img);
            /*cambio de String a Bitmap*/
            //Bitmap ss=saveToInternalStorage(img);
            //IMG.setImageBitmap(ss);
             DATA_IMG =saveToInternalStorage(img);
             IMG.setImageBitmap(DATA_IMG.img);
        }
    }
}
/*me e quedado en 1:33:00*/