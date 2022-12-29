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

public class MyAdapter1 extends ArrayAdapter<User1>{
    Context context;
    int resource;
    List<User1> transactions;

    public MyAdapter1(@NonNull Context context, int resource, @NonNull List<User1> objects) {
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

        User1 trans = transactions.get(position);

        TextView got = convertView.findViewById(R.id.amountIn);
        TextView gave = convertView.findViewById(R.id.amountOut);
        TextView details = convertView.findViewById(R.id.details);

        details.setText(trans.getDetail());
        Toast.makeText(getContext(), "amount :"+trans.getAmount(), Toast.LENGTH_SHORT).show();
        if(trans.getAmount().charAt(0) == '-'){
           gave.setText(trans.getAmount().substring(1));
           got.setText("0");
        }
        else{
            got.setText(trans.getAmount());
            gave.setText("0");
        }
        return convertView;
    }
}
