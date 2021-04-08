package com.example.rsspaper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
        Button btn = (Button) convertView.findViewById(R.id.button);
        btn.setText(objects.get(position).getTitle());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemView.class);
                intent.putExtra("description", objects.get(position).getDescription());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
