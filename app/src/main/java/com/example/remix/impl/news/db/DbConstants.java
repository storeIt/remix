package com.example.remix.impl.news.db;

public final class DbConstants {

    static final String DB_NEWS = "news_database";
    static final String TABLE_NEWS = "news_table";
    static final String COLUMN_TIMESTAMP = "timestamp";

    static final String QUERY_GET_ALL_SORTED_BY_TIME = "SELECT * FROM " + TABLE_NEWS + " ORDER BY " + COLUMN_TIMESTAMP + " DESC";
    static final String QUERY_DELETE_ALL = "DELETE FROM " + TABLE_NEWS;
}