package com.healthy.a59070040.healthy.weight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.healthy.a59070040.healthy.R;

import java.util.ArrayList;
import java.util.List;

public class WeightAdapter extends ArrayAdapter<Weight> {

    List<Weight> weights = new ArrayList<Weight>();
    Context context;

    public WeightAdapter(Context context, int resource, List<Weight> objects) {
        super(context, resource, objects);
        this.weights = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View _weightItem = LayoutInflater.from(context).inflate(R.layout.fragment_weight_item, parent, false);

        TextView _date = _weightItem.findViewById(R.id.weight_item_date);
        TextView _weight = _weightItem.findViewById(R.id.weight_item_weight);

        Weight _row = weights.get(position);
        _date.setText(_row.getDate());
        _weight.setText(String.valueOf(_row.getWeight()));

        return _weightItem;
    }
}
