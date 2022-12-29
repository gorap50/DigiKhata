package com.example.digikhata;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.net.FileNameMap;

public class DBHelper extends SQLiteOpenHelper {

    public final static String DigiKhataDB = "DIGIKHATA";
    public final static String CustomerTable = "CUSTOMERS";
    public final static String Transactions = "TRANSACTIONS";
    public final static String EasyLoad = "EASYLOAD";
    public static int eldID=1;
    public static int custID=1;

    Context context;
    public DBHelper(@Nullable Context context) {
        super(context, DigiKhataDB, null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

        DB.execSQL("CREATE TABLE IF NOT EXISTS " + CustomerTable +
                " (NAME TEXT Primary key, USERID NUMBER ,AMOUNT TEXT)");

        DB.execSQL("CREATE TABLE IF NOT EXISTS " + Transactions +
                "(USERID NUMBER, DETAILS TEXT, AMOUNT TEXT)");

        DB.execSQL("CREATE TABLE IF NOT EXISTS " + EasyLoad +
                "(ID NUMBER, OPERATOR TEXT, NUMBER TEXT,AMOUNT TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {

    }

    public Boolean insertCustomer(String name, String amount) {
        SQLiteDatabase DB = this.getWritableDatabase();

        Toast.makeText(context, " in insert", Toast.LENGTH_SHORT).show();


        Toast.makeText(context, "after max", Toast.LENGTH_SHORT).show();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("userid", custID);
        contentValues.put("amount", amount);

        long result = DB.insert(CustomerTable, null, contentValues);
        if (result == -1) {
            Toast.makeText(context, "Not inserted", Toast.LENGTH_SHORT).show();
            return false;
        }
        custID++;
        return true;
    }

    public Boolean insertTransactions(int id, String details, String amount) {

        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("userid", id);
        contentValues.put("details", details);
        contentValues.put("amount", amount);

        long result = DB.insert(Transactions, null, contentValues);
        if (result == -1) {

            return false;
        }
        updateCustomerAmount(id, amount);

        return true;
    }

    public boolean insertEasyLoad(String operator,String number, String amount){
        SQLiteDatabase DB=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();

        contentValues.put("ID",eldID);
        contentValues.put("OPERATOR",operator);
        contentValues.put("NUMBER",number);
        contentValues.put("amount",amount);

        long result=DB.insert(EasyLoad,null,contentValues);

        if(result == -1)
            return false;

        eldID = eldID + 1;

        return true;
    }

    private void updateCustomerAmount(int id, String amount) {

        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor custAmount = DB.rawQuery("Select amount from " + CustomerTable + " where userId=" + id, null);
        custAmount.moveToFirst();

        String money = custAmount.getString(0);

        String upAmount = "";

        if (money.charAt(0) == '-') {
            int mny = Integer.parseInt(money.substring(1));
            if (amount.charAt(0) == '-') {
                int amt = Integer.parseInt(amount.substring(1));
                mny += amt;
                upAmount = "-" + mny;
            } else {
                int amt = Integer.parseInt(amount);
                amt = amt - mny;
                upAmount = "" + amt;
            }
        } else {
            int mny = Integer.parseInt(money);
            if (amount.charAt(0) == '-') {
                int amt = Integer.parseInt(amount.substring(1));
                mny -= amt;
                upAmount = "" + mny;
            } else {
                int amt = Integer.parseInt(amount);
                amt = amt + mny;
                upAmount = "" + amt;
            }
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put("amount", upAmount);
        int chk=DB.update(CustomerTable, contentValues, "userid=" + id, null);
        if(chk == -1){
            Toast.makeText(context.getApplicationContext(), "not updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context.getApplicationContext(), "updated", Toast.LENGTH_SHORT).show();
        }
    }


    public Boolean updateUserData(String name, String detail, String amount) {
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("detail", detail);
        contentValues.put("amount", amount);
        Cursor cursor = DB.rawQuery("select * from UserDetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("UserDetails", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deleteUserData(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from UserDetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("UserDetails", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getCustomers() {
        SQLiteDatabase DB = this.getWritableDatabase();

        return DB.rawQuery("select * from " + CustomerTable, null);
    }

    public Cursor getTransactions(int id) {
        SQLiteDatabase DB = this.getWritableDatabase();

        return DB.rawQuery("select * from " + Transactions + " Where userid="+id, null);
    }

    public Cursor getCustomerId(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();

        return DB.rawQuery("select * from " + CustomerTable +" Where name = '"+name+"'", null);
    }

    public Cursor getEasyLoads(){
        SQLiteDatabase DB = this.getWritableDatabase();

        return DB.rawQuery("select * from " + EasyLoad, null);
    }
}
