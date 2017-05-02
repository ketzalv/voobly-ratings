package com.voobly.ratings.ui.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.voobly.ratings.R;
import com.voobly.ratings.data.DataManagerRecycler;
import com.voobly.ratings.ui.base.fragments.BaseFragment;
import com.voobly.ratings.ui.base.fragments.BaseRecyclerFragment;
import com.voobly.ratings.ui.home.interfaces.IEventHome;
import com.voobly.ratings.ui.home.interfaces.IHomeProcessData;

/**
 * @author Jose Alberto Vazquez
 * @email imketzal@gmail.com
 * @version 1.0
 * Created by jvazquez on 02/05/2017.
 */

public class HomeFragment extends BaseFragment<IHomeProcessData> implements IEventHome, View.OnClickListener {

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hideProgress();
        view.findViewById(R.id.pulsar).setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    protected void initializePresenter() {
        presenter = new HomePresenter(this);
    }

    @Override
    public void showProgress(String message) {
        showProgressActivity(message);
    }

    @Override
    public void hideProgress() {
        super.hideProgressActivity();
    }

    @Override
    public void showMessage(String message) {
        super.showMessage(message);
    }

    @Override
    public void onClick(View v) {
        presenter.getLobbies();
    }
}
