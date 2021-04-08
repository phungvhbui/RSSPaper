package com.example.rsspaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class ItemView extends AppCompatActivity {
    String desc;
    WebView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);

        Intent intent = getIntent();
        desc = intent.getStringExtra("description");

        view = findViewById(R.id.webview);
        view.loadData(desc, "text/html; charset=UTF-8", null);
    }
}