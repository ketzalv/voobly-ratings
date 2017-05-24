package com.voobly.ratings.ui.base.factoryfragments;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.voobly.ratings.ui.base.adapters.GenericRecyclerViewAdapter;
import com.voobly.ratings.ui.base.interfaces.IProcessData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jose Alberto Vazquez
 * @email imketzal@gmail.com
 * @version 1.0
 * Created by jvazquez on 02/05/2017.
 */

public abstract class BaseRecyclerFragment<iProcessData extends IProcessData, item, VH extends RecyclerView.ViewHolder, Adapter extends GenericRecyclerViewAdapter<item, VH>> extends BaseFragment<iProcessData> implements SwipeRefreshLayout.OnRefreshListener {
    private List<item> itemsList = new ArrayList<>();
    protected Adapter adapter;
    @IdRes
    protected int idMainRecycler;
    private RecyclerView rvMainRecycler;
    @IdRes
    protected int idSwipeRefreshLayout;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idMainRecycler = -1;
        initializeAdapter();
        setIdRecyclerView();
        setIdSwipeRefreshLayout();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycler(view);
        initSwipeToRefresh(view);
    }

    private void initSwipeToRefresh(View view) {
        if(idSwipeRefreshLayout != -1 && view != null){
            SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(idSwipeRefreshLayout);
            if(swipeRefreshLayout != null){
                swipeRefreshLayout.setOnRefreshListener(this);
            }
        }
    }

    protected void initRecycler(View view) {
        if(idMainRecycler != -1 && view != null){
            RecyclerView recyclerView = (RecyclerView) view.findViewById(idMainRecycler);
            if(recyclerView != null){
                LinearLayoutManager lm = new LinearLayoutManager(getContext(), 1, false);
                lm.setOrientation(GridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(lm);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
            }
        }

    }

    public void setItemsList(List<item> itemsList) {
        this.itemsList = itemsList;
        adapter.updateList(itemsList);
    }

    public List<item> getItemsList() {
        return itemsList;
    }

    protected void updateList(List<item> itemsList){
        if(adapter != null){
            adapter.updateList(itemsList);
        }
    }
    public Adapter getAdapter() {
        return adapter;
    }

    public RecyclerView getRecyclerView() {
        return rvMainRecycler;
    }

    protected abstract void initializeAdapter();

    protected abstract void setIdRecyclerView();

    protected abstract void setIdSwipeRefreshLayout();

    protected abstract void refreshView();

    @Override
    public void onRefresh() {
        if(swipeRefreshLayout != null){
            swipeRefreshLayout.setRefreshing(true);
        }
        refreshView();
    }

    @Override
    protected void showErrorView(String message) {
        super.showErrorView(message);
        if(swipeRefreshLayout != null){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    protected void hideProgressView() {
        super.hideProgressView();
        if(swipeRefreshLayout != null){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    protected void hideProgressActivity() {
        super.hideProgressActivity();
        if(swipeRefreshLayout != null){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    protected void showProgressView(String message) {
        super.showProgressView(message);
        if(swipeRefreshLayout != null){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    protected void showMainView() {
        super.showMainView();
        if(swipeRefreshLayout != null){
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    protected void showProgressActivity(String message) {
        super.showProgressActivity(message);
        if(swipeRefreshLayout != null){
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
