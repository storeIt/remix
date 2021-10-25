package com.example.remix.impl.news.db;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import static com.example.remix.impl.news.db.DbConstants.QUERY_DELETE_ALL;
import static com.example.remix.impl.news.db.DbConstants.QUERY_GET_ALL_SORTED_BY_TIME;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(News news);

    @Update
    void update(News news);

    @Delete
    void delete(News news);

    @Query(QUERY_DELETE_ALL)
    void deleteAllNews();

    @Query(QUERY_GET_ALL_SORTED_BY_TIME)
    List<News> getAllNews();
}
