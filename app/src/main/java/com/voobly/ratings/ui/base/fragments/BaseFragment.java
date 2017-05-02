package com.voobly.ratings.ui.base.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.voobly.ratings.data.DataManagerEvent;
import com.voobly.ratings.ui.base.interfaces.IEventOnFragment;
import com.voobly.ratings.ui.base.interfaces.IEventOnView;
import com.voobly.ratings.ui.base.interfaces.IProcessData;

import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_DISMISS_DIALOG;
import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_SHOW_DIALOG;
import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_SHOW_MESSAGE;

/**
 * @author Jose Alberto Vazquez
 * @email imketzal@gmail.com
 * @version 1.0
 * Created by jvazquez on 02/05/2017.
 */

public abstract class BaseFragment<iProcessData extends IProcessData> extends Fragment{

    protected IEventOnFragment eventOnFragment;
    protected iProcessData presenter;

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
        initializePresenter();
    }

    protected abstract void initializePresenter();

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
            eventOnFragment.onEvent(new DataManagerEvent(EVENT_SHOW_DIALOG, message));
        }
    }
    protected void hideProgressActivity(){
        if(eventOnFragment != null){
            eventOnFragment.onEvent(new DataManagerEvent(EVENT_DISMISS_DIALOG, null));
        }
    }
}
