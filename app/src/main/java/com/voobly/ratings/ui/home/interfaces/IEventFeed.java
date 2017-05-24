package com.voobly.ratings.ui.home.interfaces;

import com.voobly.ratings.data.model.Feed;
import com.voobly.ratings.ui.base.interfaces.IEventOnView;

import java.util.List;

/**
 * Created by jvazquez on 17/05/2017.
 */

public interface IEventFeed extends IEventOnView {
    void updateList(List<Feed> items);
}
