package com.voobly.ratings.ui.base.factoryfragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.voobly.ratings.ui.base.interfaces.IProcessData;

/**
 * Created by jvazquez on 16/05/2017.
 */

public abstract class SupportPresenterFragment<iProcessData extends IProcessData> extends Fragment {

    protected iProcessData presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializePresenter();
        if(presenter != null){
            presenter.onCreate();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter != null){
            presenter.onDestroy();
        }
    }

    protected abstract void initializePresenter();

}
