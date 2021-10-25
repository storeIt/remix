package com.example.remix.impl.news;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.remix.R;
import com.example.remix.base.interfaces.IAdapterItemListener;
import com.example.remix.base.ui.FragmentBase;
import com.example.remix.impl.news.db.News;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class NewsListFragment extends FragmentBase implements IAdapterItemListener, PopupMenu.OnMenuItemClickListener {

    private FloatingActionButton btnAdd;
    private ImageView ivMore;
    private RecyclerView recycler;

    private NewsAdapter adapter;
    private NewsViewModel viewModel;

    public NewsListFragment() {
        super(R.layout.fragment_news_list);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(NewsViewModel.class);
        viewModel.init();
    }

    @Override
    protected void initViews() {

        btnAdd = requireView().findViewById(R.id.btn_add);
        ivMore = requireView().findViewById(R.id.iv_more);
        recycler = requireView().findViewById(R.id.rv_news);
        adapter = new NewsAdapter(this);
        recycler.setAdapter(adapter);
    }

    @Override
    protected void attachListeners() {

        btnAdd.setOnClickListener(v -> {
            viewModel.setSelectedNews(null);
            Navigation.findNavController(v).navigate(R.id.action_main_fragment_to_news_details_fragment);
        });

        ivMore.setOnClickListener(view -> {

            PopupMenu popup = new PopupMenu(requireContext(), view);
            popup.setOnMenuItemClickListener(this);
            popup.inflate(R.menu.news_menu);
            popup.show();
        });

        viewModel.getAllNews().observe(getViewLifecycleOwner(), news -> {
            if (news != null) {
                adapter.setNews(news);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                News news = adapter.getNewsAt(viewHolder.getAdapterPosition());
                viewModel.deleteNews(news);
                List<News> currentList = adapter.getNewsList();
                currentList.remove(viewHolder.getAdapterPosition());
                adapter.setNews(currentList);
                Toast.makeText(requireContext(), getResources().getString(R.string.news_deleted), Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recycler);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (adapter != null) {
            (adapter).registerListener(this);
        }
    }

    private void openDetails() {
        Navigation.findNavController(requireView()).navigate(R.id.action_main_fragment_to_news_details_fragment);
    }

    private void showSnackbar() {

        Snackbar snackbar = Snackbar.make(requireView(), getResources().getString(R.string.delete_permanently), Snackbar.LENGTH_LONG);
        snackbar.addCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                if (event == Snackbar.Callback.DISMISS_EVENT_TIMEOUT) {
                    viewModel.deleteAllNews();
                    adapter.setNews(Collections.emptyList());
                }
            }
        });
        snackbar.setAction(getResources().getString(R.string.undo), v -> {
            snackbar.dismiss();
        });
        snackbar.setTextColor(getResources().getColor(R.color.fail, null));
        snackbar.show();
    }


    @Override
    public void onPause() {

        if (adapter != null) {
            (adapter).unRegisterListener(this);
        }
        super.onPause();
    }

    @Override
    public void onItemClickedCustomListener(int itemPosition) {

    }

    @Override
    public void onItemClickedCustomListener(Object obj) {

        viewModel.setSelectedNews((News) obj);
        openDetails();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        if (item.getItemId() == R.id.delete_notes) {
            showSnackbar();
        }
        return false;
    }
}