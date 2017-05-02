package com.voobly.ratings.ui.base.fragments;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.voobly.ratings.R;
import com.voobly.ratings.ui.base.adapters.GenericRecyclerViewAdapter;
import com.voobly.ratings.ui.base.interfaces.IProcessData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvazquez on 02/05/2017.
 */

public abstract class BaseRecyclerFragment<iProcessData extends IProcessData, item, VH extends RecyclerView.ViewHolder, Adapter extends GenericRecyclerViewAdapter<item, VH>> extends BaseFragment<iProcessData> {
    private List<item> itemsList = new ArrayList<>();
    private Adapter adapter;
    @IdRes
    private int idMainRecycler;
    private RecyclerView rvMainRecycler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idMainRecycler = -1;
        initializeAdapter();
        setIdRecyclerView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycler(view);
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

    public Adapter getAdapter() {
        return adapter;
    }

    public RecyclerView getRecyclerView() {
        return rvMainRecycler;
    }

    protected abstract void initializeAdapter();

    protected abstract void setIdRecyclerView();

}
