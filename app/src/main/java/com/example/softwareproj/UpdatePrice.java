package com.example.softwareproj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class UpdatePrice extends AppCompatActivity {

    /// regular price updates added
    private WebView priceUpdates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.priceupdates);

        /// regular price updates intialization
        priceUpdates = (WebView) findViewById(R.id.priceupdates);
        priceUpdates.setWebViewClient(new WebViewClient());
        priceUpdates.loadUrl("http://www.dam.gov.bd/market_daily_price_report");

        WebSettings webSettings = priceUpdates.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }



    @Override
    public void onBackPressed() {
        if(priceUpdates.canGoBack()){
            priceUpdates.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
}
