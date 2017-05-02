package com.voobly.ratings.data.remote.interfaces;

import com.voobly.ratings.data.DataManager;

/**
 * Created by jvazquez on 24/04/2017.
 */

public interface IRequestResult {
    void onSuccess(DataManager dataManager);
    void onFailed(DataManager dataManager);
}
