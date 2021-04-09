package com.example.rsspaper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Xml;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TitleActivity extends AppCompatActivity {
    List<RSSObject> list;
    String url = "";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        listView = findViewById(R.id.list_layout);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        pref = getApplicationContext().getSharedPreferences(Database.PREFS_NAME, MODE_PRIVATE);
        editor = pref.edit();

        int screenMode = pref.getInt("Application.screen", -1);
        if (screenMode == 3) {
            Intent intentPass = new Intent(TitleActivity.this, ItemView.class);
            intentPass.putExtra("title", pref.getString("RSSObject.title", null));
            intentPass.putExtra("link", pref.getString("RSSObject.link", null));
            startActivity(intentPass);
        }

        getSupportActionBar().setTitle(intent.getStringExtra("category"));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    if (!url.startsWith("http://") && !url.startsWith("https://"))
                        url = "http://" + url;

                    URL urlink = new URL(url);
                    InputStream inputStream = urlink.openConnection().getInputStream();
                    list = parseFeed(inputStream);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException | XmlPullParserException e) {
                    e.printStackTrace();
                }
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        final ItemAdapter adapter = new ItemAdapter(getApplicationContext(), list);
                        listView.setAdapter(adapter);
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        editor.putInt("Application.screen", 1);
        editor.putString("CategoryObject.category", "");
        editor.putString("CategoryObject.rssLink", "");
        editor.putString("RSSObject.title", "");
        editor.putString("RSSObject.link", "");
        editor.apply();
        super.onBackPressed();
    }

    public List<RSSObject> parseFeed(InputStream inputStream) throws XmlPullParserException,
            IOException {
        String title = null;
        String link = null;
        String description = null;
        boolean isItem = false;
        List<RSSObject> items = new ArrayList<>();
        boolean first = true;

        try {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlPullParser.setInput(inputStream, null);

            xmlPullParser.nextTag();
            while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT) {
                int eventType = xmlPullParser.getEventType();

                String name = xmlPullParser.getName();
                if (name == null)
                    continue;

                if (eventType == XmlPullParser.END_TAG) {
                    if (name.equalsIgnoreCase("item")) {
                        isItem = false;
                    }
                    continue;
                }

                if (eventType == XmlPullParser.START_TAG) {
                    if (name.equalsIgnoreCase("item")) {
                        isItem = true;
                        continue;
                    }
                }

                Log.d("MyXmlParser", "Parsing name ==> " + name);
                String result = "";
                if (xmlPullParser.next() == XmlPullParser.TEXT) {
                    result = xmlPullParser.getText();
                    xmlPullParser.nextTag();
                }

                if (name.equalsIgnoreCase("title")) {
                    title = result;
                } else if (name.equalsIgnoreCase("link")) {
                    if (!first) {
                        link = result;
                    } else {
                        first = false;
                    }
                } else if (name.equalsIgnoreCase("description")) {
                    description = result;
                }

                if (title != null && link != null && description != null) {
                    if (isItem) {
                        RSSObject item = new RSSObject(title, link, description);
                        items.add(item);
                    }

                    title = null;
                    link = null;
                    description = null;
                    isItem = false;
                }
            }

            return items;
        } finally {
            inputStream.close();
        }
    }
}