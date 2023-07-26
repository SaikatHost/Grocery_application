package com.example.grocery_new;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class newregs extends AppCompatActivity {
    TextView restxt, head3;
    EditText emailText, nameText, posswText, number, addrText, confirmpass;
    Button regBtn, back3;
    String ancodeImage;
    public int PICK_IMAGE_REQUEST = 1;
    ImageView imgbtn;
    public Uri file_path;
    public Bitmap bitmap;
    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newregs);
        String url = "https://techtreeprojects.000webhostapp.com/android/insertTable.php";
        restxt = (TextView) findViewById(R.id.restxt);
        emailText = (EditText) findViewById(R.id.mailText);
        nameText = (EditText) findViewById(R.id.nameText);
        posswText = (EditText) findViewById(R.id.passwText);
        confirmpass = (EditText) findViewById(R.id.confirmpass);
        addrText = (EditText) findViewById(R.id.addText);
        number = (EditText) findViewById(R.id.number);
        regBtn = (Button) findViewById(R.id.regBtn);
        imgbtn = (ImageView) findViewById(R.id.imgbtn);
        head3 = (TextView) findViewById(R.id.head3);
        back3 = (Button) findViewById(R.id.back3);
        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        head3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newregs.this, newregs.class);
                startActivity(intent);
                finish();
            }
        });
        getSupportActionBar().hide();

//put the image in gellery

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(newregs.this,"hello",Toast.LENGTH_SHORT).show();
                Dexter.withActivity(newregs.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                imageChooser();
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
                check = 1;
            }
        });


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(newregs.this);
                String E_mail = emailText.getText().toString();
                String E_name = nameText.getText().toString();
                String E_possward = posswText.getText().toString();
                String conf = confirmpass.getText().toString();
                String E_contact = number.getText().toString();
                String E_address = addrText.getText().toString();
                if (emailText.length() != 0 && nameText.length() != 0 && addrText.length() != 0 && posswText.length() != 0 && confirmpass.length() != 0 && number.length() == 10 && check == 1) {
                    if (E_possward.equals(conf)) {
                        calculation(E_mail, E_name, E_possward, E_contact, E_address);
                    } else {
                        builder1.setTitle("Both Password Does Not Match");
                    }
                } else {
                    builder1.setTitle("Please Enter All Fields Correctly");
                }
                builder1.create().show();
            }
        });

        emailText.setText("");
        nameText.setText("");
        posswText.setText("");
        addrText.setText("");
    }

    void imageChooser() {
        //Create an instence of the
        //intent of the type image
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //pass the constant to compare it
        //with the returned requestCod
        startActivityForResult(Intent.createChooser(intent, "Select Image"), 1);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            file_path = data.getData();
            //imgbtn.setImageURI(file_path);

            try {
                // System.out.println("This is");
                //InputStream inputStream = getContentResolver().openInputStream(file_path);
                InputStream inputStream = getContentResolver().openInputStream(file_path);
                //bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),file_path);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imgbtn.setImageBitmap(bitmap);
                Toast.makeText(newregs.this,""+bitmap,Toast.LENGTH_SHORT).show();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                //bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                 bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);

                byte[] imgbyte = stream.toByteArray();
                ancodeImage = Base64.encodeToString(imgbyte, Base64.DEFAULT);
                Toast.makeText(newregs.this,""+ancodeImage,Toast.LENGTH_SHORT).show();
                //  imgbtn.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
                posswText.setText("exp" + e);
            }

        }
    }

    public void calculation(String E_mail, String E_name, String E_possward, String E_contact, String E_address) {
        RequestQueue queue = Volley.newRequestQueue(newregs.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(newregs.this);
        StringBuffer buffer = new StringBuffer();
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://techtreeprojects.000webhostapp.com/android/insertTable.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        // emailText.setText(response);
                        //if (response != null) {
                        //Toast.makeText(newregs.this, response, Toast.LENGTH_SHORT).show();
                                    /*Intent intent=new Intent(MainActivity.this,listActivity.class);
                                    startActivity(intent);
                                    finish();*/
                        buffer.append(response);
                        restxt.setText(ancodeImage);
                        builder.setTitle(buffer.toString());
                        builder.create().show();
                        Toast.makeText(newregs.this,""+response,Toast.LENGTH_SHORT).show();
                       /* if (response=="New record inserted successfully") {
                            emailText.setText("");
                            nameText.setText("");
                            posswText.setText("");
                            addrText.setText("");
                            number.setText("");
                            confirmpass.setText("");
                        }*/
                       /* } else {
                            builder.setTitle(response);
                            builder.create().show();
                        }*/

                        //responText.setText("Response is: "+response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // responText.setText("That didn't work!");
                builder.setTitle("Some Error Occured");
                builder.create().show();
                //builder.setCancelable(false);
            }
        }) {
            @Override
            //java collaction
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("E_mail", E_mail);
                data.put("E_name", E_name);
                data.put("E_possward", E_possward);
                data.put("E_contact", E_contact);
                data.put("E_address", E_address);
                data.put("E_image", ancodeImage);
                return data;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void onBackPressed() {
        Intent intent = new Intent(newregs.this, loginActivity.class);
        startActivity(intent);
        finish();
    }
}

