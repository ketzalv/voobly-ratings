package com.voobly.ratings.ui.base.factoryactivities;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;

import com.voobly.ratings.R;
import com.voobly.ratings.data.DataManagerEvent;
import com.voobly.ratings.ui.base.interfaces.IEventOnFragment;
import com.voobly.ratings.utils.customviews.MaterialTextView;

import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_DISMISS_PROGRESS;
import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_SHOW_PROGRESS;
import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_SHOW_ERROR;
import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_SHOW_MESSAGE;
import static com.voobly.ratings.utils.Utils.showSnackBar;
import static com.voobly.ratings.utils.Utils.showToast;

/**
 * @author Jose Alberto Vazquez
 * @email imketzal@gmail.com
 * @version 1.0
 *
 * This class implements multiple methods to show basic User experience,
 * "ProgressView, Toast/SnackBar for Errors, custom dialogs"
 *
 * Created by jvazquez on 16/05/2017.
 */

public abstract class SupportUXActivity extends SupportFragmentActivity implements IEventOnFragment{
    @IdRes
    protected int idCoordinatorLayout;
    @IdRes
    protected int idProgress;
    @IdRes
    protected int idErrorView;
    @IdRes
    protected int idMainView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idProgress = -1;
        idCoordinatorLayout = -1;
        idErrorView = -1;
    }
    protected View getProgressView(){
        if(idProgress != -1){
            return findViewById(idProgress);
        }
        return null;
    }
    protected void showProgressActivity(String message) {
        View view = findViewById(idProgress);
        if (view != null) {
            hideMainViewActivity();
            hideErrorViewActivity();
            MaterialTextView tv_message = (MaterialTextView) findViewById(R.id.progressText);
            view.setVisibility(View.VISIBLE);
            if (tv_message != null && message != null && !message.isEmpty()) {
                tv_message.setText(message);
            }
        }
    }
    protected boolean isShownProgressActivity() {
        return findViewById(idProgress) != null && findViewById(idProgress).isShown() && findViewById(idProgress).getVisibility() == View.VISIBLE;
    }
    protected void hideProgressActivity() {
        View view = findViewById(idProgress);
        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }
    protected void hideMainViewActivity() {
        View view = findViewById(idMainView);
        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }
    protected void showMainViewActivity() {
        View view = findViewById(idMainView);
        if (view != null) {
            hideProgressActivity();
            hideErrorViewActivity();
            view.setVisibility(View.VISIBLE);
        }
    }
    protected boolean isShowMainViewActivity() {
        View view = findViewById(idMainView);
        return view != null && view.isShown();
    }
    protected void hideErrorViewActivity() {
        View view = findViewById(idErrorView);
        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }
    protected void showErrorViewActivity() {
        View view = findViewById(idErrorView);
        if (view != null) {
            hideProgressActivity();
            hideMainViewActivity();
            view.setVisibility(View.VISIBLE);
        }
    }
    protected boolean isShowErrorViewActivity() {
        View view = findViewById(idErrorView);
        return view != null && view.isShown();
    }
    public void setIdsContainers(@IdRes int id_coordinatorLayout, @IdRes int id_containerFragments, @IdRes int id_progress, @IdRes int idErrorView, @IdRes int idMainView){
        this.idCoordinatorLayout = id_coordinatorLayout;
        this.idContainerFragments = id_containerFragments;
        this.idProgress = id_progress;
        this.idErrorView = idErrorView;
        this.idMainView = idMainView;

    }


    protected CoordinatorLayout getCoordinatorLayout(){
        if(idCoordinatorLayout != -1){
            return (CoordinatorLayout) findViewById(idCoordinatorLayout);
        }
        return null;
    }

    protected void showMessage(String message) {
        View rootCoordinator = findViewById(idCoordinatorLayout);
        if (rootCoordinator != null) {
            showSnackBar(rootCoordinator, message);
        } else {
            showToast(message, this);
        }
    }
    protected void showMessageWithAction(String message, String actionName, int duration, View.OnClickListener listener) {
        View rootCoordinator = findViewById(idCoordinatorLayout);
        if (rootCoordinator != null) {
            showSnackBar(rootCoordinator, message, actionName, duration, listener);
        } else {
            showToast(message, this);
        }
    }
    @Override
    public void onEvent(DataManagerEvent data) {
        switch (data.getEvent()){
            case EVENT_SHOW_PROGRESS:
                if (data.getData() != null && data.getData() instanceof String) {
                    showProgressActivity((String) data.getData());
                }
                break;
            case EVENT_DISMISS_PROGRESS:
                hideProgressActivity();
                showMainViewActivity();
                break;
            case EVENT_SHOW_ERROR:
                if (data.getData() != null && data.getData() instanceof String) {
                    showMessage((String) data.getData());
                }
                break;
            case EVENT_SHOW_MESSAGE:
                if (data.getData() != null && data.getData() instanceof String) {
                    showMessage((String) data.getData());
                }
                break;
            default:
                break;
        }
    }
}
