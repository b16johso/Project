package com.example.brom.listviewjsonapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

/**
 * Created by John on 2018-05-20.
 */

public class IceCreamDetailsActivity extends Activity {
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ice_cream_details);

        Intent myIntent = getIntent();
        Bundle extras = myIntent.getExtras();
        String description = extras.getString("DESCRIPTION");
        String imgurl = extras.getString("IMGURL");

        TextView textView = (TextView) findViewById(R.id.description_textView);
        textView.setText(description);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));
    }
}
