package com.app.tyresafeindia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


public class explore extends AppCompatActivity {

    CardView ourgallery,video,demo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        ourgallery=(CardView)findViewById(R.id.ourgallery);
        video=(CardView)findViewById(R.id.videos);
        demo=(CardView)findViewById(R.id.demo);


        ourgallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(explore.this,ourgallery.class);
                startActivity(i);
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(explore.this,video.class);
                startActivity(i);
            }
        });
        demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(explore.this,demo.class);
                startActivity(i);
            }
        });

    }
}
