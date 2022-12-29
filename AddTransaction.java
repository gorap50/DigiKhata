package com.example.digikhata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTransaction extends AppCompatActivity {

    EditText descp,amount;
    int userId;
    DBHelper dbHelper;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        intent=getIntent();

        userId=intent.getIntExtra("id",0);

        dbHelper=new DBHelper(getApplicationContext());
        descp=findViewById(R.id.description);
        amount=findViewById(R.id.amount);

    }
    public void onClickAdd(View view){
        Button action=(Button) view;

        String desc = descp.getText().toString();
        String money=amount.getText().toString();

        switch (action.getId()){
            case R.id.btnIn:
                money=money;
                break;
            case R.id.btnOut:
                money="-"+money;
                break;
            default:
        }
        boolean check=dbHelper.insertTransactions(userId,desc.trim(),money.trim());
        if(check){
            Toast.makeText(getApplicationContext(), "Save", Toast.LENGTH_SHORT).show();
            onClickBack(view);
        }
        else{
            Toast.makeText(getApplicationContext(), "Not-Save", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickBack(View view){
        setResult(1234,intent);
        finish();
    }
}