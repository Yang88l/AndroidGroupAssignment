package com.example.groupassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] names;
    private final String[] prices;
    //private final String[] images;

    public CustomAdapter(Context context, String[] names, String[] prices) {
        super(context, R.layout.list_plan_history, R.id.simpleListView, names);
        this.context = context;
        this.names = names;
        this.prices = prices;
        //this.images = images;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_plan_history, parent, false);

        TextView nameTextView = rowView.findViewById(R.id.textView);
        nameTextView.setText(names[position]);

        TextView priceTextView = rowView.findViewById(R.id.textView25);
        priceTextView.setText(prices[position]);
/*
        ImageView iconImageView = rowView.findViewById(R.id.icon);
        iconImageView.setImageResource(context.getResources().getIdentifier(images[position] + "_77", "drawable", context.getPackageName()));
*/
        return rowView;
    }
}
