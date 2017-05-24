package com.voobly.ratings.data.local.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.Gson;
import com.voobly.ratings.data.local.DBContract;
import com.voobly.ratings.data.local.dao._manager.InterfaceDao;
import com.voobly.ratings.data.model.Ladder;
import com.voobly.ratings.data.model.Lobby;
import com.voobly.ratings.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.voobly.ratings.data.local.DBContract.Lobbies.COLUMN_ID;
import static com.voobly.ratings.data.local.DBContract.Lobbies.COLUMN_LADDERS;
import static com.voobly.ratings.data.local.DBContract.Lobbies.COLUMN_LOBBYID;
import static com.voobly.ratings.data.local.DBContract.Lobbies.COLUMN_MAX_PLAYERS;
import static com.voobly.ratings.data.local.DBContract.Lobbies.COLUMN_NAME;
import static com.voobly.ratings.data.local.DBContract.Lobbies.COLUMN_PLAYERS_ONLINE;

/**
 * Created by jvazquez on 03/05/2017.
 */

public class LobbyDao extends InterfaceDao<Lobby, Integer> {

    public LobbyDao(){
        super();
        TABLE_NAME = DBContract.Lobbies.TABLE_NAME;
        primaryKey = COLUMN_ID;
    }
    @Override
    public int insertObject(Lobby object) {
        return insert(TABLE_NAME, getContentValues(object));
    }

    @Override
    public int insertObjects(List<Lobby> objects) {
        if(objects != null && objects.size() > 0){
            delete(TABLE_NAME, null);
            for(Lobby object: objects){
                insert(TABLE_NAME, getContentValues(object));
            }
        }
        return 0;
    }

    @Override
    public Lobby getObjectByPrimaryKey(Integer integer) {
        open();
        Cursor queryResult = query(TABLE_NAME, this.primaryKey = " = " + integer);
        Lobby lobby = new Lobby();
        if(queryResult.moveToFirst()){
            do{
                lobby.setLobbyid(queryResult.getString(queryResult.getColumnIndex(COLUMN_LOBBYID)));
                lobby.setName(queryResult.getString(queryResult.getColumnIndex(COLUMN_NAME)));
                lobby.setPlayers_online(queryResult.getString(queryResult.getColumnIndex(COLUMN_PLAYERS_ONLINE)));
                lobby.setMax_players(queryResult.getString(queryResult.getColumnIndex(COLUMN_MAX_PLAYERS)));
                try{
                    lobby.setLadders(Utils.jsonArrayToList(String.class, queryResult.getString(queryResult.getColumnIndex(DBContract.Lobbies.COLUMN_LADDERS))));
                }catch (Exception e){
                    lobby.setLadders(new ArrayList<String>());
                }
            }while (queryResult.moveToNext());
        }
        return lobby;
    }

    @Override
    public List<Lobby> getObjects() {
        List<Lobby> lobbies = new ArrayList<>();
        open();
        Cursor queryResult = query(TABLE_NAME, null);
        Lobby lobby;
        if(queryResult.moveToFirst()){
            do{
                lobby = new Lobby();
                lobby.setLobbyid(queryResult.getString(queryResult.getColumnIndex(COLUMN_LOBBYID)));
                lobby.setName(queryResult.getString(queryResult.getColumnIndex(COLUMN_NAME)));
                lobby.setPlayers_online(queryResult.getString(queryResult.getColumnIndex(COLUMN_PLAYERS_ONLINE)));
                lobby.setMax_players(queryResult.getString(queryResult.getColumnIndex(COLUMN_MAX_PLAYERS)));
                try{
                    lobby.setLadders(Utils.jsonArrayToList(String.class, queryResult.getString(queryResult.getColumnIndex(DBContract.Lobbies.COLUMN_LADDERS))));
                }catch (Exception e){
                    lobby.setLadders(new ArrayList<String>());
                }
                lobbies.add(lobby);
            }while (queryResult.moveToNext());
        }
        close();
        return lobbies;
    }

    @Override
    public List<Lobby> findObjects(String query, String... columns) {
        return null;
    }

    @Override
    public int updateObject(Lobby object) {
        open();
        int i = update(TABLE_NAME, getContentValues(object), primaryKey + " = " + object.getLobbyid());
        close();
        return i;
    }

    @Override
    public int updateObjects(List<Lobby> objects) {
        return 0;
    }

    @Override
    public int deleteObjectByPrimaryKey(Integer integer) {
        return delete(TABLE_NAME, this.primaryKey + " = '" + integer + "'");
    }

    @Override
    public int deleteObjects(List<Integer> keys) {
        if(keys != null && keys.size() > 0){
            open();
            for(Integer primaryKey: keys){
                delete(TABLE_NAME, this.primaryKey + " = " + primaryKey);
            }
            close();
        }
        return 0;
    }

    @Override
    protected ContentValues getContentValues(Lobby object) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID            , object.getLobbyid());
        values.put(COLUMN_LOBBYID       , object.getLobbyid());
        values.put(COLUMN_NAME          , object.getName());
        values.put(COLUMN_PLAYERS_ONLINE, object.getPlayers_online());
        values.put(COLUMN_MAX_PLAYERS   , object.getMax_players());
        values.put(COLUMN_LADDERS       , new Gson().toJson(object.getLadders()));
        return values;
    }

    @Override
    public void onDestroy() {
        close();
    }
}
