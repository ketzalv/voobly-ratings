package com.voobly.ratings.ui.base.factoryfragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;
import com.voobly.ratings.utils.customviews.MaterialTextView;
import com.voobly.ratings.R;
import com.voobly.ratings.data.DataManagerEvent;
import com.voobly.ratings.ui.base.interfaces.IEventOnFragment;
import com.voobly.ratings.ui.base.interfaces.IProcessData;

import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_DISMISS_PROGRESS;
import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_SHOW_MESSAGE;
import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_SHOW_PROGRESS;

/**
 * Created by jvazquez on 16/05/2017.
 */

public abstract class SupportUXFragment<iProcessData extends IProcessData> extends SupportPresenterFragment<iProcessData> {
    protected IEventOnFragment eventOnFragment;

    @IdRes
    protected int idMainView, idProgressView, idErrorView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof  IEventOnFragment){
            this.eventOnFragment = (IEventOnFragment) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idMainView      = -1;
        idErrorView     = -1;
        idProgressView  = -1;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showMainView();
    }

    protected void setIdContainers(@IdRes int idMainView, @IdRes int idProgressView, @IdRes int idErrorView){
        this.idMainView = idMainView;
        this.idProgressView = idProgressView;
        this.idErrorView = idErrorView;
    }
    protected void showMainView(){
        if(idMainView != -1 && getView() != null){
            View mainView = getView().findViewById(idMainView);
            if(mainView != null){
                if(getProgressViewStatusVisibility()){
                    hideProgressView();
                }
                if(getErrorViewStatusVisibility()){
                    hideErrorView();
                }
                mainView.setVisibility(View.VISIBLE);
            }
        }
    }
    protected void hideMainView(){
        if(idMainView != -1 && getView() != null){
            View mainView = getView().findViewById(idMainView);
            if(mainView != null){
                mainView.setVisibility(View.GONE);
            }
        }
    }
    protected boolean getMainViewStatusVisibility(){
        if(idMainView != -1 && getView() != null){
            View mainView = getView().findViewById(idMainView);
            if(mainView != null){
               return mainView.isShown();
            }
        }
        return false;
    }
    protected void showProgressView(String message){
        if(idProgressView != -1 && getView() != null){
            View mainView = getView().findViewById(idProgressView);
            if(mainView != null){
                if(getMainViewStatusVisibility()){
                    hideMainView();
                }
                if(getErrorViewStatusVisibility()){
                    hideErrorView();
                }
                MaterialTextView tvMessage = (MaterialTextView) getView().findViewById(R.id.progressText);
                if (tvMessage != null && message != null && !message.isEmpty()) {
                    tvMessage.setText(message);
                }
                mainView.setVisibility(View.VISIBLE);
            }
        }
    }
    protected void hideProgressView(){
        if(idProgressView != -1 && getView() != null){
            View mainView = getView().findViewById(idProgressView);
            if(mainView != null){
                mainView.setVisibility(View.GONE);
                showMainView();
            }
        }
    }
    protected boolean getProgressViewStatusVisibility(){
        if(idProgressView != -1 && getView() != null){
            View mainView = getView().findViewById(idProgressView);
            if(mainView != null){
                return mainView.isShown();
            }
        }
        return false;
    }

    protected void showErrorView(String message){
        if(idErrorView != -1 && getView() != null){
            View mainView = getView().findViewById(idErrorView);
            if(mainView != null){
                if(getMainViewStatusVisibility()){
                    hideMainView();
                }
                if(getProgressViewStatusVisibility()){
                    hideProgressView();
                }
                MaterialTextView tvMessage = (MaterialTextView) getView().findViewById(R.id.tvErrorDescription);
                if (tvMessage != null && message != null && !message.isEmpty()) {
                    tvMessage.setText(message);
                }
                mainView.setVisibility(View.VISIBLE);
            }
        }
    }
    protected void hideErrorView(){
        if(idErrorView != -1 && getView() != null){
            View mainView = getView().findViewById(idErrorView);
            if(mainView != null){
                mainView.setVisibility(View.GONE);
                showMainView();
            }
        }
    }
    protected boolean getErrorViewStatusVisibility(){
        if(idErrorView != -1 && getView() != null){
            View mainView = getView().findViewById(idErrorView);
            if(mainView != null){
                return mainView.isShown();
            }
        }
        return false;
    }
    @Override
    public void onDetach() {
        super.onDetach();
        this.eventOnFragment = null;
    }
    protected void showMessage(String message){
        if(eventOnFragment != null){
            eventOnFragment.onEvent(new DataManagerEvent(EVENT_SHOW_MESSAGE, message));
        }
    }
    protected void showProgressActivity(String message){
        if(eventOnFragment != null){
            eventOnFragment.onEvent(new DataManagerEvent(EVENT_SHOW_PROGRESS, message));
        }
    }
    protected void hideProgressActivity(){
        if(eventOnFragment != null){
            eventOnFragment.onEvent(new DataManagerEvent(EVENT_DISMISS_PROGRESS, null));
        }
    }
}
