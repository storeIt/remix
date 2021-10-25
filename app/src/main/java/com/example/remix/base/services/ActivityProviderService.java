package com.example.remix.base.services;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelStoreOwner;

public class ActivityProviderService {

    private static ActivityProviderService mInstance = null;
    private Activity mActivity;
    private Activity mLastCreatedActivity; // this reference is set in onActivity PreCreated and is showing what mActivity will be in onResume state of the lifecycle

    private ActivityProviderService() {
    }

    public static ActivityProviderService getInstance() {
        if (mInstance == null) {
            synchronized (ActivityProviderService.class) {
                mInstance = new ActivityProviderService();
            }
        }

        return mInstance;
    }

    public Activity getCurrentActivity() {
        return mActivity;
    }

    public FragmentActivity getCurrentFragmentActivity() {
        return (FragmentActivity) mActivity;
    }

    public ViewModelStoreOwner getCurrentViewModelStoreOwner() {
        return (FragmentActivity) mActivity;
    }

    public void registerCurrentActivity(Activity activity) {
        mActivity = activity;
    }

    /**
     * future activity will be set in on start step of the lifecycle and later current activity will point to the same instance when it's ready to be used
     */
    public void registerLastCreatedActivity(Activity activity) {
        mLastCreatedActivity = activity;
    }

    public Activity getLastCreatedActivity() {
        return mLastCreatedActivity;
    }
}
