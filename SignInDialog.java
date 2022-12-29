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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

public class SignInDialog extends AppCompatDialogFragment {
    private  EditText number,password;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    public Dialog onCreateDialog(Bundle savedInstancesState) {
        sharedPreferences= getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogsignin, null);
        builder.setView(view)
                .setTitle("Sign In")
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
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = editText.getText().toString();
//                listener.applyTexts(name);
//
//            }
//        });
        number= view.findViewById(R.id.number);
        password = view.findViewById(R.id.password);
        return builder.create();
    }
    public void storeData(){
        String contact=number.getText().toString().trim();
        String pass=password.getText().toString().trim();

        editor=sharedPreferences.edit();
        editor.putString("number",contact);
        editor.putString("password",pass);
        editor.commit();
    }

}
