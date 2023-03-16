package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.doan.adapter.menuadapter;
import com.example.doan.domain.menu_domain;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView.Adapter adapter;
    RecyclerView recyclerViewList;
    ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();

        actionViewFip();
        recyclerViewList();

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
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, cart22.class));
            }
        });
    }

    private  void recyclerViewList(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewList = findViewById(R.id.recyclerView);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        ArrayList<menu_domain> menuList = new ArrayList<>();
        menuList.add(new menu_domain("áo thun", "img_2", 100.000));
        menuList.add(new menu_domain("áo khoát", "img", 150.000));
        menuList.add(new menu_domain("quần jean", "img_1", 250.000));
        menuList.add(new menu_domain("quần tây", "img_3", 150.000));
        menuList.add(new menu_domain("áo sweater", "img_4", 120.000));
        menuList.add(new menu_domain("quần short", "img", 120.000));
        adapter =new menuadapter(menuList) ;
        recyclerViewList.setAdapter(adapter);
    }

//  =======================================quang cao===================================================
    private void actionViewFip() {
        List<String> mquangcao= new ArrayList<>();
        mquangcao.add("https://img.freepik.com/free-psd/fashion-store-template-banner_23-2148707242.jpg");
        mquangcao.add("https://img.freepik.com/free-psd/horizontal-banner-template-online-fashion-sale_23-2148585405.jpg");
        mquangcao.add("https://static.vecteezy.com/system/resources/thumbnails/008/174/590/small/fashion-advertising-web-banner-illustration-vector.jpg");
        for(int i = 0; i<mquangcao.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(mquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation silde_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.silde_quangcao);
        Animation silde_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.silde_out);
        viewFlipper.setInAnimation(silde_in);
        viewFlipper.setOutAnimation(silde_out);
    }


    private void anhxa() {
        viewFlipper = findViewById(R.id.quangcao);
    }
}