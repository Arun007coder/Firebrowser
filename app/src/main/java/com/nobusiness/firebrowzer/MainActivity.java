package com.nobusiness.firebrowzer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import static com.nobusiness.firebrowzer.R.id.WVB;

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

        WebView wv= (WebView)findViewById(WVB);
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("www.google.com");
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setAppCacheEnabled(true);

    }
}