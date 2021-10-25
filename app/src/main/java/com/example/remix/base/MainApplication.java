package com.example.remix.base;

import android.app.Application;

import com.example.remix.impl.news.db.NewsDB;

public class MainApplication extends Application {

    private static NewsDB db;

    @Override
    public void onCreate() {
        super.onCreate();

        initDb();
    }

    private void initDb() {
       db = NewsDB.getInstance(this);
    }

    public static NewsDB getDb() {
        return db;
    }
}
