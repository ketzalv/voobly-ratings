package com.voobly.ratings.ui.rank;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.voobly.ratings.utils.customviews.MaterialTextView;
import com.voobly.ratings.R;
import com.voobly.ratings.VooblyApp;
import com.voobly.ratings.data.model.Ladder;
import com.voobly.ratings.ui.base.adapters.GenericRecyclerViewAdapter;
import com.voobly.ratings.ui.base.interfaces.IEventOnFragment;

import java.util.List;

/**
 * Created by jvazquez on 08/05/2017.
 */

public class RankAdapter extends GenericRecyclerViewAdapter<Ladder, RankAdapter.ViewHolder> {

    private Context context = VooblyApp.getInstance().getApplicationContext();


    public RankAdapter(List<Ladder> items, IEventOnFragment onEventRVListener) {
        super(items, onEventRVListener);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_rank, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Ladder ladder = items.get(position);
        holder.tvName.setText(ladder.getDisplay_name());
        holder.tvRank.setText(context.getString(R.string.text_rank, ladder.getRank()));
        holder.tvRating.setText(context.getString(R.string.text_rating,ladder.getRating()));
        holder.tvWins.setText(context.getString(R.string.text_wins,ladder.getWins()));
        holder.tvLosses.setText(context.getString(R.string.text_losses,ladder.getLosses()));
        holder.tvStreak.setText(context.getString(R.string.text_streak,ladder.getStreak()));

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView tvName, tvRank, tvRating, tvWins, tvLosses, tvStreak;
        private AppCompatImageView ivProfile;
        private FrameLayout content_element;

        ViewHolder(View itemView) {
            super(itemView);
            tvName      = (MaterialTextView) itemView.findViewById(R.id.tvName);
            tvRank      = (MaterialTextView) itemView.findViewById(R.id.tvRank);
            tvRating    = (MaterialTextView) itemView.findViewById(R.id.tvRating);
            tvWins      = (MaterialTextView) itemView.findViewById(R.id.tvWins);
            tvLosses    = (MaterialTextView) itemView.findViewById(R.id.tvLosses);
            tvStreak    = (MaterialTextView) itemView.findViewById(R.id.tvStreak);
//            content_element = (FrameLayout) itemView.findViewById(R.id.cvContentElement);
        }
    }
}