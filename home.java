package com.example.digikhata;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class home extends Fragment{

    boolean isReady = false;
    Fragment selectedFragment;
    FloatingActionButton fab;
    View view;
    ArrayList<User> list;
    MyAdapter adapter;
    ListView mylist;
    DBHelper dbHelper;
    ArrayList<String> transaction;
    TextView give,get;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.fragment_home, container, false);
        dbHelper=new DBHelper(view.getContext());
//        ((MainActivity) getActivity()).setFragmentListener(this);

        // codwe here
        mylist = view.findViewById(R.id.mylist);
        give=view.findViewById(R.id.textView2);
        get=view.findViewById(R.id.textView3);
        transaction=new ArrayList<String>();

        populate();

        fab= view.findViewById(R.id.floatingButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),AddCustomer.class);
                startActivityForResult(intent,1234);
            }
        });

        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if(!(list.isEmpty())){
//                    Toast.makeText(getActivity(), "In item", Toast.LENGTH_SHORT).show();
                    User user=list.get(position);
                    Bundle bundle=new Bundle();
                    bundle.putString("name",user.getName());

                    userData uData=new userData();
                    uData.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container,uData).commit();
                }
                else{

                }

            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        populate();
//        Toast.makeText(getContext(), "Returned", Toast.LENGTH_SHORT).show();
    }

    public void populate(){
//        Toast.makeText(getContext(), "In populate", Toast.LENGTH_SHORT).show();
        list = new ArrayList<User>();
        mylist.setAdapter(null);

        transaction.clear();
        Cursor cursor=dbHelper.getCustomers();
        cursor.moveToFirst();
        User customer;
        if(cursor.getCount() > 0){
            do{
                String name=cursor.getString(0);
                String amount=cursor.getString(2);
                transaction.add(amount);

                customer=new User(name,amount);
                list.add(customer);
            }
            while (cursor.moveToNext());
        }

        adapter = new MyAdapter( getContext(), R.layout.list, list);
        mylist.setAdapter(adapter);
        calc();
    }
    public void calc(){
        int gv=0;
        int gt=0;
        for(int i=0;i<transaction.size();i++){
            String str=transaction.get(i);
            if(str.charAt(0) == '-'){
                int a=Integer.parseInt(str.substring(1));
                gt=gt+a;
            }
            else{
                int a=Integer.parseInt(str);
                gv=gv+a;
            }
        }
        give.setText(" RS: "+gv+"\n You will give");
        get.setText(" RS: "+gt+"\n You will get");
    }

}