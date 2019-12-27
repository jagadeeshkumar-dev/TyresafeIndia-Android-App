package com.app.tyresafeindia;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;



public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomnav);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        bottomNavigationView.setSelectedItemId(R.id.nav_home);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.share,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()){
            case R.id.share:
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/app/details?id=com.app.tyresafeindia");
               startActivity(intent.createChooser(intent,"Share this app"));

        }
        return true;
    }

    //bottom nav bar----------------------------------------------------------
    Home HomeFragment = new Home();
    Shop ShopFragment = new Shop();
    Gallery GalleryFragment = new Gallery();


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, HomeFragment).commit();
                return true;

            case R.id.nav_shop:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, ShopFragment).commit();
                return true;

            case R.id.nav_gallery:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, GalleryFragment).commit();
                return true;




        }

        return false;
    }
}
