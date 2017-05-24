package com.voobly.ratings.ui.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.voobly.ratings.R;
import com.voobly.ratings.data.DataManagerEvent;
import com.voobly.ratings.data.model.Feed;
import com.voobly.ratings.data.model.Lobby;
import com.voobly.ratings.data.remote.EndPoint;
import com.voobly.ratings.ui.base.factoryfragments.BaseRecyclerFragment;
import com.voobly.ratings.ui.home.feeds.FeedPresenter;
import com.voobly.ratings.ui.home.feeds.FeedsAdapter;
import com.voobly.ratings.ui.home.interfaces.IEventFeed;
import com.voobly.ratings.ui.home.interfaces.IEventRank;
import com.voobly.ratings.ui.home.interfaces.IFeedProcessData;
import com.voobly.ratings.ui.home.interfaces.IRankProcessData;
import com.voobly.ratings.ui.lobbies.LobbiesAdapter;

import java.util.List;

import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_GO_RANKING;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseRecyclerFragment<IFeedProcessData, Feed, FeedsAdapter.ViewHolder, FeedsAdapter> implements IEventFeed, View.OnClickListener {

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setIdContainers(R.id.rvFeeds, R.id.progressView, R.id.errorView);
        if(getItemsList().isEmpty()){
            getFeeds();
        }
        if(getActivity().findViewById(R.id.btnSearch) != null){
            getActivity().findViewById(R.id.btnSearch).setOnClickListener(this);
        }
        if(getActivity().findViewById(R.id.btnRanking) != null){
            getActivity().findViewById(R.id.btnRanking).setOnClickListener(this);
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
        if(getItemsList().isEmpty()){
            super.showErrorView(message);
        }
        super.showMessage(message);
    }

    @Override
    public void showMessage(String message) {
        super.showMessage(message);
    }

    @Override
    protected void initializePresenter() {
        presenter = new FeedPresenter(this);
    }

    @Override
    protected void initializeAdapter() {
        adapter = new FeedsAdapter(getItemsList(), eventOnFragment);
    }

    @Override
    protected void setIdRecyclerView() {
        idMainRecycler = R.id.rvFeeds;
    }

    @Override
    protected void setIdSwipeRefreshLayout() {
        idSwipeRefreshLayout = R.id.srlFeeds;
    }

    @Override
    protected void refreshView() {
        getFeeds();
    }

    private void getFeeds() {
        presenter.getFeed(EndPoint.FORUM_ANNOUNCEMENTS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSearch:
                getFeeds();
                showMessage("Implementar find user");
                break;
            case R.id.btnRanking:
                eventOnFragment.onEvent(new DataManagerEvent(EVENT_GO_RANKING, null));
                break;
        }
    }

    @Override
    public void updateList(List<Feed> items) {
        super.updateList(items);
    }
}
