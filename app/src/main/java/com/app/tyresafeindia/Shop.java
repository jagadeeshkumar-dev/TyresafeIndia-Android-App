package com.app.tyresafeindia;


import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Shop extends Fragment {
    CardView bikescard,carscard,heavy;

    public Shop() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        bikescard=(CardView) view.findViewById(R.id.bikescard);
        carscard=(CardView) view.findViewById(R.id.carcard);
        heavy=(CardView) view.findViewById(R.id.heavycard);

        bikescard.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

                     Shop.this.startActivity(new Intent(Shop.this.getContext(),bikesactivity.class));
                 }


     });
        carscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Shop.this.startActivity(new Intent(Shop.this.getContext(),carsactivity.class));
            }


        });

        heavy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Shop.this.startActivity(new Intent(Shop.this.getContext(),heavy.class));
            }


        });
  return view;
    }

}
