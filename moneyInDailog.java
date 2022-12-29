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

import androidx.appcompat.app.AppCompatDialogFragment;

public class moneyInDailog extends AppCompatDialogFragment {
    private EditText amount;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    public Dialog onCreateDialog(Bundle savedInstancesState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.moneyindailog, null);
        builder.setView(view)
                .setTitle("Money In")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
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

        String money=amount.getText().toString().trim();
        String num=sharedPreferences.getString("money","0");

        editor=sharedPreferences.edit();
        editor.putString("money",""+(Integer.parseInt(num)+Integer.parseInt(money)));
        editor.commit();
    }
}
