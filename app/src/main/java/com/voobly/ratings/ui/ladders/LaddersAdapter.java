package com.voobly.ratings.ui.ladders;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.voobly.ratings.utils.customviews.MaterialTextView;

import com.voobly.ratings.R;
import com.voobly.ratings.data.DataManagerEvent;
import com.voobly.ratings.ui.base.adapters.GenericRecyclerViewAdapter;
import com.voobly.ratings.ui.base.interfaces.IEventOnFragment;

import java.util.List;

import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_LADDER_ELEMENT;
import static com.voobly.ratings.utils.Utils.findLadderName;

/**
 * Created by jvazquez on 16/05/2017.
 */

public class LaddersAdapter extends GenericRecyclerViewAdapter<String, LaddersAdapter.ViewHolder> {

    public LaddersAdapter(List<String> items, IEventOnFragment onEventListener) {
        super(items, onEventListener);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_ladder, parent, false);
        return new ViewHolder(v);    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String currentElement = items.get(position);
        holder.tvLadderName.setText(findLadderName(currentElement).isEmpty() ? currentElement: findLadderName(currentElement));
        holder.cvContentElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventOnFragment.onEvent(new DataManagerEvent(EVENT_LADDER_ELEMENT, currentElement));
            }
        });

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView tvLadderName;
        private ImageView ivLadder;
        private FrameLayout cvContentElement;

        ViewHolder(View itemView) {
            super(itemView);
            tvLadderName      = (MaterialTextView) itemView.findViewById(R.id.tvLadderName);
            ivLadder          = (ImageView) itemView.findViewById(R.id.ivLadder);
            cvContentElement = (FrameLayout) itemView.findViewById(R.id.cvContentElement);
        }
    }
}
