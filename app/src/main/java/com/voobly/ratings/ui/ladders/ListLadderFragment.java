package com.voobly.ratings.ui.ladders;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.voobly.ratings.R;
import com.voobly.ratings.data.model.Lobby;
import com.voobly.ratings.ui.base.factoryfragments.BaseRecyclerFragment;
import com.voobly.ratings.ui.home.RankPresenter;
import com.voobly.ratings.ui.home.interfaces.IEventRank;
import com.voobly.ratings.ui.home.interfaces.IRankProcessData;

import java.util.List;

import static com.voobly.ratings.utils.Constants.KEY_LOBBY;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListLadderFragment extends BaseRecyclerFragment<IRankProcessData, String, LaddersAdapter.ViewHolder, LaddersAdapter> implements IEventRank<String> {

    private Lobby lobby;

    public static ListLadderFragment newInstance(Lobby lobby) {

        Bundle args = new Bundle();
        args.putString(KEY_LOBBY, new Gson().toJson(lobby));
        ListLadderFragment fragment = new ListLadderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ListLadderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null && getArguments().containsKey(KEY_LOBBY)){
            lobby = new Gson().fromJson(getArguments().getString(KEY_LOBBY), Lobby.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_ladder, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateAdapter(lobby.getLadders());

    }

    @Override
    public void showProgress(String message) {
       super.showProgressActivity(message);
    }

    @Override
    public void hideProgress() {
        super.hideProgressActivity();
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
        super.showMessage(message);
    }

    @Override
    public void updateAdapter(List<String> itemsList) {
        super.updateList(itemsList);
    }

    @Override
    protected void initializeAdapter() {
        adapter = new LaddersAdapter(getItemsList(), eventOnFragment);
    }

    @Override
    protected void setIdRecyclerView() {
        idMainRecycler = R.id.rvLadders;
    }

    @Override
    protected void setIdSwipeRefreshLayout() {

    }

    @Override
    protected void refreshView() {
        updateAdapter(lobby.getLadders());
    }
}
