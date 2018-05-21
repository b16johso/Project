package com.example.brom.listviewjsonapp;

/**
 * Created by John on 2018-04-23.
 */

public class Mountain {
    private String name;
    private String location;
    private int cost;
    private String imgurl;

    public Mountain(String _name, String _location, int _cost, String _imgurl){
        name = _name;
        location = _location;
        cost = _cost;
        imgurl = _imgurl;
    }

    public String infoText() {
        return location;
    }

    public String infoImage() {
        return imgurl;
    }

    public String infoCost() { return Integer.toString(cost)+"kr";}

    public String infoName() {
        return name;
    }
}
