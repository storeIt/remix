package com.example.remix.impl.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.remix.R;
import com.example.remix.base.widgets.recyclerview.RecyclerViewAdapterBase;
import com.example.remix.impl.news.db.News;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsRecyclerAdapter extends RecyclerViewAdapterBase<NewsRecyclerAdapter.ViewHolder> {

    private final List<News> items;
    private final LayoutInflater inflater;

    public NewsRecyclerAdapter(Context context, List<News> items) {

        inflater = LayoutInflater.from(context);
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_news_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        News news = items.get(position);
        holder.tvTitle.setText(news.getTitle());
        holder.tvDescription.setText(news.getDescription());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitle;
        TextView tvDescription;

        ViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvTitle.setOnClickListener(this);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvDescription.setOnClickListener(this);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }


}
