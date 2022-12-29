package com.example.digikhata;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends ArrayAdapter<User>{
    Context context;
    int resource;
    List<User> users;

    public MyAdapter(@NonNull Context context, int resource, @NonNull List<User> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.users = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        convertView = inflater.inflate(this.resource, parent, false);

        User user = users.get(position);

        TextView amount = convertView.findViewById(R.id.amount);
        TextView name =convertView.findViewById(R.id.name);

        name.setText(user.getName());
        amount.setText(user.getAmount());

        return convertView;
    }
}
