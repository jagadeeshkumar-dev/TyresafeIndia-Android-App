package com.app.tyresafeindia;



import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class bikesactivity extends AppCompatActivity implements bikesadapter.OnItemClickListner {

    private static final String APIURL="https://tyresafetest.000webhostapp.com/bikes.php";

    public static final String Image_URL= "imageurl";
    public static final String Product_Title = "vechilename";
    public static final String Quantity = "qtyml";
    public static final String tyresize  = "tyresize";
    public static final String totalQnty = "totalml";
    public static final String price = "price";
    // public static final double  Product_Price= Double.parseDouble("price");
    RecyclerView recyclerview;
    bikesadapter adapter;
    //ProgressBar progressBar;

    List<bikes>BikesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bikesactivity);

        BikesList = new ArrayList<>();
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
                                    JSONArray bikes=new JSONArray(response);
                                    for (int i=0; i<bikes.length();i++){

                                        JSONObject bikeJSONObject=bikes.getJSONObject(i);

                                        int bikeid= bikeJSONObject.getInt("bikeid");
                                        String vechilename=bikeJSONObject.getString("vechilename");
                                        String tyresize=bikeJSONObject.getString("tyresize");
                                        int qtyml= bikeJSONObject.getInt("qtyml");
                                        int totalml= bikeJSONObject.getInt("totalml");
                                        int price= bikeJSONObject.getInt("price");

                                        //double price= carsJSONObject.getDouble("price");

                                        String imageurl=bikeJSONObject.getString("imageurl");

                                        bikes bike=new bikes(bikeid,vechilename,tyresize,qtyml,totalml,price,imageurl);

                                        BikesList.add(bike);


                                    }

                                    adapter= new bikesadapter(bikesactivity.this,BikesList);
                                    recyclerview.setAdapter(adapter);
                                    adapter.OnItemClickListner(bikesactivity.this);




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

                                Toast.makeText(bikesactivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();

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

        bikes clickedItem= BikesList.get(position);
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
