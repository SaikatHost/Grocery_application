package com.example.grocery_new;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class new_registration extends AppCompatActivity {

    EditText email,name,mno,password,conpass,address;
    Button pro_back,regbtn;
    String t_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_registration);
        getSupportActionBar().hide();
        email=findViewById(R.id.email);
        name=findViewById(R.id.name);
        mno=findViewById(R.id.mno);
        password=findViewById(R.id.pass);
        conpass=findViewById(R.id.conpass);
        address=findViewById(R.id.address);
        pro_back=findViewById(R.id.pro_back);
        regbtn=findViewById(R.id.regBtn);
        pro_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1=email.getText().toString();
                String name1=name.getText().toString();
                String contact1=mno.getText().toString();
                String password1=password.getText().toString();
                String conpass1=conpass.getText().toString();
                String address1=address.getText().toString();
                AlertDialog.Builder b=new AlertDialog.Builder(new_registration.this);
                if(email.length()!=0 && name.length()!=0 && mno.length()==10 && password.length()!=0 && address.length()!=0) {
                    if (password1.equals(conpass1)) {
                       /* ProgressDialog p = new ProgressDialog(new_registration.this);
                        p.setMessage("Loading.Please wait...");
                        p.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        p.setProgress(3);
                        p.setMax(100);
                        p.setCancelable(true);
                        p.show();*/
                        regs(email1,name1,contact1,password1,address1);

                    }else {
                        b.setMessage("Both Password Does Not Match");
                    }
                    }else {
                    b.setMessage("Please Enter All Fields Correctly");
                }
                b.setCancelable(true);
                b.show();

            }
        });

        
        
    }

    private void create_table(String t_name1) {
        RequestQueue queue = Volley.newRequestQueue(new_registration.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(new_registration.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://grocerryproject.000webhostapp.com/androidfiles/create_new_table.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            //java collaction
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("tname", t_name1);
                //data.put("E_image", ancodeImage);
                return data;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public String regs(String email,String name,String contact,String password,String address){
        final String[] usertablename = new String[1];
        RequestQueue queue = Volley.newRequestQueue(new_registration.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(new_registration.this);
        StringBuffer buffer = new StringBuffer();
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://grocerryproject.000webhostapp.com/androidfiles/grocery_insert.php",
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
                        //restxt.setText(ancodeImage);
                        builder.setTitle(buffer.toString());
                        builder.create().show();
                        if(buffer.toString().equals("Account Created Successfully")) {
                           String[] usertable =email.split("@");
                           t_name =usertable[0];
                           create_table(t_name);
                           Toast.makeText(new_registration.this,""+t_name,Toast.LENGTH_SHORT).show();
                            ArrayList<String> list=new ArrayList<>();
                            list.add(email);
                            list.add(name);
                           Intent intent=new Intent(new_registration.this,profile_picture_up.class);
                           intent.putExtra("emp_data",list);
                           startActivity(intent);
                           finish();
                        }
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
                data.put("email", email);
                data.put("name", name);
                data.put("contact",contact);
                data.put("password", password);
                data.put("address", address);
                //data.put("E_image", ancodeImage);
                return data;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);
        return t_name;
    }
    public void onBackPressed(){
        Intent i=new Intent(new_registration.this,loginActivity.class);
        startActivity(i);
        finish();
    }
}