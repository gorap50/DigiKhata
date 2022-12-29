package com.example.digikhata;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

public class moneyOutDailog extends AppCompatDialogFragment {
    private EditText amount;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    public Dialog onCreateDialog(Bundle savedInstancesState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.moneyoutdailog, null);
        builder.setView(view)
                .setTitle("Money Out")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        storeData();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        storeData();
                    }
                });

        amount=view.findViewById(R.id.amount);
        return builder.create();
    }
    public void storeData(){
        sharedPreferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        int money=Integer.parseInt(amount.getText().toString().trim());
        int num=Integer.parseInt(sharedPreferences.getString("money","0"));

//        Toast.makeText(getActivity(), " Money :"+money+"\n Num : "+num, Toast.LENGTH_SHORT).show();

        if(num >=money){
            editor.putString("money",""+(num-money));
            editor.commit();
        }
        else {
            Toast.makeText(getActivity(), "Transfer amount is greater than available amount.\n Try Again",
                    Toast.LENGTH_SHORT).show();
        }
        
    }

}
