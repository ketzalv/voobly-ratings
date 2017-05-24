package com.voobly.ratings.ui.home.feeds;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.voobly.ratings.utils.customviews.MaterialTextView;
import com.voobly.ratings.R;
import com.voobly.ratings.data.DataManagerEvent;
import com.voobly.ratings.data.model.Feed;
import com.voobly.ratings.data.model.Lobby;
import com.voobly.ratings.ui.base.adapters.GenericRecyclerViewAdapter;
import com.voobly.ratings.ui.base.interfaces.IEventOnFragment;
import com.voobly.ratings.ui.lobbies.LobbiesAdapter;

import java.util.List;

import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_SHOW_MESSAGE;

/**
 * Created by jvazquez on 17/05/2017.
 */

public class FeedsAdapter extends GenericRecyclerViewAdapter<Feed, FeedsAdapter.ViewHolder> {


    public FeedsAdapter(List<Feed> items, IEventOnFragment onEventListener) {
        super(items, onEventListener);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_feed, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Feed currentItem = items.get(position);
        holder.tvForumTitle.setText(currentItem.getForumTitle());
        holder.tvForumDescription.setText(currentItem.getForumDescription());

        holder.tvThreadTitle.setText(currentItem.getTitle());
        holder.tvThreadDescription.setText(currentItem.getDescription());
        holder.tvThreadAuthor.setText(currentItem.getAuthor());
        holder.tvThreadDate.setText(currentItem.getPubDate());

        holder.tvReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventOnFragment.onEvent(new DataManagerEvent(EVENT_SHOW_MESSAGE, "Implementar webView " + currentItem.getLink()));
            }
        });

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView tvForumTitle, tvForumDescription, tvThreadTitle, tvThreadDescription, tvThreadAuthor, tvThreadDate, tvReadMore;
        private ImageView ivLobby;
        private FrameLayout cvContentElement;

        ViewHolder(View itemView) {
            super(itemView);

            tvForumTitle        = (MaterialTextView) itemView.findViewById(R.id.tvForumTitle);
            tvForumDescription  = (MaterialTextView) itemView.findViewById(R.id.tvForumDescription);

            tvThreadTitle       = (MaterialTextView) itemView.findViewById(R.id.tvThreadTitle);
            tvThreadDescription = (MaterialTextView) itemView.findViewById(R.id.tvThreadDescription);
            tvThreadAuthor      = (MaterialTextView) itemView.findViewById(R.id.tvThreadAuthor);
            tvThreadDate        = (MaterialTextView) itemView.findViewById(R.id.tvThreadDate);
            tvReadMore          = (MaterialTextView) itemView.findViewById(R.id.tvReadMore);
            ivLobby             = (ImageView) itemView.findViewById(R.id.ivLobby);
            cvContentElement    = (FrameLayout) itemView.findViewById(R.id.cvContentElement);
        }
    }
}
