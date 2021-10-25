package com.example.remix.impl.news;

import com.example.remix.base.viewmodels.BaseViewModel;
import com.example.remix.impl.news.db.News;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class NewsViewModel extends BaseViewModel {

    private MutableLiveData<List<News>> newsList;
    private News currentNews;

    private NewsRepository repository;

    @Override
    protected void init() {

        repository = new NewsRepository();
        newsList = new MutableLiveData<>();
    }

    public void saveNews(@NonNull String title, @NonNull String description) {

        if (currentNews == null) {
            repository.insert(new News(title, description));
        } else {

            News news = new News(title, description);
            if (currentNews != null) {
                news.setId(currentNews.getId());
                repository.update(news);
            }
            repository.insert(news);
        }
    }

    public LiveData<List<News>> getAllNews() {

        ExecutorService executor = null;
        try {
            executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                List<News> result = repository.getAllNews();
                if (result != null) {
                    newsList.postValue(result);
                }
            });
        } finally {
            if (executor != null) {
                executor.shutdown();
            }
        }

        return newsList;
    }

    public void deleteNews(@NonNull News news) {
        repository.delete(news);
    }

    public void deleteAllNews() {
        repository.deleteAllNews();
    }

    public void setSelectedNews(News selectedNews) {
        currentNews = selectedNews;
    }

    public News getSelectedNews() {
        return currentNews;
    }
}