package com.example.rsspaper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.button4);
        btn1.setText("Thể thao");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TitleActivity.class);
                String link = "https://vnexpress.net/rss/the-thao.rss";
                intent.putExtra("url", link);
                startActivity(intent);
            }
        });

        Button btn2 = findViewById(R.id.button5);
        btn2.setText("Du lịch");
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TitleActivity.class);
                String link = "https://vnexpress.net/rss/du-lich.rss";
                intent.putExtra("url", link);
                startActivity(intent);

            }
        });


        Button btn3 = findViewById(R.id.button6);
        btn3.setText("Kinh tế");
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TitleActivity.class);
                String link = "https://vnexpress.net/rss/kinh-doanh.rss";
                intent.putExtra("url", link);
                startActivity(intent);

            }
        });


        Button btn4 = findViewById(R.id.button7);
        btn4.setText("Giáo dục");
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TitleActivity.class);
                String link = "https://vnexpress.net/rss/giao-duc.rss";
                intent.putExtra("url", link);
                startActivity(intent);

            }
        });


    }

}