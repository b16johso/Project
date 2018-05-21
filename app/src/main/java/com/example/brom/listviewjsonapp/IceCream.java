package com.example.brom.listviewjsonapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by John on 2018-05-21.
 */

public class IceCream {

    private String name;

    private int cost;

    private String imgurl;

    private String category;

    private String description;

    private int image;

    private int image1 = R.drawable.icecream_strut;

    private int image2 = R.drawable.icecream_pinne;

    private String strut = "Strut";

    private String pinne = "Pinne";
    public IceCream(String _name, int _cost, String _imgurl, String _category, String _description) {
        this.name = _name;
        this.cost = _cost;
        this.imgurl = _imgurl;
        this.category = _category;
        this.description = _description;
    }

    public int getImage() {
        Log.d("category: ", category);

        if (category.equals(strut)){
            this.image = image1;
        }

        else if (category.equals(pinne)){
            this.image = image2;
        }

        return image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return Integer.toString(cost)+" kr";
    }

    public String getImgurl() {
        return imgurl;
    }

    public String getDescription() {
        return description;
    }
}
