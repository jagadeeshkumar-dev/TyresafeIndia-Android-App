package com.app.tyresafeindia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Booknow extends AppCompatActivity {

    TextView title,datetime;
    EditText Name,contact;
    Button submit;

    private static String URL_SUBMIT="https://tyresafetest.000webhostapp.com/order.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booknow);

        title=(TextView)findViewById(R.id.finaltitle);
        datetime=(TextView)findViewById(R.id.datetime);
        Name=(EditText)findViewById(R.id.Name);
        contact=(EditText)findViewById(R.id.contact);
        submit=(Button)findViewById(R.id.Submit);

        Intent intent = getIntent();
        String result = intent.getStringExtra("finaltitle");
        title.setText("Title::"+result);

        DateFormat df = new SimpleDateFormat("d MMM yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());
          datetime.setText((CharSequence) date);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendorder();
            }
        });


    }

    private void sendorder(){
        //submit.setVisibility(View.GONE);


        final String vechilename=this.title.getText().toString().trim();
        final String Name=this.Name.getText().toString().trim();
        final String phno=this.contact.getText().toString().trim();
        final String datetime=this.datetime.getText().toString();

        //DateFormat df = new SimpleDateFormat("d MMM yyyy, HH:mm");
        //final String date = df.format(Calendar.getInstance().getTime());


        if (TextUtils.isEmpty(Name)) {
            ((TextView) findViewById(R.id.Name)).requestFocus();
            ((TextView) findViewById(R.id.Name)).setError("Enter your Name");
            return;
        }
        if (TextUtils.isEmpty(phno)) {
            ((TextView) findViewById(R.id.contact)).requestFocus();
            ((TextView) findViewById(R.id.contact)).setError("Enter Contact");
            return;
        }
        if (phno.length()<10){
            ((TextView) findViewById(R.id.contact)).requestFocus();
            ((TextView) findViewById(R.id.contact)).setError("Enter Valid no");
            return;
        }
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading..Please Wait.");
        pDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_SUBMIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String success=jsonObject.getString("success");

                            if (success.equals("1")){

                                Toast.makeText(Booknow.this, "Order Recevied", Toast.LENGTH_LONG).show();
                                pDialog.hide();
                                submit.setVisibility(View.VISIBLE);


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            pDialog.hide();
                            Toast.makeText(Booknow.this, "Error occured"+e.toString(), Toast.LENGTH_SHORT).show();

                            //submit.setVisibility(View.VISIBLE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                pDialog.hide();
                Toast.makeText(Booknow.this, "Error occured"+error.toString(), Toast.LENGTH_SHORT).show();

                //submit.setVisibility(View.VISIBLE);

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
               Map<String,String> parms=new HashMap<>();
               parms.put("vechilename",vechilename);
                parms.put("name",Name);
                parms.put("phno", String.valueOf(phno));
                parms.put("datetime",datetime);

                return parms;


            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }
}
