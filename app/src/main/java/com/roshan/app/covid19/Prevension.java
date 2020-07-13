package com.roshan.app.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Prevension extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevension);
    }

    public void goToPrevension(View view) {
    }

    public void aboutmenu(View view) {
        startActivity(new Intent(getApplicationContext(),About.class));
    }

    public void goToHome(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
