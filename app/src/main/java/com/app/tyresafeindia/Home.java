package com.app.tyresafeindia;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ViewFlipper;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    ViewFlipper flipper;
    Button productdetails,explore;



    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        flipper = (ViewFlipper)view.findViewById(R.id.flipper);

        productdetails=(Button)view.findViewById(R.id.pd);
        explore=(Button)view.findViewById(R.id.explore);




        flipper.setAutoStart(true);
        flipper.setFlipInterval(3000);
        flipper.startFlipping();


        productdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Home.this.startActivity(new Intent(Home.this.getContext(),pd.class));
            }


        });

        explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Home.this.startActivity(new Intent(Home.this.getContext(),explore .class));
            }


        });


        return view;
    }


}
