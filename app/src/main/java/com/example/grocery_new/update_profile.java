package com.example.grocery_new;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class update_profile extends AppCompatActivity {
    EditText name5,update_name,update_contact,update_address;
    Button namebtn,back,update;
    TextView response,head2,update_email;
    String[] datareturn1=new String[10];
    SharedPreferences sharedPreferences;
    //so create a sharedpreferences name and also create key name
    private static final String SHARED_PREF_NAME = "my_pref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASS = "password";
    private static final String KEY_NAME="name";
    private static final String KEY_CONTACT="contact";
    private static final String KEY_ADDRESS="address";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
       /* name5=findViewById(R.id.name5);
        namebtn=findViewById(R.id.namebtn);
        response=findViewById(R.id.response);*/
        Bundle bundle=getIntent().getExtras();
        //String email=bundle.getString("email");
        //String password=bundle.getString("password");
        back=findViewById(R.id.pro_back);
        head2=findViewById(R.id.pro_head);
        update_name=findViewById(R.id.order_name);
        update_contact=findViewById(R.id.order_contact);
        update_address=findViewById(R.id.order_address);
        update_email=findViewById(R.id.order_email);
        update=findViewById(R.id.update);
        //sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        sharedPreferences=getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String email = sharedPreferences.getString(KEY_EMAIL, null);
        String password = sharedPreferences.getString(KEY_PASS, null);
        String name = sharedPreferences.getString(KEY_NAME, null);
        String contact = sharedPreferences.getString(KEY_CONTACT, null);
        String address = sharedPreferences.getString(KEY_ADDRESS, null);
        update_email.setText(email);
        update_name.setText(name);
        update_contact.setText(contact);
        update_address.setText(address);
       /* //starting
        String url = "https://grocerryproject.000webhostapp.com/androidfiles/grocery_showprofile.php";
        StringBuffer buffer = new StringBuffer();
        RequestQueue queue = Volley.newRequestQueue(update_profile.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(update_profile.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //responsetxt.setText(response);
                        if (response != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("result");
                                int records = jsonArray.length();
                                if (records == 1) {
                                    JSONObject obj = jsonArray.getJSONObject(0);
                                    datareturn1[0]=obj.getString("email");
                                    datareturn1[1]=obj.getString("name");
                                    datareturn1[2]=obj.getString("password");
                                    datareturn1[3]=obj.getString("contact");
                                    datareturn1[4]=obj.getString("address");
                                    update_email.setText(datareturn1[0]);
                                    update_name.setText(datareturn1[1]);
                                    update_contact.setText(datareturn1[3]);
                                    update_address.setText(datareturn1[4]);


                                } else {
                                    buffer.append("Record Does Not Exists,Please Cheak Entered Details");
                                    builder.setCancelable(true);
                                    //builder.setTitle("Entries");
                                    builder.setMessage(buffer.toString());
                                    builder.show();
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
                                builder.show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                buffer.append("It Didn't Work");
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
                data.put("password", password);
                return data;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        //ending
*/

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(update_profile.this,logoutpage.class);
                startActivity(intent);
                finish();
            }
        });
        head2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(update_profile.this,update_profile.class);
                startActivity(intent);
                finish();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=update_name.getText().toString();
                String contact=update_contact.getText().toString();
                String address=update_address.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(update_profile.this);
                if(name.length()!=0 && contact.length()==10 && address.length()!=0) {
                    //starting
                    String url = "https://grocerryproject.000webhostapp.com/androidfiles/grocery_update.php";
                    StringBuffer buffer = new StringBuffer();
                    RequestQueue queue = Volley.newRequestQueue(update_profile.this);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Display the first 500 characters of the response string.
                                    //responsetxt.setText(response);
                                    if (response != null) {
                                       buffer.append(response);
                                       builder.setMessage(buffer.toString());
                                        builder.create().show();
                                        editor.putString(KEY_NAME,name);
                                        editor.putString(KEY_CONTACT,contact);
                                        editor.putString(KEY_ADDRESS,address);
                                        editor.apply();

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
                            buffer.append("It Didn't Work");
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
                            data.put("name", name);
                            data.put("contact", contact);
                            data.put("password", password);
                            data.put("address", address);
                            return data;
                        }
                    };

                    // Add the request to the RequestQueue.
                    queue.add(stringRequest);
                    //ending
                }else {
                    builder.setMessage("Please Fill The Details Correctly");
                    builder.create().show();
                }

            }
        });
        getSupportActionBar().hide();
    }
    public void onBackPressed()
    {
        Intent intent=new Intent(update_profile.this,MainActivity.class);
        //intent.putExtra("email",)
        startActivity(intent);
        finish();
    }


}