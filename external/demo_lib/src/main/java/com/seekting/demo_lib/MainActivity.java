package com.seekting.demo_lib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    public static final boolean DEBUG = BuildConfig.DEBUG;
    public static final String TAG = "MainActivity";
    ListView list;
    private List<Class> data;
    private BaseAdapter baseAdapter;
    public static HashMap<Class, String> DESC_MAP = new HashMap<Class, String>();
    public static HashMap<Class, String> TITLE_MAP = new HashMap<Class, String>();
    public static final List<Class> EXCLUDE_LIST = new ArrayList<Class>();

    public static void putDesc(Class clazz, String desc) {
        DESC_MAP.put(clazz, desc);
    }

    public static void putTitle(Class clazz, String title) {
        TITLE_MAP.put(clazz, title);
    }

    public static void putExclude(Class<? extends Activity> cl) {
        EXCLUDE_LIST.add(cl);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (DEBUG) {
            Log.d(TAG, "onCreate.");
        }
        list = new ListView(this);
        EXCLUDE_LIST.add(MainActivity.class);
        String pkgName = this.getPackageName();
        if (DEBUG) {
            Log.d(TAG, "onCreate." + pkgName);
        }
        data = ClassUtils.getActivitiesClass(this, this.getPackageName(), EXCLUDE_LIST);

        for (Class aClass : data) {
            Annotation[] a = aClass.getDeclaredAnnotations();
            for (Annotation annotation : a) {
                if (Demo.class.isAssignableFrom(annotation.getClass())) {
                    Demo d = Demo.class.cast(annotation);
                    if(!TextUtils.isEmpty(d.desc())) {
                        DESC_MAP.put(aClass, d.desc());
                    }
                    if(!TextUtils.isEmpty(d.title())) {
                        TITLE_MAP.put(aClass, d.title());
                    }

                }
            }
        }
        final LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        baseAdapter = new BaseAdapter() {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = layoutInflater.inflate(R.layout.demo_layout, null);
                    ViewHolder viewHolder = new ViewHolder();
                    viewHolder.title = (TextView) convertView.findViewById(R.id.title);
                    viewHolder.description = (TextView) convertView.findViewById(R.id.description);
                    viewHolder.className = (TextView) convertView.findViewById(R.id.class_name);
                    viewHolder.thumb = (ImageView) convertView.findViewById(R.id.thumb);
                    convertView.setTag(viewHolder);
                }
                setView((ViewHolder) convertView.getTag(), position);
                return convertView;
            }

            private void setView(ViewHolder viewHolder, int position) {
                Class class1 = getItem(position);
                viewHolder.className.setText(class1.getSimpleName());
                viewHolder.title.setText(TITLE_MAP.get(class1));
                viewHolder.description.setText(DESC_MAP.get(class1));

            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public Class getItem(int position) {
                return data.get(position);
            }

            @Override
            public int getCount() {
                return data.size();
            }
        };
        list.setAdapter(baseAdapter);
        setContentView(list);
        list.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private static class ViewHolder {
        TextView title;
        TextView description;
        TextView className;
        ImageView thumb;
        long requestCode;
        String key;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Class clazz = (Class) baseAdapter.getItem(position);
        startActivity(new Intent(this, clazz));

    }


}