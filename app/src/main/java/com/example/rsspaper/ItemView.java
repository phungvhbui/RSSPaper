package com.example.rsspaper;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemView extends AppCompatActivity {
    String link;
    WebView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);

        Intent intent = getIntent();
        link = intent.getStringExtra("link");

        view = findViewById(R.id.webview);
        view.loadUrl(link);
    }
}