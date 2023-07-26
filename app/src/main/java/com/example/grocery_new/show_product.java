package com.example.grocery_new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class show_product extends AppCompatActivity {

    Button back_pro;
    TextView p_name2,p_quantity2,p_price2,p_des2,orderbtn;
    ImageView p_img2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
        findViewById(R.id.back_pro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(show_product.this,MainActivity.class);
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
        orderbtn=findViewById(R.id.orderbtn);
        p_img2=findViewById(R.id.p_img2);
        p_name2=findViewById(R.id.p_name2);
        p_quantity2=findViewById(R.id.p_quantity2);
        p_price2=findViewById(R.id.p_price2);
        p_des2=findViewById(R.id.p_des2);
        Picasso.with(this).load(p_img)
                .into(p_img2);
        p_name2.setText(p_name);
        p_quantity2.setText(p_quantity);
        p_price2.setText("₹"+p_price);
        p_des2.setText(p_des);
        orderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i=new Intent(show_product.this,orderpage.class);
               i.putExtra("p_img",p_img);
               i.putExtra("p_name",p_name);
               i.putExtra("p_quantity",p_quantity);
               i.putExtra("p_price",p_price);
               i.putExtra("p_des",p_des);
               startActivity(i);
            }
        });
        p_img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(show_product.this,showimg.class);
                i.putExtra("img",p_img);
                startActivity(i);
            }
        });
    getSupportActionBar().hide();
    }
}