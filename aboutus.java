package com.example.digikhata;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.textfield.TextInputEditText;


public class aboutus extends AppCompatDialogFragment {


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstancesState){

        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater  inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.aboutus, null );
        builder.setView(view)
                .setTitle("About Us")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

//
                    }
                });


        return builder.create();
    }
}
