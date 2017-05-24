package com.voobly.ratings.ui.lobbies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.voobly.ratings.R;
import com.voobly.ratings.data.DataManagerEvent;
import com.voobly.ratings.data.model.Lobby;
import com.voobly.ratings.ui.base.adapters.GenericRecyclerViewAdapter;
import com.voobly.ratings.ui.base.interfaces.IEventOnFragment;
import com.voobly.ratings.utils.customviews.MaterialTextView;

import java.util.List;

import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_LOBBY_ELEMENT;

/**
 * Created by jvazquez on 15/05/2017.
 */

public class LobbiesAdapter extends GenericRecyclerViewAdapter<Lobby, LobbiesAdapter.ViewHolder> {


    public LobbiesAdapter(List<Lobby> items, IEventOnFragment onEventListener) {
        super(items, onEventListener);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_lobby, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Lobby currentElement = items.get(position);
        holder.tvLobbyName.setText(currentElement.getName());
        holder.cvContentElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventOnFragment.onEvent(new DataManagerEvent(EVENT_LOBBY_ELEMENT, currentElement));
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView tvLobbyName;
        private ImageView ivLobby;
        private FrameLayout cvContentElement;

        ViewHolder(View itemView) {
            super(itemView);
            tvLobbyName     = (MaterialTextView) itemView.findViewById(R.id.tvLobbyName);
            ivLobby         = (ImageView) itemView.findViewById(R.id.ivLobby);
            cvContentElement = (FrameLayout) itemView.findViewById(R.id.cvContentElement);
        }
    }
}
