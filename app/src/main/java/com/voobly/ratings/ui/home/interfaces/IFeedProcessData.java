package com.voobly.ratings.ui.home.interfaces;

import com.voobly.ratings.data.remote.EndPoint;
import com.voobly.ratings.ui.base.interfaces.IProcessData;

/**
 * Created by jvazquez on 17/05/2017.
 */

public interface IFeedProcessData extends IProcessData {
    void getFeed(EndPoint feed);
}
