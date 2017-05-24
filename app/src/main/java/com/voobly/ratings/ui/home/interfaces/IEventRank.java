package com.voobly.ratings.ui.home.interfaces;

import com.voobly.ratings.data.model.Lobby;
import com.voobly.ratings.ui.base.interfaces.IEventOnView;

import java.util.List;

/**
 * Created by jvazquez on 02/05/2017.
 */

public interface IEventRank<T> extends IEventOnView{
    void updateAdapter(List<T> itemsList);
}
