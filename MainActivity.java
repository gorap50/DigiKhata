package com.example.digikhata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class    MainActivity extends AppCompatActivity implements MyDialog.MyDialogListener {

    Fragment selectedFragment;
    FragmentListener fragmentListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView Bnv = findViewById(R.id.bottomNav);

        Bnv.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new home()).commit();
//        getActivity

    }
    public void setFragmentListener(FragmentListener listener)
    {

        fragmentListener = listener;

    }

    public void sendDataToFragment(String name) {
        fragmentListener.updateFragmentList(name);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    selectedFragment = null;
                    switch(item.getItemId()){
                        case R.id.home:
                            selectedFragment = new home();
                            break;
                        case R.id.money:
                            selectedFragment = new money();
                            break;
                        case R.id.category:
                            selectedFragment = new category();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };


    @Override
    public void applyTexts(String name) {
        sendDataToFragment(name);
    }

    public interface FragmentListener {
        void updateFragmentList(String name);
    }
}