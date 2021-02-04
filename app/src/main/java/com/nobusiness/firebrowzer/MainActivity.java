package com.nobusiness.firebrowzer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.webkit.WebChromeClient;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.webkit.WebSettings;
import android.view.View.OnKeyListener;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URL;
import java.util.Objects;

import static com.nobusiness.firebrowzer.R.id.Load;
import static com.nobusiness.firebrowzer.R.id.WVB;
import static com.nobusiness.firebrowzer.R.id.urlET;

public class MainActivity extends AppCompatActivity {

    WebView wv;
    WebViewClient wvc;
    Button LD;
    EditText LW;
    WebChromeClient WCC;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button LD = (Button) findViewById(Load);

        wv.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                LD.setText(progress + "%");
            }
        });

        LD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Net();
            }


        });
        final GestureDetector gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
            public void onLongPress(MotionEvent e) {
                sou();
            }
        });
    }


    public void Net(){
        WebView wv= (WebView)findViewById(WVB);
        LW = (EditText)findViewById(urlET);
        wv.setWebViewClient(new WebViewClient());
        if (LW.getText().toString().contains("https://"))
        {
            wv.loadUrl(LW.getText().toString());
        }
        else {
            wv.loadUrl("https://"+LW.getText().toString());
        }
        url = LW.getText().toString();

        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setAppCacheEnabled(true);

        LW.setText(wv.getUrl().toString());

    }

    public void sou(){
        LW = (EditText)findViewById(urlET);
        WebView wv = (WebView)findViewById(WVB);
        wv.setWebViewClient(new WebViewClient());
        if (wv.getUrl().contains("view-source:")){
            wv.loadUrl(LW.getText().toString());
        }
        else {
            wv.loadUrl("view-source:"+wv.getUrl());
        }
    }

    public class MyWebViewClient extends WebViewClient {

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Objects.requireNonNull(getSupportActionBar()).setTitle(view.getTitle());
                getSupportActionBar().setSubtitle(view.getUrl());
                LW.setText(view.getUrl().toString());
                LD.setText("Load");
            }
        }
    }