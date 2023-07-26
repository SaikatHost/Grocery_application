package com.example.grocery_new;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class orderpage extends AppCompatActivity {

    TextView p_name3,p_quantity3,p_price3,order_email,order_name,order_contact,order_address;
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
        setContentView(R.layout.activity_orderpage);
        p_name3=findViewById(R.id.p_name3);
        p_quantity3=findViewById(R.id.p_quantity3);
        p_price3=findViewById(R.id.p_price3);
        order_email=findViewById(R.id.order_email);
        order_name=findViewById(R.id.order_name);
        order_contact=findViewById(R.id.order_contact);
        order_address=findViewById(R.id.order_address);
        findViewById(R.id.backbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(orderpage.this,show_product.class);
                startActivity(i);
                finish();
            }
        });
        Bundle bundle=getIntent().getExtras();
        String p_img=bundle.getString("p_img");
        String p_name=bundle.getString("p_name");
        String p_quantity=bundle.getString("p_quantity");
        String p_price=bundle.getString("p_price");
        String p_des=bundle.getString("p_des");
        p_name3.setText(p_name);
        p_price3.setText("₹ "+p_price);
        p_quantity3.setText(p_quantity);
        
        sharedPreferences=getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String email = sharedPreferences.getString(KEY_EMAIL, null);
        String password = sharedPreferences.getString(KEY_PASS, null);
        String name = sharedPreferences.getString(KEY_NAME, null);
        String contact = sharedPreferences.getString(KEY_CONTACT, null);
        String address = sharedPreferences.getString(KEY_ADDRESS, null);
        order_name.setText(name);
        order_email.setText(email);
        order_contact.setText(contact);
        order_address.setText(address);
        
        findViewById(R.id.placeorder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1=order_email.getText().toString();
                String name1=order_name.getText().toString();
                String contact1=order_contact.getText().toString();
                String address1=order_address.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(orderpage.this);
                if(email1.length()!=0 && name1.length()!=0 && contact1.length()==10 && address1.length()!=0) {
                    //starting
                    String url = "https://grocerryproject.000webhostapp.com/androidfiles/order_product.php";
                    StringBuffer buffer = new StringBuffer();
                    RequestQueue queue = Volley.newRequestQueue(orderpage.this);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Display the first 500 characters of the response string.
                                    //responsetxt.setText(response);
                                    if (response != null) {
                                        buffer.append(response);
                                        builder.setMessage(buffer.toString());
                                        builder.setCancelable(true);
                                        builder.show();
                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            buffer.append("Cann't Place Order\nPlease Try again Later");
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
                            data.put("email", email1);
                            data.put("name", name1);
                            data.put("contact", contact1);
                            data.put("address", address1);
                            data.put("p_img", p_img);
                            data.put("p_name", p_name);
                            data.put("p_quantity", p_quantity);
                            data.put("p_price", p_price);
                            data.put("p_des", p_des);
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
}