package com.voobly.ratings.ui.lobbies;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.voobly.ratings.R;
import com.voobly.ratings.data.model.Lobby;
import com.voobly.ratings.ui.base.factoryfragments.BaseRecyclerFragment;
import com.voobly.ratings.ui.home.RankPresenter;
import com.voobly.ratings.ui.home.interfaces.IEventRank;
import com.voobly.ratings.ui.home.interfaces.IRankProcessData;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListLobbyFragment extends BaseRecyclerFragment<IRankProcessData, Lobby, LobbiesAdapter.ViewHolder, LobbiesAdapter> implements IEventRank<Lobby> {


    public static ListLobbyFragment newInstance() {

        Bundle args = new Bundle();

        ListLobbyFragment fragment = new ListLobbyFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public ListLobbyFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_lobby, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setIdContainers(R.id.rvLobbies, R.id.progressView, R.id.errorView);
        if(getItemsList().isEmpty()){
            presenter.getLobbies();
        }
    }

    @Override
    public void showProgress(String message) {
        super.showProgressView(message);
    }

    @Override
    public void hideProgress() {
        super.hideProgressView();
    }

    @Override
    public void showError(String message) {
        super.showErrorView(message);
    }

    @Override
    protected void initializePresenter() {
        presenter = new RankPresenter(this);
    }

    @Override
    public void showMessage(String message) {
        if(getItemsList().size() == 0){
            showError(message);
        }
        super.showMessage(message);
    }

    @Override
    public void updateAdapter(List<Lobby> itemsList) {
        super.updateList(itemsList);
    }

    @Override
    protected void initializeAdapter() {
        adapter = new LobbiesAdapter(getItemsList(), eventOnFragment);
    }

    @Override
    protected void setIdRecyclerView() {
        idMainRecycler = R.id.rvLobbies;
    }

    @Override
    protected void setIdSwipeRefreshLayout() {
        idSwipeRefreshLayout = R.id.srlLobbies;
    }

    @Override
    protected void refreshView() {
        presenter.getLobbies();
    }
}
