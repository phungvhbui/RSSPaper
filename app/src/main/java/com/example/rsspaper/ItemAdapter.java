package com.example.rsspaper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;

public class ItemAdapter extends BaseAdapter {
    Context context;
    List<RSSObject> objects;
    LayoutInflater inflater;

    public ItemAdapter(Context context, List<RSSObject> objects) {
        this.context = context;
        this.objects = objects;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.list_item, null);
        Button btn = convertView.findViewById(R.id.button);
        btn.setText(objects.get(position).getTitle());

        btn.setOnClickListener(v -> {
            Intent intent = new Intent(context, ItemView.class);
            intent.putExtra("link", objects.get(position).getLink());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
        return convertView;
    }
}
