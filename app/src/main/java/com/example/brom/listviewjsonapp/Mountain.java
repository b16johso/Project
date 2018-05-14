package com.example.brom.listviewjsonapp;

/**
 * Created by John on 2018-04-23.
 */

public class Mountain {
    private String name;
    private String location;
    private int height;

    public Mountain(String _name, String _location, int _height){
        name = _name;
        location = _location;
        height = _height;
    }

    public String infoText() {
        return name+" - "+location+" - "+height+"kr";
    }

    @Override
    public String toString() {
        return name;
    }
}
