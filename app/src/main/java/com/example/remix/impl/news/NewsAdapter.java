package com.example.remix.impl.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.remix.R;
import com.example.remix.base.interfaces.IAdapterItemListener;
import com.example.remix.base.widgets.recyclerview.RecyclerViewAdapterBase;
import com.example.remix.impl.news.db.News;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerViewAdapterBase<NewsAdapter.NewsHolder> {

    private List<News> items = new ArrayList<>();

    public NewsAdapter(IAdapterItemListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_row, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {

        News news = items.get(position);
        holder.tvTitle.setText(news.getTitle());
        holder.tvDescription.setText(news.getDescription());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public News getNewsAt(int position) {
        return items.get(position);
    }

    public void setNews(List<News> newsList) {
        items = newsList;
        notifyDataSetChanged();
    }

    public List<News> getNewsList() {
        return items;
    }

    public class NewsHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final TextView tvDescription;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            itemView.setOnClickListener(view -> {
                onItemSelected(items.get(getAdapterPosition()));
            });
        }
    }

}
