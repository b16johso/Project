package com.example.brom.listviewjsonapp;

/**
 * Created by John on 2018-05-21.
 */

public class IceCream {


    private int image = R.drawable.temp_image;

    private String name;

    private int cost;

    public IceCream(String _name, int _cost) {
        this.name = _name;
        this.cost = _cost;
    }

    public int getImage() {
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
}
