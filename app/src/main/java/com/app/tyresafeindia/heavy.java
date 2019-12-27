package com.app.tyresafeindia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class heavy extends AppCompatActivity {
    Button heavy;

    public String stat="Other type of vechile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heavy);

        heavy=(Button)findViewById(R.id.heavy);


        heavy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(heavy.this,Booknow.class);
                intent.putExtra("finaltitle",stat);
                startActivity(intent);
            }
        });


    }
}
