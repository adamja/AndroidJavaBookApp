package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebsiteActivity extends AppCompatActivity {

    public static final String URL_KEY = "url";

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);

        Intent intent = getIntent();
        if (null != intent) {
            String url = intent.getStringExtra(URL_KEY);

            webView = findViewById(R.id.webView);
            webView.loadUrl(url);
            webView.setWebViewClient(new WebViewClient());  // create web client within app
            webView.getSettings().setJavaScriptEnabled(true);  // enable javascript
        }
    }

    @Override
    public void onBackPressed() {
        // When pack is pressed, go back on website first before exiting the webview screen
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}