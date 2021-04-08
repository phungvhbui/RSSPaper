package com.example.rsspaper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<CategoryObject> btnData = Arrays.asList(
            new CategoryObject("Thế giới", "https://vnexpress.net/rss/the-gioi.rss"),
            new CategoryObject("Thời sự", "https://vnexpress.net/rss/thoi-su.rss"),
            new CategoryObject("Kinh doanh", "https://vnexpress.net/rss/kinh-doanh.rss"),
            new CategoryObject("Startup", "https://vnexpress.net/rss/startup.rss"),
            new CategoryObject("Giải trí", "https://vnexpress.net/rss/giai-tri.rss"),
            new CategoryObject("Thể thao", "https://vnexpress.net/rss/the-thao.rss"),
            new CategoryObject("Pháp luật", "https://vnexpress.net/rss/phap-luat.rss"),
            new CategoryObject("Giáo dục", "https://vnexpress.net/rss/giao-duc.rss"),
            new CategoryObject("Tin mới nhất", "https://vnexpress.net/rss/tin-moi-nhat.rss"),
            new CategoryObject("Tin nổi bật", "https://vnexpress.net/rss/tin-noi-bat.rss"),
            new CategoryObject("Sức khỏe", "https://vnexpress.net/rss/suc-khoe.rss"),
            new CategoryObject("Đời sống", "https://vnexpress.net/rss/doi-song.rss"),
            new CategoryObject("Du lịch", "https://vnexpress.net/rss/du-lich.rss"),
            new CategoryObject("Khoa học", "https://vnexpress.net/rss/khoa-hoc.rss"),
            new CategoryObject("Số hóa", "https://vnexpress.net/rss/so-hoa.rss"),
            new CategoryObject("Xe", "https://vnexpress.net/rss/xe.rss"),
            new CategoryObject("Ý kiến", "https://vnexpress.net/rss/y-kien.rss"),
            new CategoryObject("Tâm sự", "https://vnexpress.net/rss/tam-su.rss"),
            new CategoryObject("Cười", "https://vnexpress.net/rss/cuoi.rss"),
            new CategoryObject("Tin xem nhiều", "https://vnexpress.net/rss/tin-xem-nhieu.rss"));

    LinearLayout layout;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getApplicationContext().getSharedPreferences(Database.PREFS_NAME, MODE_PRIVATE);
        editor = pref.edit();

        int screenMode = pref.getInt("Application.screen", -1);
        if (screenMode == -1) {
            editor.putInt("Application.screen", 1);
            editor.putString("CategoryObject.category", "");
            editor.putString("CategoryObject.rssLink", "");
            editor.putString("RSSObject.title", "");
            editor.putString("RSSObject.link", "");
            editor.apply();
        } else {
            if (screenMode == 2) {
                Intent intent = new Intent(MainActivity.this, TitleActivity.class);
                intent.putExtra("url", pref.getString("CategoryObject.rssLink", null));
                intent.putExtra("category", pref.getString("CategoryObject.category", null));
                startActivity(intent);
            }
            else if(screenMode == 3){
                Intent intent = new Intent(MainActivity.this, TitleActivity.class);
                intent.putExtra("url", pref.getString("CategoryObject.rssLink", null));
                intent.putExtra("category", pref.getString("CategoryObject.category", null));
                intent.putExtra("title", pref.getString("RSSObject.title", null));
                intent.putExtra("link", pref.getString("RSSObject.link", null));
                startActivity(intent);
            }
        }

        getSupportActionBar().setTitle("VnExpress's categories");

        layout = findViewById(R.id.main_layout);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(5, 5, 5, 5);

        for (CategoryObject cat : btnData) {
            Button btn = new Button(this);
            btn.setLayoutParams(params);
            btn.setText(cat.getCategory());
            btn.setOnClickListener(v -> {
                editor.putInt("Application.screen", 2);
                editor.putString("CategoryObject.category", cat.getCategory());
                editor.putString("CategoryObject.rssLink", cat.getRssLink());
                editor.apply();

                Intent intent = new Intent(MainActivity.this, TitleActivity.class);
                intent.putExtra("url", cat.getRssLink());
                intent.putExtra("category", cat.getCategory());
                startActivity(intent);
            });

            layout.addView(btn);
        }
    }

}