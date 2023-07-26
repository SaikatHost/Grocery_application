package com.example.grocery_new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class profile extends AppCompatActivity {

    Button back;
    TextView home_btn,profile_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
       /* back=findViewById(R.id.back);
        home_btn=findViewById(R.id.home_btn);
        profile_btn=findViewById(R.id.profile_btn);*/

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.home_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(profile.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        getSupportActionBar().hide();
        findViewById(R.id.profile_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(profile.this,logoutpage.class);
                startActivity(i);
                finish();
            }
        });
    }

   public void onBackPressed(){
       Intent i=new Intent(profile.this,MainActivity.class);
       startActivity(i);
       finish();
   }
}