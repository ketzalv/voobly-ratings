package com.voobly.ratings.ui.home;

import android.util.Log;

import com.google.gson.Gson;
import com.voobly.ratings.data.DataManager;
import com.voobly.ratings.data.local.dao.LadderDao;
import com.voobly.ratings.data.local.dao.LobbyDao;
import com.voobly.ratings.data.model.Ladder;
import com.voobly.ratings.data.model.Lobby;
import com.voobly.ratings.data.remote.VooblyRequestMethod;
import com.voobly.ratings.ui.base.presenters.BasePresenter;
import com.voobly.ratings.ui.home.interfaces.IEventRank;
import com.voobly.ratings.ui.home.interfaces.IRankProcessData;
import com.voobly.ratings.utils.CastObjectResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvazquez on 02/05/2017.
 */

public class RankPresenter extends BasePresenter<IEventRank> implements IRankProcessData {

    private LadderDao ladderDao = new LadderDao();
    private LobbyDao lobbyDao = new LobbyDao();

    public RankPresenter(IEventRank view) {
        super(view);
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
        ladderDao.onDestroy();
    }

    @Override
    public void getLobbies() {
        view.showProgress("Recuperando Lobbies");
        VooblyRequestMethod.getLobbies(this);
    }

    @Override
    public void getLadder(String ladder) {

    }

    @Override
    public void onSuccess(DataManager dataManager) {
        view.hideProgress();
        switch (dataManager.getEndPoint()){
            case LOBBIES:
                List<Lobby> lobbies = CastObjectResponse.castVooblyResponseToObject(Lobby.class, (String) dataManager.getData());

                List<Lobby> lobbiesWithLadder = new ArrayList<>();
                lobbiesWithLadder.addAll(clearLobbyWithOutLadder(lobbies));
                for(Lobby lobby: lobbiesWithLadder){
                    List<String> ladderList = findLadders(lobby.getLadders());
                    if(ladderList.size() > 0){
                        for(String ladder: ladderList){
                            requestTop5Ladder(ladder + ","+ lobby.getLobbyid(),ladder);
                        }

                    }
                }
                view.updateAdapter(lobbiesWithLadder);
//                Log.d(getClass().getSimpleName(), "RESPONSE > " + dataManager.getData());
//                Log.d(getClass().getSimpleName(), "RESPONSE OBJECT FORMATED: " + new Gson().toJson(CastObjectResponse.castVooblyResponseToObject(Lobby.class, (String) dataManager.getData())));
                break;
            case LADDER:
                Log.d(getClass().getSimpleName(), "RESPONSE  > " + dataManager.getData());
                List<Ladder> ladders = CastObjectResponse.castVooblyResponseToObject(Ladder.class, (String) dataManager.getData());
                for(Ladder ladder: ladders){
                    try{
                        String[] splitIdentifier = dataManager.getIdentifier().split(",");
                        ladder.setId(splitIdentifier[0]);
                        ladder.setLobby(splitIdentifier[1]);
                    }catch (Exception e) {}
                    Log.d(getClass().getSimpleName(), "Ladder " + new Gson().toJson(ladder));
                    ladderDao.insertObject(ladder);
                }
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
    private List<Lobby> clearLobbyWithOutLadder(List<Lobby> lobbies){
        List<Lobby> lobbyList = new ArrayList<>();
        for(Lobby lobby: lobbies){
            if(lobby.getLadders() != null && !lobby.getLadders().isEmpty()){
                lobbyList.add(lobby);
            }
        }
        return lobbyList;
    }
    private void requestTop5Ladder(String lobby, String ladder){
        VooblyRequestMethod.getTopLadders(lobby,ladder, this);
    }
    @Override
    public List<String> findLadders(List<String> strings){
        List<String> laddersNeedUpdate = new ArrayList<>();
        for(String ladder: strings){
            List<Ladder> ladderList = ladderDao.findLadders(ladder);
            if(ladderList.size() == 0){
                laddersNeedUpdate.add(ladder);
            }
        }
        return laddersNeedUpdate;
    }

    @Override
    public List<Ladder> getLaddersById(String id) {
        return ladderDao.findLadders(id);
    }
}
