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
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.developer.isabel.fastfood.utils.BitmapStruct;
import com.developer.isabel.fastfood.utils.BitmapStructMenu;
import com.developer.isabel.fastfood.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cz.msebera.android.httpclient.Header;

public class PhotoMenu extends AppCompatActivity {
    private final int CODEM=100;
    private final int CODE_PERMISSION=101;
    private ImageView IMG_M;
    private ImageButton btn_M;
    private Button SEND_M;
    private BitmapStructMenu DATA_IMG_M;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_menu);
        btn_M=findViewById(R.id.cameraM);
        SEND_M=findViewById(R.id.registerM);
        IMG_M=findViewById(R.id.imageM);

        btn_M.setVisibility(View.INVISIBLE);
        if(reviewPermissions()){
            btn_M.setVisibility(View.VISIBLE);
        }
        SEND_M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DATA_IMG_M!=null){
                    AsyncHttpClient client=new AsyncHttpClient();
                    File img=new File(DATA_IMG_M.path);
                    RequestParams params = new RequestParams();
                    try {
                        //client.addHeader("authorization", Data.TOKEN);
                        params.put("img", img);
                        if(BitmapStructMenu.ID_M!=null){
                            client.post(Data.UPLOAD_MENUS +"?id="+ BitmapStructMenu.ID_M, params, new JsonHttpResponseHandler(){
                                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                    Intent menus = new Intent(PhotoMenu.this, ViewMenus.class);
                                    PhotoMenu.this.startActivity(menus);


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
        btn_M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                PhotoMenu.this.startActivityForResult(camera, CODEM);
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
    private BitmapStructMenu saveToInternalStorage(Bitmap bitmapImage){

        ContextWrapper cw1=new ContextWrapper(getApplicationContext());
        //path to /data/data/yourapp/ap_data/imageDir
        File directory= cw1.getDir("imageDir", Context.MODE_PRIVATE);
        //create imageDir
        File mypath=new File(directory,"profile1.jpg");
        FileOutputStream fos1=null;
        try{
            fos1= new FileOutputStream(mypath);
            //use the compress method on the bitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos1);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                fos1.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        String path=directory.getAbsolutePath()+"/profile1.jpg";
        BitmapStructMenu pM= new BitmapStructMenu();
        pM.img=BitmapFactory.decodeFile(path);
        pM.path=path;
        return pM;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(CODE_PERMISSION==requestCode){
            if(permissions.length==3){
                btn_M.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CODEM){
            Bitmap img=(Bitmap)data.getExtras().get("data");
            DATA_IMG_M=saveToInternalStorage(img);
            IMG_M.setImageBitmap(DATA_IMG_M.img);
        }
    }
}
