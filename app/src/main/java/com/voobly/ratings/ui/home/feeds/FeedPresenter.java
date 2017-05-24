package com.voobly.ratings.ui.home.feeds;

import android.util.Log;

import com.google.gson.Gson;
import com.voobly.ratings.data.DataManager;
import com.voobly.ratings.data.model.Channel;
import com.voobly.ratings.data.model.Feed;
import com.voobly.ratings.data.model.response.FeedResponse;
import com.voobly.ratings.data.remote.EndPoint;
import com.voobly.ratings.data.remote.VooblyRequestMethod;
import com.voobly.ratings.ui.base.presenters.BasePresenter;
import com.voobly.ratings.ui.home.interfaces.IEventFeed;
import com.voobly.ratings.ui.home.interfaces.IFeedProcessData;

import org.json.JSONObject;
import org.json.XML;

import java.util.List;

/**
 * Created by jvazquez on 17/05/2017.
 */

public class FeedPresenter extends BasePresenter<IEventFeed> implements IFeedProcessData {

    public FeedPresenter(IEventFeed view) {
        super(view);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void getFeed(EndPoint feed) {
        view.showProgress("Obteniendo noticias...");
        VooblyRequestMethod.getFeed(feed, this);
    }

    @Override
    public void onSuccess(DataManager dataManager) {
        super.onSuccess(dataManager);
        switch (dataManager.getEndPoint()){
            case FORUM_ANNOUNCEMENTS:
                if(dataManager.getData() instanceof String){
                    try{
                        JSONObject jsonObject = XML.toJSONObject((String) dataManager.getData());
                        FeedResponse response = new Gson().fromJson(jsonObject.toString(), FeedResponse.class);
                        view.updateList(addForumParameters(response.getRss().getChannel()));
                    }catch (Exception e){
                        Log.d(getClass().getSimpleName(), "Error parsing");
                    }
                }
                break;
        }
    }

    @Override
    public void onFailed(DataManager dataManager) {
        super.onFailed(dataManager);
        view.showError((String) dataManager.getData());
    }
    private List<Feed> addForumParameters(Channel channel){
        for (Feed feed: channel.getItem()){
            feed.setForumTitle(channel.getTitle());
            feed.setForumDescription(channel.getDescription());
        }
        return channel.getItem();
    }
}
