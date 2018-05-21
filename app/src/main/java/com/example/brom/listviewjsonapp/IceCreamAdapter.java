package com.example.brom.listviewjsonapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class IceCreamAdapter extends ArrayAdapter<IceCream> {

    private Context mContext;
    private List<IceCream> iceCreamList = new ArrayList<>();

    public IceCreamAdapter(@NonNull Context context, @LayoutRes ArrayList<IceCream> list) {
        super(context, 0 , list);
        mContext = context;
        iceCreamList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item_textview,parent,false);

        IceCream currentIceCream = iceCreamList.get(position);

        ImageView image = (ImageView)listItem.findViewById(R.id.my_item_imageview);
        image.setImageResource(currentIceCream.getImage());

        TextView name = (TextView) listItem.findViewById(R.id.primary_item_textview);
        name.setText(currentIceCream.getName());

        TextView cost = (TextView) listItem.findViewById(R.id.secondary_item_textview);
        cost.setText(currentIceCream.getCost());

        return listItem;
    }
}
