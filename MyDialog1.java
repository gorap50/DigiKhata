package com.example.digikhata;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class MyDialog1 extends AppCompatDialogFragment {

    DBHelper dbHelper;
    TextInputEditText number,amount;
    String operator="";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstancesState){
        dbHelper=new DBHelper(getActivity());
        operator=getArguments().getString("operator");

        sharedPreferences=getActivity().getSharedPreferences("user",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater  inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dailog1, null );
        builder.setView(view)
                .setTitle("Easy Load")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        storeData();
//
//                        NotificationCompat.Builder builder =  new NotificationCompat.Builder(getActivity(), "my notification");
//                        builder.setContentTitle("title");
//                        builder.setContentText(amount);
//                        builder.setSmallIcon(R.drawable.ic_notification);
//                        //builder.setAutoCancel(true);
//
//                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getActivity());
//                        managerCompat.notify(12345, builder.build());
                    }
                });

        number=view.findViewById(R.id.number);
        amount=view.findViewById(R.id.amount);

        return builder.create();
    }

    public void storeData(){

        String contact=number.getText().toString().trim();
        int money=Integer.parseInt(amount.getText().toString().trim());
        int avlb=Integer.parseInt(sharedPreferences.getString("money","0"));

        Toast.makeText(getActivity(), " Operator : "+operator+"\n Contact : "+contact +
                        " \n Amount : "+money, Toast.LENGTH_LONG).show();
        if(avlb >= money){
            boolean check=dbHelper.insertEasyLoad(operator,contact,money+"");

            if(check){
                Toast.makeText(getActivity(), "EasyLoad Done", Toast.LENGTH_SHORT).show();
                editor.putString("money",""+(avlb-money));
                editor.commit();
            }
            else{
                Toast.makeText(getActivity(), "Try Again -- Easyload", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getActivity(), "In-Sufficient Funds \n\n Try Again", Toast.LENGTH_SHORT).show();
        }
    }
}
