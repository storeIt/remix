package com.example.remix.base.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class FragmentBase extends Fragment {


    public FragmentBase(int fragment) {
        super(fragment);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
        attachListeners();
    }

    /**
     * Implement this method with the views that the fragment uses. It must be called before "setListeners()" method
     */
    protected abstract void initViews();

    /**
     * Implement this to set listeners to the views initialized in the "initViews()"
     */
    protected abstract void attachListeners();
}