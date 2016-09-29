package com.example.faustocheca.photochooseshare;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    //private int numFotos = 1;
    //private Bitmap bitmap;
    private ArrayList <Uri> uriFiles;
    private ImageView imageView1, imageView2,imageView3;
    private Button button_takePics;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        button_takePics = (Button) findViewById(R.id.Butt_tiraFotos);
        imageView1 = (ImageView) findViewById(R.id.image_1);
        imageView2 = (ImageView) findViewById(R.id.image_2);
        imageView3 = (ImageView) findViewById(R.id.image_3);



        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            button_takePics.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[]
                    { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
                     }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                button_takePics.setEnabled(true);
            }
        }
    }


    public void takePictures(View view){

        uriFiles = new ArrayList<>();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);




        for (int i=1; i<4; i++){

            uriFiles.add(Uri.fromFile(getOutputMediaFile()));
            intent.putExtra(MediaStore.EXTRA_OUTPUT,uriFiles);

            startActivityForResult(intent, 100);

    }






    }

    private static File getOutputMediaFile() {

        File mediaStorageDir=null;
        File formattedFile = null;


        mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                Log.d("CameraDemo", "failed to create directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        formattedFile= new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");

        return formattedFile;



    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {

               uriFiles = data.getParcelableExtra(MediaStore.EXTRA_OUTPUT);
                // PROBLEM: urifiles size 3 and different

                Log.i(">>TAG", String.valueOf(uriFiles));
                // but next step Log urifiles is null   ¡¡




                imageView1.setImageURI(uriFiles.get(0));
                imageView2.setImageURI(uriFiles.get(1));
                imageView3.setImageURI(uriFiles.get(2));


            }

        }
    }




    }





