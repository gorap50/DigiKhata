package com.example.digikhata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter3 extends ArrayAdapter<eLoad>{
    Context context;
    int resource;
    List<eLoad> transactions;

    public MyAdapter3(@NonNull Context context, int resource, @NonNull List<eLoad> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.transactions = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(this.resource, parent, false);

        eLoad load = transactions.get(position);

        TextView id = convertView.findViewById(R.id.id);
        TextView number = convertView.findViewById(R.id.number);
        TextView operator = convertView.findViewById(R.id.operator);
        TextView amount = convertView.findViewById(R.id.amount);

        id.setText(""+load.getId());
        number.setText(load.getNumber());
        operator.setText(load.getOperator());
        amount.setText(load.getAmount());

        return convertView;
    }
}
