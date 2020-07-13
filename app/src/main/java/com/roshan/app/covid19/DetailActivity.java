package com.roshan.app.covid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jpardogo.android.googleprogressbar.library.GoogleProgressBar;

public class DetailActivity extends AppCompatActivity {
    TextView cases,recovered,active,deaths,critical,todayCases,todayDeaths,affected,country;
    private int positioncountry;
    NestedScrollView scrollView;
    ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        Intent intent = getIntent();
        positioncountry = intent.getIntExtra("position",0);

        mProgressBar = (GoogleProgressBar) findViewById(R.id.google_progress);
        scrollView = (NestedScrollView)findViewById(R.id.nested_content);
       country = findViewById(R.id.countryName);
        cases = findViewById(R.id.cases);
        todayCases = findViewById(R.id.todayCases);
        recovered = findViewById(R.id.recovered);
        active  = findViewById(R.id.activecases);
        todayDeaths = findViewById(R.id.todayDeaths);
        deaths = findViewById(R.id.deaths);
        critical  = findViewById(R.id.critical);


        country.setText(AffectedCountries.countryModelList.get(positioncountry).getCountry());
        cases.setText(AffectedCountries.countryModelList.get(positioncountry).getCases());
        todayCases.setText(AffectedCountries.countryModelList.get(positioncountry).getTodayCases());
        recovered.setText(AffectedCountries.countryModelList.get(positioncountry).getRecovered());
        active.setText(AffectedCountries.countryModelList.get(positioncountry).getActive());
        critical.setText(AffectedCountries.countryModelList.get(positioncountry).getCritical());
        deaths.setText(AffectedCountries.countryModelList.get(positioncountry).getDeaths());
        todayDeaths.setText(AffectedCountries.countryModelList.get(positioncountry).getTodaydeaths());
        mProgressBar.setTop(0);
        mProgressBar.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void goTrackCountries(View view) {
        startActivity(new Intent(getApplicationContext(),AffectedCountries.class));
    }
    public void goToPrevension(View view) {
        startActivity(new Intent(getApplicationContext(),Prevension.class));
    }
}
