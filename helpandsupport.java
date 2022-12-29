package com.example.digikhata;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;


public class helpandsupport extends AppCompatDialogFragment {


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstancesState){

        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater  inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.helpandsupport, null );
        builder.setView(view)
                .setTitle("Help & Support")
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
