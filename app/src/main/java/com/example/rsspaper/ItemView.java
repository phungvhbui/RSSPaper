package com.example.rsspaper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemView extends AppCompatActivity {
    String link;
    WebView view;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);

        pref = getApplicationContext().getSharedPreferences(Database.PREFS_NAME, MODE_PRIVATE);
        editor = pref.edit();

        Intent intent = getIntent();
        link = intent.getStringExtra("link");

        getSupportActionBar().setTitle(intent.getStringExtra("title"));

        view = findViewById(R.id.webview);
        view.loadUrl(link);
    }

    @Override
    public void onBackPressed() {
        editor.putInt("Application.screen", 2);
        editor.putString("RSSObject.title", "");
        editor.putString("RSSObject.link", "");
        editor.apply();
        super.onBackPressed();
    }

}