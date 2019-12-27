package com.app.tyresafeindia;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class Gallery extends Fragment {


    TextView ourwebsite,aboutcompany;
    ImageButton cfb,cinsta,cyoutube;
    ImageButton dlinked,dinsta,dgmail;




    public Gallery() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        ourwebsite=(TextView)view.findViewById(R.id.cwebsite);

        aboutcompany=(TextView)view.findViewById(R.id.abc);


        cfb=(ImageButton)view.findViewById(R.id.cfb);
        cinsta=(ImageButton)view.findViewById(R.id.cinsta);
        cyoutube=(ImageButton)view.findViewById(R.id.cyt);


        dlinked=(ImageButton)view.findViewById(R.id.dlinked);
        dinsta=(ImageButton)view.findViewById(R.id.dinsta);
        dgmail=(ImageButton)view.findViewById(R.id.dgmail);


     /** ourwebsite.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(Gallery.this.getContext(),webview.class);
              intent.putExtra("LINK",link);
          }
      });
     **/
        ourwebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="http://www.tyresafeindia.com";
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        cyoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://www.youtube.com/channel/UCDzXbrfHnCjHI0JS4FO5aZw";
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });


        cfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://www.facebook.com/tyresafeindia";
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });


        cinsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://instagram.com/_u/tyresafeindia");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/tyresafeindia")));
                };
            }
        });

        aboutcompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gallery.this.startActivity(new Intent(Gallery.this.getContext(),aboutcompany.class));
            }


        });


        dgmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String to="kjagadeesh74@gmail.com";

                Intent email=new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL,new String[]{to});
                email.setType("message/rfc822");

                startActivity(email.createChooser(email,"Choose an Email Client:"));
            }
        });


        dinsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("http://instagram.com/_u/k_jagadeeshkumar");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/k_jagadeeshkumar")));
                }

            }
        });

        dlinked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://www.linkedin.com/in/jagadeesh-kumar-a295a9195/";
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        return view;
    }

}
