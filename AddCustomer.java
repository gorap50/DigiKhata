package com.example.digikhata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddCustomer extends AppCompatActivity {

    private EditText Name;
    private EditText Amount;
    private Spinner spMode;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        dbHelper=new DBHelper(getApplicationContext());
        Name = findViewById(R.id.name);
        Amount=findViewById(R.id.amount);
        spMode=findViewById(R.id.spMode);
    }
    public void onClickAdd(View view){
        String name = Name.getText().toString();
        String amount=Amount.getText().toString();

        Toast.makeText(getApplicationContext(), spMode.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
        if(spMode.getSelectedItem().toString().equals("Receivable")){

            boolean check=dbHelper.insertCustomer(Name.getText().toString().trim(),
                    "-"+Amount.getText().toString().trim());
            if(check){
                Toast.makeText(getApplicationContext(), "Save", Toast.LENGTH_SHORT).show();
                onClickBack(view);
            }
            else{
                Toast.makeText(getApplicationContext(), "Not-Save", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            boolean check=dbHelper.insertCustomer(Name.getText().toString().trim(),
                    Amount.getText().toString().trim());

            if(check){
                Toast.makeText(getApplicationContext(), "Save", Toast.LENGTH_SHORT).show();
                onClickBack(view);
            }
            else{
                Toast.makeText(getApplicationContext(), "Not-Save", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onClickBack(View view){
        Intent intent= getIntent();
        setResult(1234,intent);
        finish();
    }

}