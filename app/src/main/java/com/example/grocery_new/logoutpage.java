package com.example.grocery_new;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class logoutpage extends AppCompatActivity {

    Button logoutbtn, updatebtn, deletebtn, back;
    TextView pro_img;
    TextView pro_name, pro_email, pro_contact, head2, pro_address;
    CircleImageView show_image;
    LinearLayout view;
    //for fetching data
    SharedPreferences sharedPreferences;
    //so create a sharedpreferences name and also create key name
    private static final String SHARED_PREF_NAME = "my_pref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASS = "password";
    private static final String KEY_NAME="name";
    private static final String KEY_CONTACT="contact";
    private static final String KEY_ADDRESS="address";
    private static final String KEY_IMAGE="image";

    String email1,pass1,name,cno,add;
    String datareturn="";
    String[] datareturn1=new String[10];
    private List<MovieModel> movieList7 = new ArrayList<>();
    private MoviesAdapter mAdapter7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logoutpage);
        logoutbtn = findViewById(R.id.logout);
        updatebtn = findViewById(R.id.updatebtn);
        //deletebtn = findViewById(R.id.deletebtn);
        //pro_img = findViewById(R.id.pro_img);
        pro_name = findViewById(R.id.pro_name);
        pro_email = findViewById(R.id.pro_email);
        //pro_contact = findViewById(R.id.pro_contact);
        //pro_address = findViewById(R.id.pro_address);
        back = findViewById(R.id.pro_back);
        head2 = findViewById(R.id.pro_head);
        view = findViewById(R.id.view);
        show_image=findViewById(R.id.show_image);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(KEY_EMAIL, null);
        String password = sharedPreferences.getString(KEY_PASS, null);
        String name = sharedPreferences.getString(KEY_NAME, null);
        String contact = sharedPreferences.getString(KEY_CONTACT, null);
        String address = sharedPreferences.getString(KEY_ADDRESS, null);
        String url_img=sharedPreferences.getString(KEY_IMAGE,null);
        /* if (email!=null && password!=null){
            //set data on textview
            //email2txt.setText("Your email id:-  "+email);
        }*/
        //String data=get_details(email, password);

        /*char ch=name.charAt(0);
        pro_img.setText(String.format(String.valueOf(ch)));*/
        pro_name.setText(name);
        pro_email.setText(email);
        Glide.with(this).load(url_img).into(show_image);
        //call button for logout
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(logoutpage.this);
                builder.setTitle("LOG OUT:-");
                builder.setMessage("ARE YOU WANT TO LOG OUT?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();
                        Intent intent = new Intent(logoutpage.this, loginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MainActivity.this,"no button",Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                builder.create();
                builder.show();

            }
        });
        //set image in imageview

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setContentView(R.layout.updateprofile);
                Intent intent = new Intent(logoutpage.this, update_profile.class);
                intent.putExtra("email",email);
                intent.putExtra("password",password);
                startActivity(intent);
                finish();
                Toast.makeText(logoutpage.this, "Update Your Profile", Toast.LENGTH_SHORT).show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(logoutpage.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        head2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(logoutpage.this, logoutpage.class);
                startActivity(intent);
                finish();
            }
        });
        getSupportActionBar().hide();
        mAdapter7 = new MoviesAdapter(movieList7);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecyclerView recycle=findViewById(R.id.orders_list);
        recycle.setLayoutManager(mLayoutManager);
        recycle.setItemAnimator(new DefaultItemAnimator());
        recycle.setAdapter(mAdapter7);
        prepareMovieData1(email);
    }

    private void prepareMovieData1(String email) {
        String url = "https://grocerryproject.000webhostapp.com/androidfiles/myorders.php";
        StringBuffer buffer = new StringBuffer();
        RequestQueue queue = Volley.newRequestQueue(logoutpage.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(logoutpage.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        if (response != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("result");
                                int records = jsonArray.length();
                                if (records >0) {
                                    //String imgadd="https://grocerryproject.000webhostapp.com/androidfiles/img/";
                                    for(int j=0;j<jsonArray.length();j++) {
                                        JSONObject obj = jsonArray.getJSONObject(j);
                                        String img=obj.getString("p_img");
                                        String name=obj.getString("p_name");
                                        String price=obj.getString("p_price");
                                        String quantity=obj.getString("p_quantity");
                                        String description=obj.getString("p_des");
                                        MovieModel movie = new MovieModel(img, name, price, quantity, description, logoutpage.this);
                                        movieList7.add(movie);

                                    }
                                    mAdapter7.notifyDataSetChanged();

                                } else {
                                    /*buffer.append("Record Does Not Exists,Please Cheak Entered Details");
                                    builder.setCancelable(true);
                                    //builder.setTitle("Entries");
                                    builder.setMessage(buffer.toString());
                                    builder.show();*/
                                }
                                //responsetxt.setText(" "+records);
                            } catch (JSONException e) {
                                buffer.append("ERROR OCCURED!!!!...");
                                builder.setCancelable(true);
                                //builder.setTitle("Entries");
                                builder.setMessage(buffer.toString());
                                builder.show();
                            }
                        }
                               /* buffer.append(response);
                                AlertDialog.Builder builder=new AlertDialog.Builder(SlideshowFragment.super.getContext());
                                builder.setCancelable(true);
                                //builder.setTitle("Entries");
                                builder.setMessage(buffer.toString());
                                builder.show();*/

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                buffer.append("Cann't Load Product.\nPlease Check Your Internet Connection");
                builder.setCancelable(true);
                //builder.setTitle("Entries");
                builder.setMessage(buffer.toString());
                builder.show();

            }

        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("email", email);
                return data;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        //ending
    }


    public void onBackPressed() {
        Intent intent = new Intent(logoutpage.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}