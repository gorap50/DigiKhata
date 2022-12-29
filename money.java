package com.example.digikhata;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class money extends Fragment{

    Button btn1, btn2 ,btn3, btn4, signIn, moneyIn, moneyOut;
    View view;
    TextView status;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    MyAdapter3 myAdapter3;
    ArrayList<eLoad> list;
    ListView mylist;

    DBHelper dbHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_money, container, false);
        status=view.findViewById(R.id.text1);
        dbHelper=new DBHelper(getActivity());

        setstatus();
        mylist=view.findViewById(R.id.listEasyLoads);
        poupulate();

        signIn = view.findViewById(R.id.signIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SignInDialog myDialog = new SignInDialog();
                myDialog.show(getActivity().getSupportFragmentManager(), "Sign In");
//                setstatus();
            }
        });

        moneyIn = view.findViewById(R.id.moneyIn);
        moneyIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moneyInDailog myDialog = new moneyInDailog();
                myDialog.show(getActivity().getSupportFragmentManager(), "Money IN");
            }
        });

        moneyOut = view.findViewById(R.id.moneyOut);
        moneyOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moneyOutDailog myDialog = new moneyOutDailog();
                myDialog.show(getActivity().getSupportFragmentManager(), "Money Out");
            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel("my notification", "my notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager= getActivity().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        btn3 = view.findViewById(R.id.btn3);
        btn4 = view.findViewById(R.id.btn4);


        Bundle bundle=new Bundle();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("operator","Telenor");
                MyDialog1 myDialog = new MyDialog1();
                myDialog.setArguments(bundle);
                myDialog.show(getActivity().getSupportFragmentManager(), "Easy Load");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("operator","Ufone");
                MyDialog1 myDialog = new MyDialog1();
                myDialog.setArguments(bundle);
                myDialog.show(getActivity().getSupportFragmentManager(), "Easy Load");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("operator","Zong");
                MyDialog1 myDialog = new MyDialog1();
                myDialog.setArguments(bundle);
                myDialog.show(getActivity().getSupportFragmentManager(), "Easy Load");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("operator","Jazz");
                MyDialog1 myDialog = new MyDialog1();
                myDialog.setArguments(bundle);
                myDialog.show(getActivity().getSupportFragmentManager(), "Easy Load");
            }
        });
        return view;
    }

    @SuppressLint("SetTextI18n")
    public void setstatus(){
        preferences= getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        editor=preferences.edit();
        String number=preferences.getString("number","0333-1234567");
        String amount=preferences.getString("money","0");

        status.setText("DIGI CASH"+"\n  "+number+"\n  Rs: "+amount);
    }
    public void poupulate(){
        list = new ArrayList<eLoad>();
        mylist.setAdapter(null);


        Cursor cursor=dbHelper.getEasyLoads();
        cursor.moveToFirst();
        eLoad loads;
        if(cursor.getCount() > 0){
            do{
                int id=cursor.getInt(0);
                String operator=cursor.getString(1);
                String number=cursor.getString(2);
                String amount=cursor.getString(3);


                loads=new eLoad(id,operator,number,amount);
                list.add(loads);
            }
            while (cursor.moveToNext());
        }

        myAdapter3 = new MyAdapter3( getContext(), R.layout.easyload, list);
        mylist.setAdapter(myAdapter3);
    }
}























