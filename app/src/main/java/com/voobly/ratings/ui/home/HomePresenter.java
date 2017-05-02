package com.voobly.ratings.ui.home;

import android.util.Log;

import com.google.gson.Gson;
import com.voobly.ratings.data.DataManager;
import com.voobly.ratings.data.model.Lobby;
import com.voobly.ratings.data.model.UserVoobly;
import com.voobly.ratings.data.remote.VooblyRequestMethod;
import com.voobly.ratings.data.remote.interfaces.IRequestResult;
import com.voobly.ratings.ui.base.interfaces.IEventOnView;
import com.voobly.ratings.ui.base.presenters.BasePresenter;
import com.voobly.ratings.ui.home.interfaces.IEventHome;
import com.voobly.ratings.ui.home.interfaces.IHomeProcessData;
import com.voobly.ratings.utils.CastObjectResponse;

import java.util.List;

/**
 * Created by jvazquez on 02/05/2017.
 */

public class HomePresenter extends BasePresenter implements IHomeProcessData{

    public HomePresenter(IEventHome view) {
        super(view);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void getLobbies() {
        view.showProgress("Recuperando Lobbies");
        VooblyRequestMethod.getLobbies(this);
    }

    @Override
    public void onSuccess(DataManager dataManager) {
        view.hideProgress();
        switch (dataManager.getEndPoint()){
            case LOBBIES:
                List<Lobby> lobbies = CastObjectResponse.castVooblyResponseToObject(Lobby.class, (String) dataManager.getData());

                for(Lobby lobby: lobbies)
                    Log.d(getClass().getSimpleName(), "Lobby: " + new Gson().toJson(lobby));
//                Log.d(getClass().getSimpleName(), "RESPONSE > " + dataManager.getData());
//                Log.d(getClass().getSimpleName(), "RESPONSE OBJECT FORMATED: " + new Gson().toJson(CastObjectResponse.castVooblyResponseToObject(Lobby.class, (String) dataManager.getData())));
                break;
        }
    }

    @Override
    public void onFailed(DataManager dataManager) {
        view.hideProgress();
        switch (dataManager.getEndPoint()){
            case LOBBIES:
                if(dataManager.getData() instanceof String){
                    view.showMessage((String) dataManager.getData());
                }
                break;
            default:
                if(dataManager.getData() instanceof String){
                    view.showMessage((String) dataManager.getData());
                }
                break;
        }
    }
}
