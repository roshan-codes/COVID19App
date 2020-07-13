package com.roshan.app.covid19;
import com.google.android.material.appbar.AppBarLayout;
import com.jpardogo.android.googleprogressbar.library.GoogleProgressBar;
import com.roshan.app.covid19.R;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView cases,recovered,active,deaths,critical,todayCases,todayDeaths,affected;
    SimpleArcLoader simpleArcLoader;
    NestedScrollView scrollView;
    PieChart pieChart;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;

    ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (GoogleProgressBar) findViewById(R.id.google_progress);
        scrollView = (NestedScrollView)findViewById(R.id.nested_content);


        cases = findViewById(R.id.cases);
        todayCases = findViewById(R.id.todayCases);
        recovered = findViewById(R.id.recovered);
         active  = findViewById(R.id.activecases);
        todayDeaths = findViewById(R.id.todayDeaths);
        deaths = findViewById(R.id.deaths);
         critical  = findViewById(R.id.critical);
         affected = findViewById(R.id.affected);

        fetchData();
    }

    private void fetchData() {

        String url = "https://corona.lmao.ninja/v2/all";
        //simpleArcLoader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());


                            cases.setText(jsonObject.getString("cases"));
                            recovered.setText(jsonObject.getString("recovered"));

                            deaths.setText(jsonObject.getString("deaths"));
                            active.setText(jsonObject.getString("active"));
                            todayDeaths.setText(jsonObject.getString("todayDeaths"));

                            todayCases.setText(jsonObject.getString("todayCases"));
                            critical.setText(jsonObject.getString("critical"));
                            affected.setText(jsonObject.getString("affectedCountries"));

                            scrollView.setVisibility(View.VISIBLE);
                                     mProgressBar.setTop(0);
                           mProgressBar.setVisibility(View.GONE);


                        } catch (JSONException e) {
                            mProgressBar.setTop(0);
                            mProgressBar.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText((MainActivity.this), error.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }



    public void goTrackCountries(View view) {
        startActivity(new Intent(getApplicationContext(),AffectedCountries.class));

    }

    public void goToPrevension(View view) {
        startActivity(new Intent(getApplicationContext(),Prevension.class));
    }
}
