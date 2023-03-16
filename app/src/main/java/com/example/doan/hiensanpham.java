package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifBitmapProvider;
import com.example.doan.DB.cart;
import com.example.doan.domain.menu_domain;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

public class hiensanpham extends AppCompatActivity {
    private TextView addtocarbtn;
    private TextView titleTxt, feetxt,  numberOderTxt;
    private ImageView plubtn, minbtun, picitem;
    private menu_domain object;
    private cart cart;
    private int numberOder =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiensanpham);
        cart = new cart(this);
        initView();
        getBunle();
        bottomNavigation();
    }
    private void bottomNavigation() {
        BottomNavigationItemView homebtn = findViewById(R.id.menu_home);
        BottomNavigationItemView menu = findViewById(R.id.menu_menu);
        BottomNavigationItemView btn_cart = findViewById(R.id.menu_cart);
        BottomNavigationItemView user = findViewById(R.id.menu_user);

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(hiensanpham.this, MainActivity.class));
            }
        });
        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(hiensanpham.this, cart22.class));
            }
        });
    }
    private void getBunle() {
        object = (menu_domain) getIntent().getSerializableExtra("object");

        int drawResour = this.getResources().getIdentifier(object.getPic(), "drawable", this.getCallingPackage());
        Glide.with(this)
                .load(drawResour)
                .into(picitem);
        titleTxt.setText(object.getTitle());
        feetxt.setText("$" +object.getTien());
        numberOderTxt.setText(String.valueOf(numberOder));

        plubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOder = numberOder + 1;
                numberOderTxt.setText(String.valueOf(numberOder));
                feetxt.setText(String.valueOf(numberOder*object.getTien()));
            }
        });
        minbtun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numberOder >1){
                    numberOder = numberOder -1;
                }
                numberOderTxt.setText(String.valueOf(numberOder));
                feetxt.setText(String.valueOf(numberOder*object.getTien()));
            }
        });
        addtocarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberIntCart(numberOder);
                cart.instertitem(object);
            }
        });

    }

    private void initView() {
        addtocarbtn = findViewById(R.id.btncart);
        titleTxt = findViewById(R.id.title2);
        feetxt = findViewById(R.id.totalEachItem4);
        numberOderTxt =findViewById(R.id.numberItem);
        plubtn = findViewById(R.id.Plusbtn);
        minbtun =findViewById(R.id.minusBtn);
        picitem = findViewById(R.id.itempic);
    }
}