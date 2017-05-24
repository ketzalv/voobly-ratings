package com.voobly.ratings.data.model.response;

import com.voobly.ratings.data.model.Channel;
import com.voobly.ratings.data.model.Rss;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvazquez on 17/05/2017.
 */

public class FeedResponse implements Serializable {
    private Rss rss = new Rss();

    public FeedResponse(){

    }

    public Rss getRss() {
        return rss;
    }

    public void setRss(Rss rss) {
        this.rss = rss;
    }
}
