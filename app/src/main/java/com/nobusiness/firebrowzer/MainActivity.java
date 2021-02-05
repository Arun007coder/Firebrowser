package com.nobusiness.firebrowzer;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.webkit.WebChromeClient;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import static com.nobusiness.firebrowzer.R.id.Back;
import static com.nobusiness.firebrowzer.R.id.Forward;
import static com.nobusiness.firebrowzer.R.id.Home;
import static com.nobusiness.firebrowzer.R.id.Load;
import static com.nobusiness.firebrowzer.R.id.WVB;
import static com.nobusiness.firebrowzer.R.id.urlET;

public class MainActivity extends AppCompatActivity {

    WebView wv;
    WebViewClient wvc;
    Button LD;
    Button HM;
    Button UP;
    Button DN;
    EditText LW;
    WebChromeClient WCC;
    String url;
    Boolean isSourcecodeLoaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LW = (EditText)findViewById(urlET);
        Button LD = (Button) findViewById(Load);
        wv = (WebView)findViewById(WVB);
        Button HM = (Button)findViewById(Home);
        Button UP = (Button)findViewById(Forward);
        Button DN = (Button)findViewById(Back);
        wv.loadUrl("https://www.google.com");
        LW.setText(wv.getUrl().toString());

        wv.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                LD.setText(progress + "%");
            }
        });

        if (isSourcecodeLoaded = false) {
            LD.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Net();
                }


            });
        }

        LD.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                sou();
                isSourcecodeLoaded = true;
                return false;
            }
        });

        HM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home();
            }
        });

        UP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                up();
            }
        });

        DN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                down();
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

    public void Home(){
        wv = (WebView)findViewById(WVB);
        LW = (EditText)findViewById(urlET);
        wv.loadUrl("https://www.google.com");
        LW.setText("https://www.google.com");

        isSourcecodeLoaded = false;
    }

    public void up(){
        wv = (WebView)findViewById(WVB);
        LW = (EditText)findViewById(urlET);
        if (wv.canGoForward() == true){
            wv.goForward();
            LW.setText(wv.getUrl().toString());
            isSourcecodeLoaded = false;
        }
    }

    public void down(){
        wv = (WebView)findViewById(WVB);
        LW = (EditText)findViewById(urlET);
        if (wv.canGoBack() == true){
            wv.goBack();
            LW.setText(wv.getUrl().toString());
            isSourcecodeLoaded = false;
        }
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