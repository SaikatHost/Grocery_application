package com.example.grocery_new;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class loginActivity extends AppCompatActivity {

    EditText name,password1;
    Button loginbtn,forget,newacc;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME="my_pref";
    private static final String KEY_EMAIL="email";
    private static final String KEY_PASS="password";
    private static final String KEY_NAME="name";
    private static final String KEY_CONTACT="contact";
    private static final String KEY_ADDRESS="address";
    private static final String KEY_IMAGE="image";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name=findViewById(R.id.log_name);
        password1=findViewById(R.id.log_password);
        loginbtn=findViewById(R.id.loginbtn);
        //forget=findViewById(R.id.forget);
        newacc=findViewById(R.id.newacc);
        sharedPreferences=getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        if (sharedPreferences.contains(KEY_EMAIL) && sharedPreferences.contains(KEY_PASS)) {
            String email = sharedPreferences.getString(KEY_EMAIL, null);
            String pass = sharedPreferences.getString(KEY_PASS, null);
            String name = sharedPreferences.getString(KEY_NAME, null);
            String contact = sharedPreferences.getString(KEY_CONTACT, null);
            String address = sharedPreferences.getString(KEY_ADDRESS, null);
            //10.if data is available goto home page
            Intent intent = new Intent(loginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        newacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(loginActivity.this,"create new account",Toast.LENGTH_SHORT).show();
               // Intent intent=new Intent(loginActivity.this,profile_picture_up.class);
                Intent intent=new Intent(loginActivity.this,new_registration.class);
                startActivity(intent);
                finish();
            }
        });
        //9.when open activity first cheak shared preference data is available or not
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.length()!=0 && password1.length()!=0){
                    String name1=name.getText().toString();
                    String pass1=password1.getText().toString();
                    /*editor.putString(KEY_EMAIL,name1);
                    editor.putString(KEY_PASS,pass1);
                    editor.apply();
                    //editor.commit();
                    Toast.makeText(loginActivity.this,"welcome "+name1+" with password "+pass1,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginActivity.this, logoutpage.class);
                    startActivity(intent);
                    finish();*/
                    funclogin(editor,name1,pass1);
                }else {
                    Toast.makeText(loginActivity.this,"Please enter all field",Toast.LENGTH_SHORT).show();
                }
            }
        });
        getSupportActionBar().hide();

    }
    /**/
    public void funclogin(SharedPreferences.Editor editor, String name1, String pass1){
        //starting
        String url = "https://grocerryproject.000webhostapp.com/androidfiles/grocery_showprofile.php";
        StringBuffer buffer = new StringBuffer();
        RequestQueue queue = Volley.newRequestQueue(loginActivity.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(loginActivity.this);
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
                                    /*datareturn1[0]=email1=obj.getString("email");
                                    datareturn1[1]=name=obj.getString("name");
                                    datareturn1[2]=pass1=obj.getString("password");
                                    datareturn1[3]=cno=obj.getString("contact");
                                    datareturn1[4]=add=obj.getString("address");
                                    char ch=name.charAt(0);
                                    pro_img.setText(String.format(String.valueOf(ch)));
                                    pro_name.setText(name);
                                    pro_email.setText(email1);
                                    Toast.makeText(logoutpage.this,""+datareturn1[0]+datareturn1[1]+datareturn1[2]+datareturn1[3]+datareturn1[4],Toast.LENGTH_SHORT).show();
                                    */
                                    editor.putString(KEY_EMAIL,obj.getString("email"));
                                    editor.putString(KEY_NAME,obj.getString("name"));
                                    editor.putString(KEY_PASS,obj.getString("password"));
                                    editor.putString(KEY_CONTACT,obj.getString("contact"));
                                    editor.putString(KEY_ADDRESS,obj.getString("address"));
                                    String image=obj.getString("image");
                                    editor.putString(KEY_IMAGE,"https://grocerryproject.000webhostapp.com/androidfiles/uplod_imges/"+image);
                                    editor.apply();
                                    //editor.commit();
                                    Toast.makeText(loginActivity.this,"welcome To Our Online Store",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(loginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    buffer.append("Login Failed,Please Cheak Entered Details");
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
                data.put("email", name1);
                data.put("password", pass1);
                return data;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        //ending

        /*RequestQueue queue = Volley.newRequestQueue(loginActivity.this);
        String url = "https://grocerryproject.000webhostapp.com/androidfiles/check_user.php";
        StringBuffer buffer = new StringBuffer();
        AlertDialog.Builder builder=new AlertDialog.Builder(loginActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //responsetxt.setText(response);
                        Toast.makeText(loginActivity.this," "+response,Toast.LENGTH_SHORT).show();
                        if (response.equals("Login successful")) {
                            editor.putString(KEY_EMAIL,name1);
                            editor.putString(KEY_PASS,pass1);
                            editor.apply();
                            //editor.commit();
                            Toast.makeText(loginActivity.this,"welcome To Our Online Store",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(loginActivity.this, logoutpage.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                builder.setTitle("Cannot Connect To The Server");

            }

        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("email", name1);
                data.put("password",pass1);
                return data;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        builder.create().show();
        */
    }
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(loginActivity.this);
        builder.setTitle("EXIT:-");
        builder.setMessage("DO YOU WANT TO EXIT?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(MainActivity.this,"yes button",Toast.LENGTH_SHORT).show();
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

}