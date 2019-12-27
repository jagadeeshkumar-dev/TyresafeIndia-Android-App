package com.app.tyresafeindia;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static com.app.tyresafeindia.carsactivity.Image_URL;
import static com.app.tyresafeindia.carsactivity.Product_Title;
import static com.app.tyresafeindia.carsactivity.tyresize;


public class Detailactivity extends AppCompatActivity {
    ImageView imageView;
    TextView textViewTitle,textViewPrice,texttyre,txtqunty;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailactivity);

        Intent intent=getIntent();
        String image=intent.getStringExtra(Image_URL);
        final String title=intent.getStringExtra(Product_Title);
        String tyressize=intent.getStringExtra(tyresize);

        //double price=intent.getDoubleExtra(Product_Price);
        Bundle bundle = getIntent().getExtras();
        int productprice = bundle.getInt("price");
        int productqunty = bundle.getInt("qtyml");

        imageView=(ImageView) findViewById(R.id.productimage);
        textViewTitle=(TextView) findViewById(R.id.textViewTitle);
        textViewPrice=(TextView) findViewById(R.id.textViewPrice);
        texttyre=(TextView) findViewById(R.id.texttyresize);
        txtqunty=(TextView )findViewById(R.id.textQuantity);



        btn=(Button)findViewById(R.id.booknow);

        imageView = findViewById(R.id.productimage);
        Glide.with(this).load(image)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imageView);

        textViewTitle.setText("Model:"+title);
        texttyre.setText("Size: "+tyressize);
        //textViewPrice.setText((int) productprice);
        String price= Double.toString(productprice);
        textViewPrice.setText("Price:"+price+"/-");
        //txtqunty.setText(price);
        txtqunty.setText("Qnty(ml):"+String.valueOf(productqunty));


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Detailactivity.this,Booknow.class);

                i.putExtra("finaltitle",title);
                startActivity(i);
            }
        });

    }

}
