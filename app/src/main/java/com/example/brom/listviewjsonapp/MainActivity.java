package com.example.brom.listviewjsonapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// Create a ListView as in "Assignment 1 - Toast and ListView"

// Retrieve data from Internet service using AsyncTask and the included networking code

// Parse the retrieved JSON and update the ListView adapter

// Implement a "refresh" functionality using Android's menu system


public class MainActivity extends AppCompatActivity {
    ArrayList<IceCream> iceCreamList = new ArrayList<>();
    List<String> iceCreamCost = new ArrayList<String>();
    List<String> iceCreamDescription = new ArrayList<String>();
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new FetchData().execute();

        setContentView(R.layout.activity_main);
        adapter = new IceCreamAdapter(getApplicationContext(),iceCreamList);

        ListView myListView = (ListView)findViewById(R.id.my_listview);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {
                //here v is your ListItem's layout.
                Intent myIntent = new Intent(MainActivity.this, IceCreamDetailsActivity.class);
                Bundle extras = new Bundle();
                extras.putString("DESCRIPTION",iceCreamList.get(arg2).getDescription());
                extras.putString("IMGURL",iceCreamList.get(arg2).getImgurl());
                myIntent.putExtras(extras);
                startActivity(myIntent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_refresh){
            adapter.clear();
            new FetchData().execute();
            return true;
        }
        else if (id == R.id.action_about){
            Intent myIntent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(myIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class FetchData extends AsyncTask<Void,Void,String>{
        @Override
        protected String doInBackground(Void... params) {
            Log.d("Evert", "Background");
            // These two variables need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a Java string.
            String jsonStr = null;

            try {
                // Construct the URL for the Internet service
                URL url = new URL("http://wwwlab.iit.his.se/b16johso/mobilprogrammering/glass.json");

                // Create the request to the PHP-service, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                jsonStr = buffer.toString();
                Log.d("Nisse", jsonStr);
                return jsonStr;
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in
                // attempting to parse it.
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("Network error", "Error closing stream", e);
                    }
                }
            }
        }
        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);
            // This code executes after we have received our data. The String object o holds
            // the un-parsed JSON string or is null if we had an IOException during the fetch.

            // Implement a parsing code that loops through the entire JSON and creates objects

            try {
                JSONArray mountains = new JSONArray(o);

                Log.d("Rock n roll McDonalds", mountains.toString());

                for (int i = 0; i < mountains.length(); i++){
                    JSONObject mountain = mountains.getJSONObject(i);

                    int id = mountain.getInt("ID");
                    String name = mountain.getString("name");
                    Log.d("Name: ", name);
                    String description = mountain.getString("location");
                    int cost = mountain.getInt("cost");
                    String imgurl = mountain.getString("auxdata");
                    String category = mountain.getString("category");

                    adapter.add(new IceCream(name, cost, imgurl, category, description));

                    Log.d("icarray", adapter.toString());
                }
            }

            catch (JSONException e){
                e.printStackTrace();
            }
        }
    }
}