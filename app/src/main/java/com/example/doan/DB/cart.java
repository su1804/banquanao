package com.example.doan.DB;

import android.content.Context;
import android.widget.Toast;

import com.example.doan.domain.menu_domain;
import com.example.doan.domain.inteface.changeNumber;

import java.util.ArrayList;

public class cart {
    private Context context;
    private static TinyDB tinyDB;

    public cart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void instertitem(menu_domain item) {
        ArrayList<menu_domain> listitem =getlistCart() ;
        boolean existAlready = false;
        int n= 0;
         for(int i = 0; i<listitem.size(); i++){
             if (listitem.get(i).getTitle().equals(item.getTitle())){
                 existAlready=true;
                 n=i;
                 break;
             }
         }
         if(existAlready){
             listitem.get(n).setNumberIntCart(item.getNumberIntCart());
         }else {
             listitem.add(item);
         }
         tinyDB.putListObject("CardList", listitem);
        Toast.makeText(context, "thêm vào giỏ hàng của bạn", Toast.LENGTH_SHORT).show();
    }

    public static ArrayList<menu_domain> getlistCart(){
        return tinyDB.getListObject("CardList");
    }

    public void plusNumberitem (ArrayList<menu_domain> listitem, int position, changeNumber changeNumber){
        if(listitem.get(position).getNumberIntCart()==1){
            listitem.remove(position);

        }else {
            listitem.get(position).setNumberIntCart(listitem.get(position).getNumberIntCart()-1);
        }
        tinyDB.putListObject("CardList",listitem);
        changeNumber.changed();
    }
    public static Double getTongTien(){
        ArrayList<menu_domain> listitem2 = getlistCart();
        double fee=0;
        for (int i = 0; i<listitem2.size(); i++){
            fee = fee+(listitem2.get(i).getTien()*listitem2.get(i).getNumberIntCart());
        }
        return fee;
    }
}
