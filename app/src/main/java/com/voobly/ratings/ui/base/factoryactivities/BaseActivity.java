package com.voobly.ratings.ui.base.factoryactivities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.voobly.ratings.VooblyApp;
import com.voobly.ratings.data.local.Preferencias;

import static com.voobly.ratings.utils.Utils.showSnackBar;

/**
 * @author Jose Alberto Vazquez
 * @email imketzal@gmail.com
 * @version 1.0
 * Created by jvazquez on 02/05/2017.
 */

public  abstract class BaseActivity extends SupportUXActivity {


    protected Preferencias pref;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = VooblyApp.getInstance().getPref();
        setTitle("");
    }
}
