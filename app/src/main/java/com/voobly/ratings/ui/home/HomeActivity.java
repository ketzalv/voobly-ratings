package com.voobly.ratings.ui.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.voobly.ratings.R;
import com.voobly.ratings.data.DataManager;
import com.voobly.ratings.data.model.UserVoobly;
import com.voobly.ratings.data.remote.VooblyRequestMethod;
import com.voobly.ratings.data.remote.interfaces.IRequestResult;
import com.voobly.ratings.ui.base.activities.BaseActivity;
import com.voobly.ratings.utils.CastObjectResponse;

public class HomeActivity extends BaseActivity implements IRequestResult, View.OnClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setIdsContainers(R.id.coordinatorContainer, R.id.fragmentContainer, R.id.progressContainer);
        loadFragment(HomeFragment.newInstance());
    }

    @Override
    public void onSuccess(DataManager dataManager) {
        Log.d(getClass().getSimpleName(), "RESPONSE > " + dataManager.getData());
        Log.d(getClass().getSimpleName(), "RESPONSE OBJECT FORMATED: " + new Gson().toJson(CastObjectResponse.castVooblyResponseToObject(UserVoobly.class, (String) dataManager.getData())));
    }

    @Override
    public void onFailed(DataManager dataManager) {
        Log.d(getClass().getSimpleName(), "RESPONSE > " + dataManager.getData());
    }

    @Override
    public void onClick(View v) {
        VooblyRequestMethod.getUserInformation(this, "149723");
    }
}
