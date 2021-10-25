package com.example.remix.base.widgets.recyclerview;


import com.example.remix.base.interfaces.IAdapterItemListener;

import androidx.recyclerview.widget.RecyclerView;

public abstract class RecyclerViewAdapterBase<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    protected IAdapterItemListener listener;

    public void registerListener(IAdapterItemListener listener) {

        if (listener != null) {
            this.listener = listener;
        }
    }

    public void unRegisterListener(IAdapterItemListener listener) {

        if (listener == this.listener) {
            this.listener = null;
        }
    }

    protected void onItemSelected(int itemPosition) {

        if (listener != null) {
            listener.onItemClickedCustomListener(itemPosition);
        }
    }

    protected void onItemSelected(Object item) {
        listener.onItemClickedCustomListener(item);
    }
}
