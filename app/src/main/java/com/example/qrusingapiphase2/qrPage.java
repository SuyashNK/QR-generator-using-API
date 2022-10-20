package com.example.qrusingapiphase2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class qrPage extends AppCompatActivity {

    WebView mWebview;
    Button SaveBtn, NextBtn;
    String word = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_page);

        mWebview = findViewById(R.id.qrWebView);
        SaveBtn = findViewById(R.id.qrSaveBtn);
        NextBtn = findViewById(R.id.qrNextBtn);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            word = bundle.getString("word");
        }

        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(qrPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void generateQR (String word) {
        String URL = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + word;


        mWebview.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(qrPage.this, "Thanks for generating", Toast.LENGTH_SHORT).show();
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });

        mWebview.loadUrl(URL);
    }
}