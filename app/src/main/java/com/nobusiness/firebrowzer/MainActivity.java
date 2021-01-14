package com.nobusiness.firebrowzer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView wv;
    WebViewClient wvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Net();
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void Net(){

        wv.findViewById(R.id.WVB);
        wv.loadUrl("www.google.com");
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setAppCacheEnabled(true);
    }
}