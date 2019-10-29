package com.example.creditos_webview;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //webView = (WebView) findViewById(R.id.webView);
        //.setWebViewClient(new MyBrowser());
    }

    public void Davi(View v) {
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl("https://www.linkedin.com/in/davi-marques-santos-a13bab94/");
    }

    private class MyBrowser extends WebViewClient {
        public boolean overrideUrlLoading (WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

    }

}
