package com.voobly.ratings.ui.base.factoryfragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.voobly.ratings.data.DataManagerEvent;
import com.voobly.ratings.ui.base.interfaces.IEventOnFragment;
import com.voobly.ratings.ui.base.interfaces.IProcessData;

import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_DISMISS_PROGRESS;
import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_SHOW_PROGRESS;
import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_SHOW_MESSAGE;

/**
 * @author Jose Alberto Vazquez
 * @email imketzal@gmail.com
 * @version 1.0
 * Created by jvazquez on 02/05/2017.
 */

public abstract class BaseFragment<iProcessData extends IProcessData> extends SupportUXFragment<iProcessData>{


}
