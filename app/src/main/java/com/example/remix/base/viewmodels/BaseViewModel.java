package com.example.remix.base.viewmodels;

import android.app.Activity;

import com.example.remix.R;

import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel extends ViewModel {

    /**
     * runs the passed runnable on the UI thread
     *
     * @param runnable
     */
    protected void runOnUI(Activity currentActivity, Runnable runnable) {

        if (currentActivity == null) {
            return;
        }
        currentActivity.runOnUiThread(runnable);
    }

    /**
     * handles the exceptions and shows error
     */
    protected void handleException(Throwable e) {

        if (e.getMessage() != null) {
            showError(e);
        }
    }

    /**
     * Shows the given error on the ui thread
     *
     * @param e
     */
    protected void showError(Throwable e) {

//        Activity currentActivity = ActivityProviderService.getInstance().getCurrentActivity();
//        if (currentActivity == null) {
//            return;
//        }
//
//        currentActivity.runOnUiThread(() -> {
//
//                    String message = (e.getMessage() == null || e.getMessage().equalsIgnoreCase(Constants.ERROR_MESSAGE_TIMEOUT)) ?
//                            ResourceProvider.getString(R.string.msg_not_available_cbs) : e.getMessage();
//                    new DialogAlert(currentActivity, R.string.empty_string, message).show();
//                }
//        );
    }

    protected abstract void init();

}
