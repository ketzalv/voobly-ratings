package com.voobly.ratings.ui.base.presenters;

import com.voobly.ratings.data.remote.interfaces.IRequestResult;
import com.voobly.ratings.ui.base.interfaces.IEventOnView;

/**
 * Created by jvazquez on 02/05/2017.
 */

public abstract class BasePresenter<iEventOnView extends IEventOnView> implements IRequestResult {
    protected iEventOnView view;

    public BasePresenter(iEventOnView view){
        this.view = view;
    }
}
