package com.voobly.ratings.ui.rank;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.voobly.ratings.R;
import com.voobly.ratings.data.model.Ladder;
import com.voobly.ratings.ui.base.factoryfragments.BaseRecyclerFragment;
import com.voobly.ratings.ui.home.RankPresenter;
import com.voobly.ratings.ui.home.interfaces.IEventRank;
import com.voobly.ratings.ui.home.interfaces.IRankProcessData;

import java.util.List;

import static com.voobly.ratings.utils.Constants.KEY_LADDER;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankFragment extends BaseRecyclerFragment<IRankProcessData, Ladder, RankAdapter.ViewHolder, RankAdapter> implements IEventRank<Ladder> {
    private String id = "";
    public static RankFragment newInstance(String ladder) {

        Bundle args = new Bundle();
        args.putString(KEY_LADDER, ladder);
        RankFragment fragment = new RankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public RankFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments().containsKey(KEY_LADDER)){
            id = getArguments().getString(KEY_LADDER);
        }else{
            throw new RuntimeException("no habilitado");
        }
        if(!id.isEmpty()){
            setItemsList(presenter.getLaddersById(id));
        }
    }

    @Override
    protected void initializeAdapter() {
        adapter = new RankAdapter(getItemsList(), eventOnFragment);
    }

    @Override
    protected void setIdRecyclerView() {
        idMainRecycler = R.id.rvRanks;
    }

    @Override
    protected void setIdSwipeRefreshLayout() {

    }

    @Override
    protected void refreshView() {
        if(!id.isEmpty()){
            setItemsList(presenter.getLaddersById(id));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_rank, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initializePresenter() {
        presenter = new RankPresenter(this);
    }

    @Override
    public void showProgress(String message) {
        super.showProgressView(message);
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {
        super.showErrorView(message);
    }

    @Override
    public void showMessage(String Message) {

    }

    @Override
    public void updateAdapter(List<Ladder> itemsList) {
        super.updateList(itemsList);
    }
}
