package com.example.doan.domain;

import java.io.Serializable;

public class menu_domain implements Serializable {
    private String title;
    private String pic;
    private int numberIntCart;

    public int getNumberIntCart() {
        return numberIntCart;
    }

    public void setNumberIntCart(int numberIntCart) {
        this.numberIntCart = numberIntCart;
    }

    private double tien;

    public menu_domain(String title, String pic, double tien) {
        this.title = title;
        this.pic = pic;

        this.tien = tien;
    }

    public String getTitle() {
        return title;
    }

    public String getPic() {
        return pic;
    }


    public double getTien() {
        return tien;
    }
}

