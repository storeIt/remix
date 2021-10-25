package com.example.remix.impl.news;

import com.example.remix.base.MainApplication;
import com.example.remix.impl.news.db.News;
import com.example.remix.impl.news.db.NewsDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewsRepository {

    private final NewsDao newsDao;

    public NewsRepository() {
        newsDao = MainApplication.getDb().newsDao();
    }

    public void insert(News news) {

        ExecutorService executor = null;
        try {
            executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                newsDao.insert(news);
            });
        } finally {
            if (executor != null) {
                executor.shutdown();
            }
        }
    }

    public void update(News news) {

        ExecutorService executor = null;
        try {
            executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                newsDao.update(news);
            });
        } finally {
            if (executor != null) {
                executor.shutdown();
            }
        }
    }

    public void delete(News news) {

        ExecutorService executor = null;
        try {
            executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                newsDao.delete(news);
            });
        } finally {
            if (executor != null) {
                executor.shutdown();
            }
        }
    }

    public void deleteAllNews() {

        ExecutorService executor = null;
        try {
            executor = Executors.newSingleThreadExecutor();
            executor.execute(newsDao::deleteAllNews);
        } finally {
            if (executor != null) {
                executor.shutdown();
            }
        }
    }

    public List<News> getAllNews() {
        return newsDao.getAllNews();
    }
}