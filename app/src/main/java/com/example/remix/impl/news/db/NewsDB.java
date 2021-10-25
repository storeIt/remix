package com.example.remix.impl.news.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import static com.example.remix.impl.news.db.DbConstants.DB_NEWS;

@Database(entities = News.class, version = 1)
public abstract class NewsDB extends RoomDatabase {

    private static volatile NewsDB INSTANCE;

    public abstract NewsDao newsDao();

    public static NewsDB getInstance(final Context context) {

        if (INSTANCE == null) {
            synchronized (NewsDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NewsDB.class, DB_NEWS)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
