package com.example.grocery_new;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile_picture_up extends AppCompatActivity {
    Button pro_back,skip_btn;
    FloatingActionButton addImgBtn;
    public int PICK_IMAGE_REQUEST = 1;
    Button proUpdate;
    String email;
    CircleImageView profile_image;
    TextView nameShow;
     Bitmap bitmap;
    String ancodeImage;
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_picture_up);
    getSupportActionBar().hide();
        pro_back=(Button)findViewById(R.id.pro_back);
        skip_btn=findViewById(R.id.skip_btn);
         addImgBtn=findViewById(R.id.addImgBtn);
         profile_image=findViewById(R.id.profile_image);
        proUpdate=findViewById(R.id.proUpdate);
        nameShow=findViewById(R.id.nameShow);

        skip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(profile_picture_up.this,loginActivity.class);
                startActivity(intent);
                finish();
            }
        });



        //Receive INformation which is coming another page

Bundle bundle=getIntent().getExtras();
list=bundle.getStringArrayList("emp_data");
        nameShow.setText(list.get(1));


         //back page going btn
         pro_back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onBackPressed();
        }
    });

         ///image add btn
         addImgBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ImagePicker.with(profile_picture_up.this)
                    .crop()	    			//Crop image(Optional), Check Customization for more option
                    .compress(1024)			//Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                    .start();
        }
    });

//

        proUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ancodeImage!=null){
                email=list.get(0);

                    RequestQueue queue = Volley.newRequestQueue(profile_picture_up.this);
                    String url ="https://grocerryproject.000webhostapp.com/androidfiles/update_prfile_IMG.php";



                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Display the first 500 characters of the response string.
                                    if(response!=null){
                                        //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                                        Toast.makeText(profile_picture_up.this,"Picture upload successfully",Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(profile_picture_up.this,loginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                                    }

                                    //responText.setText("Response is: "+response);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(profile_picture_up.this,"That didn't work",Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        //java collaction
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> data=new HashMap<>();
                            data.put("E_image",ancodeImage);
                            data.put("E_mail",email);
                            return data;
                        }
                    };

// Add the request to the RequestQueue.
                    queue.add(stringRequest);







                }else {
                    Toast.makeText(profile_picture_up.this,"Please select the picture",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
///put the picure
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {



        if (data!=null && resultCode == RESULT_OK) {
          Uri file_path = data.getData();
          try {
                //xxx System.out.println("This is");
                InputStream inputStream = getContentResolver().openInputStream(file_path);
                bitmap = BitmapFactory.decodeStream(inputStream);
                //profile_image.setImageBitmap(bitmap);
                profile_image.setImageBitmap(bitmap);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100,stream);
                // bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);

                byte[] imgbyte = stream.toByteArray();
                ancodeImage = android.util.Base64.encodeToString(imgbyte, Base64.DEFAULT);

                //  imgbtn.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(profile_picture_up.this,"Picture is not Convart",Toast.LENGTH_SHORT).show();
               // posswText.setText("exp" + e);
            }


        }else {nameShow.setText("Disnot work");}
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onBackPressed(){
        Intent i=new Intent(profile_picture_up.this,loginActivity.class);
        startActivity(i);
        finish();
    }
}