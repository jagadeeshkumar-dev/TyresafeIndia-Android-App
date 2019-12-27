package com.app.tyresafeindia;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class carsactivity extends AppCompatActivity implements carsadapter.OnItemClickListner {

    private static final String APIURL="https://tyresafetest.000webhostapp.com/cars.php";

    public static final String Image_URL= "imageurl";
    public static final String Product_Title = "vechilename";
    public static final String Quantity = "qtyml";
    public static final String tyresize  = "tyresize";
    public static final String totalQnty = "totalml";
    public static final String price = "price";
    // public static final double  Product_Price= Double.parseDouble("price");
    RecyclerView recyclerview;
    carsadapter adapter;
    //ProgressBar progressBar;

    List<cars>CarsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_carsactivity);

        CarsList = new ArrayList<>();
        //swipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        recyclerview=(RecyclerView)findViewById(R.id.recyclerview);

        //progressBar=(ProgressBar)findViewById(R.id.proggressbar);

        recyclerview=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));


        loadproducts();






    }

    private void loadproducts(){

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading..Please Wait.");
        pDialog.show();


        StringRequest stringRequest=new StringRequest(Request.Method.GET,APIURL,
                new
                        Response.Listener<String>() {


                            @Override
                            public void onResponse(String response) {

                                //progressBar.setVisibility(View.VISIBLE);


                                try {
                                    JSONArray cars=new JSONArray(response);
                                    for (int i=0; i<cars.length();i++){

                                        JSONObject carsJSONObject=cars.getJSONObject(i);

                                        int carid= carsJSONObject.getInt("carid");
                                        String vechilename=carsJSONObject.getString("vechilename");
                                        String tyresize=carsJSONObject.getString("tyresize");
                                        int qtyml= carsJSONObject.getInt("qtyml");
                                        int totalml= carsJSONObject.getInt("totalml");
                                        int price= carsJSONObject.getInt("price");

                                        //double price= carsJSONObject.getDouble("price");

                                        String imageurl=carsJSONObject.getString("imageurl");

                                        cars car=new cars(carid,vechilename,tyresize,qtyml,totalml,price,imageurl);

                                        CarsList.add(car);


                                    }

                                    adapter= new carsadapter(carsactivity.this,CarsList);
                                    recyclerview.setAdapter(adapter);
                                    adapter.OnItemClickListner(carsactivity.this);




                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                pDialog.hide();

                            }

                        },
                new
                        Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Toast.makeText(carsactivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();

                                pDialog.hide();
                            }
                        });

        Volley.newRequestQueue(this).add(stringRequest);
        // progressBar.setVisibility(View.GONE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();

        inflater.inflate(R.menu.example_menu,menu);

        MenuItem searchitem=menu.findItem(R.id.action_search);

        SearchView searchView=(SearchView)searchitem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newtext) {
                adapter.getFilter().filter(newtext);
                return false;
            }
        });
        return true;
    }

    @Override
    public void OnItemClick(int position) {
        Intent detailintent=new Intent(this,Detailactivity.class);

        cars clickedItem= CarsList.get(position);
        detailintent.putExtra(Image_URL,clickedItem.getImageurl());
        detailintent.putExtra(Product_Title,clickedItem.getVechilename());
        detailintent.putExtra(tyresize,clickedItem.getTyresize());
        detailintent.putExtra(Quantity,clickedItem.getQtyml());
        detailintent.putExtra(totalQnty,clickedItem.getTotalml());
        //detailintent.putExtra(price,clickedItem.getPrice());

        Bundle bundle=new Bundle();
        bundle.putInt("price",clickedItem.getPrice());
        bundle.putInt("Quantity",clickedItem.getQtyml());
        bundle.putInt("totalQnty",clickedItem.getTotalml());
        detailintent.putExtras(bundle);

        //detailintent.putExtra(Product_Price,clickedItem.getPrice());

        startActivity(detailintent);

    }
}
