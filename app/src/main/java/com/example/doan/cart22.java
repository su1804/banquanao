package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.doan.DB.cart;
import com.example.doan.adapter.CartListadapter;
import com.example.doan.domain.inteface.changeNumber;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

public class cart22 extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private cart mangcart;
    private TextView totalFeetxt, taxTxt, delivaryTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart22);
        mangcart = new cart(this);

        initView();
        tinhtong();
        initList();
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
                startActivity(new Intent(cart22.this, MainActivity.class));
            }
        });
        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(cart22.this, cart22.class));
            }
        });
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListadapter(cart.getlistCart(), this, new changeNumber() {
            @Override
            public void changed() {
                tinhtong();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if(cart.getlistCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }

    }

    private void tinhtong() {
        double percentTax = 0.02; // bạn có thể thay đổi sản phẩm theo giá thuế.
        double delivary = 10; // bạn có thể thay đổi sản phẩm theo giá giao hàng.

        tax = Math.round((cart.getTongTien()*percentTax)*100.0)/100.0;
        double total = Math.round((cart.getTongTien()+tax+delivary)*100.0)/100.0;
        double itemtotal = Math.round(cart.getTongTien()*100.0)/100.0;
        totalFeetxt.setText("$"+ itemtotal);
        taxTxt.setText("$"+ tax);
        delivaryTxt.setText("$"+delivary);
        totalTxt.setText("$"+total);
    }

    private void initView() {
        totalFeetxt = findViewById(R.id.totalFeetxt);
        taxTxt = findViewById(R.id.taxTxt);
        delivaryTxt = findViewById(R.id.delivaryTxt);
        totalTxt = findViewById(R.id.totalTxt);
        recyclerViewList = findViewById(R.id.RV2);
        scrollView = findViewById(R.id.scrollView);
        emptyTxt = findViewById(R.id.emptyTxt);
    }
}