package com.example.digikhata;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class category extends Fragment {
    ImageButton a, h ;
    TextView textView;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_category, container, false);
        // codwe here

        a = view.findViewById(R.id.aboutUs);
        h = view.findViewById(R.id.helpandSupport);

textView= view.findViewById(R.id.log);

       a.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               aboutus myDialog = new aboutus();
               myDialog.show(getActivity().getSupportFragmentManager(), "About Us");
           }
       });
       h.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               helpandsupport myDialog = new helpandsupport();
               myDialog.show(getActivity().getSupportFragmentManager(), "Help & Support");

           }
       });
       textView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               logout myDialog = new logout();
               myDialog.show(getActivity().getSupportFragmentManager(), "Do you want to exit?");

           }

       });


        return view;

    }
}