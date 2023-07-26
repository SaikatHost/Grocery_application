package com.example.grocery_new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class showimg extends AppCompatActivity {
ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showimg);
        img=findViewById(R.id.img);
        Bundle bundle=getIntent().getExtras();
        String imgcode=bundle.getString("img");
        Picasso.with(showimg.this).load(imgcode)
                .into(img);
        findViewById(R.id.back_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(showimg.this,MoviesAdapter.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().hide();
    }
}