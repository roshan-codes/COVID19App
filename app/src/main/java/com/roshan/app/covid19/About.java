package com.roshan.app.covid19;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import java.util.WeakHashMap;

public class About extends AppCompatActivity {
        WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        wv = (WebView) findViewById(R.id.webView);
        wv.loadUrl("file:///android_asset/policy.html");   // fails here

    }
}
