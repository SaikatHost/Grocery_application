package com.example.grocery_new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class mysplash extends AppCompatActivity {

    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysplash);
        //img=findViewById(R.id.img);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //to start a project after starting page
                Intent intent=new Intent(mysplash.this,loginActivity.class);
                startActivity(intent);
                finish();
            }
        },1000);
        getSupportActionBar().hide();
    }
}