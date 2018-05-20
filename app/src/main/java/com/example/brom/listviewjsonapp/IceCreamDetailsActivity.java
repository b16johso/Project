package com.example.brom.listviewjsonapp;

import android.app.Activity;
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

        description = "Yo.";

        setContentView(R.layout.activity_ice_cream_details);
        TextView textView = (TextView) findViewById(R.id.description_textView);
        textView.setText(description);
    }
}
