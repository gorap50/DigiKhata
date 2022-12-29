package com.example.digikhata;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class userData extends Fragment implements MainActivity.FragmentListener{

    FloatingActionButton fab;
    View view;
    DBHelper DB;
    ListView listView;
    String user;
    TextView cName;
    int id=1;

    public void newData() {

        cName=view.findViewById(R.id.CustomerName);
        cName.setText(this.user);

        DB = new DBHelper(getContext());

        findID();
    }
    public void findID(){
        Cursor cursor=DB.getCustomerId(this.user);
//        Toast.makeText(getContext(), " after cursor return", Toast.LENGTH_SHORT).show();
        cursor.moveToFirst();
        if(cursor.getCount() >0){
//            Toast.makeText(getActivity()," User found",Toast.LENGTH_SHORT).show();
            id=Integer.parseInt(cursor.getString(1));
//            Toast.makeText(getActivity()," ID: "+id,Toast.LENGTH_SHORT).show();
        }
        else{
//            Toast.makeText(getActivity()," User not found",Toast.LENGTH_SHORT).show();
            getActivity().onBackPressed();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_user_data, container, false);
        listView = view.findViewById(R.id.userdata);

//        Toast.makeText(getActivity()," in user data",Toast.LENGTH_SHORT).show();

        user=getArguments().getString("name");
        newData();

//        ((MainActivity) getActivity()).setFragmentListener(this);

        fab=view.findViewById(R.id.youGot);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),AddTransaction.class);
                intent.putExtra("id",id);
                startActivityForResult(intent,1234);
            }
        });

        populateData();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        populateData();
//        Toast.makeText(getContext(), "Returned:user data", Toast.LENGTH_SHORT).show();
    }

    public void populateData()
    {
        Cursor cursor = DB.getTransactions(id);

        ArrayList<User1> list = new ArrayList<User1>();
        cursor.moveToFirst();
        User1 trans;
        if(cursor.getCount() > 0){
            do{
                String details=cursor.getString(1);
                String amount=cursor.getString(2);

//                Toast.makeText(getContext(), "Details :"+details+" Amount : "+amount,
//                        Toast.LENGTH_SHORT).show();

                trans=new User1(amount,details);
                list.add(trans);
            }
            while (cursor.moveToNext());
        }
        MyAdapter1 myAdapter1=new MyAdapter1(getContext(),R.layout.list1,list);
        listView.setAdapter(myAdapter1);
    }



    @Override
    public void updateFragmentList(String name) {

    }
}