package com.example.digikhata;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.digikhata.R;

public class MyDialog extends AppCompatDialogFragment {
    private EditText Name;
    private EditText Amount;
    private Spinner spMode;
    private MyDialogListener listener;
    DBHelper dbHelper;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (MyDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstancesState){

        dbHelper=new DBHelper(getActivity());

        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater  inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null );
        builder.setView(view)
                .setTitle("AddCustomer")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = Name.getText().toString();
                        String amount=Amount.getText().toString();
                        if(spMode.getSelectedItem().toString().equals("Receivable")){
                            Toast.makeText(getActivity(), "Name :"+name+" Amount : -"+amount,
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getActivity(), "Name :"+name+" Amount : "+amount,
                                    Toast.LENGTH_SHORT).show();
                        }
                        listener.applyTexts(name);
                    }
                });
        Name = view.findViewById(R.id.name);
        Amount=view.findViewById(R.id.amount);
        spMode=view.findViewById(R.id.spMode);

        return builder.create();
    }
//    public void onClickSave(View view){
//        if(spMode.getSelectedItem().toString().equals("Receivable")){
//            dbHelper.insertCustomer(Name.getText().toString().trim(),
//                    "-"+Amount.getText().toString().trim());
//        }
//        else{
//            dbHelper.insertCustomer(Name.getText().toString().trim(), Amount.getText().toString().trim());
//        }
//    }
    public interface MyDialogListener{
        void applyTexts(String name);
    }
}
