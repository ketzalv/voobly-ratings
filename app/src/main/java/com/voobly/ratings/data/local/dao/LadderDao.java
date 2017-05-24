package com.voobly.ratings.data.local.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.voobly.ratings.data.local.DBContract;
import com.voobly.ratings.data.local.dao._manager.InterfaceDao;
import com.voobly.ratings.data.model.Ladder;

import java.util.ArrayList;
import java.util.List;

import static com.voobly.ratings.data.local.DBContract.Ladders.COLUMN_DISPLAY_NAME;
import static com.voobly.ratings.data.local.DBContract.Ladders.COLUMN_ID;
import static com.voobly.ratings.data.local.DBContract.Ladders.COLUMN_LOBBY;
import static com.voobly.ratings.data.local.DBContract.Ladders.COLUMN_LOSSES;
import static com.voobly.ratings.data.local.DBContract.Ladders.COLUMN_RANK;
import static com.voobly.ratings.data.local.DBContract.Ladders.COLUMN_RATING;
import static com.voobly.ratings.data.local.DBContract.Ladders.COLUMN_STREAK;
import static com.voobly.ratings.data.local.DBContract.Ladders.COLUMN_UID;
import static com.voobly.ratings.data.local.DBContract.Ladders.COLUMN_WINS;

/**
 * Created by jvazquez on 03/05/2017.
 */

public class LadderDao extends InterfaceDao<Ladder,Integer> {


    public LadderDao(){
        super();
        TABLE_NAME = DBContract.Ladders.TABLE_NAME;
        primaryKey = COLUMN_ID;
    }
    @Override
    public int insertObject(Ladder object) {
        return insert(TABLE_NAME, getContentValues(object));
    }

    @Override
    public int insertObjects(List<Ladder> objects) {
        if(objects != null && objects.size() > 0){
            delete(TABLE_NAME, null);
            for(Ladder object: objects){
                insert(TABLE_NAME, getContentValues(object));
            }
        }
        return 0;
    }

    @Override
    public Ladder getObjectByPrimaryKey(Integer integer) {
        open();
        Cursor queryResult = query(TABLE_NAME, this.primaryKey + " = " + integer);
        Ladder ladder = new Ladder();
        if(queryResult.moveToFirst()){
            do{
                ladder.setId(queryResult.getString(queryResult.getColumnIndex(COLUMN_ID)));
                ladder.setLobby(queryResult.getString(queryResult.getColumnIndex(COLUMN_LOBBY)));
                ladder.setRank(queryResult.getString(queryResult.getColumnIndex(COLUMN_RANK)));
                ladder.setUid(queryResult.getString(queryResult.getColumnIndex(COLUMN_UID)));
                ladder.setDisplay_name(queryResult.getString(queryResult.getColumnIndex(COLUMN_DISPLAY_NAME)));
                ladder.setRating(queryResult.getString(queryResult.getColumnIndex(COLUMN_RATING)));
                ladder.setWins(queryResult.getString(queryResult.getColumnIndex(COLUMN_WINS)));
                ladder.setLosses(queryResult.getString(queryResult.getColumnIndex(COLUMN_LOSSES)));
                ladder.setStreak(queryResult.getString(queryResult.getColumnIndex(COLUMN_STREAK)));
            }while (queryResult.moveToNext());
        }
        close();
        return ladder;
    }

    @Override
    public List<Ladder> getObjects() {
        List<Ladder> ladders = new ArrayList<>();
        open();
        Cursor queryResult = query(TABLE_NAME, null);
        Ladder ladder;
        if(queryResult.moveToFirst()){
            do{
                ladder = new Ladder();
                ladder.setId(queryResult.getString(queryResult.getColumnIndex(COLUMN_ID)));
                ladder.setLobby(queryResult.getString(queryResult.getColumnIndex(COLUMN_LOBBY)));
                ladder.setRank(queryResult.getString(queryResult.getColumnIndex(COLUMN_RANK)));
                ladder.setUid(queryResult.getString(queryResult.getColumnIndex(COLUMN_UID)));
                ladder.setDisplay_name(queryResult.getString(queryResult.getColumnIndex(COLUMN_DISPLAY_NAME)));
                ladder.setRating(queryResult.getString(queryResult.getColumnIndex(COLUMN_RATING)));
                ladder.setWins(queryResult.getString(queryResult.getColumnIndex(COLUMN_WINS)));
                ladder.setLosses(queryResult.getString(queryResult.getColumnIndex(COLUMN_LOSSES)));
                ladder.setStreak(queryResult.getString(queryResult.getColumnIndex(COLUMN_STREAK)));
                ladders.add(ladder);
            }while (queryResult.moveToNext());
        }
        return ladders;
    }
    public List<Ladder> findLadders(String idLadder){
        List<Ladder> ladderList = new ArrayList<>();
        open();
        Cursor queryResult = query(TABLE_NAME, this.primaryKey + " = " + idLadder);
        Ladder ladder;
        if(queryResult.moveToFirst()){
            do{
                ladder = new Ladder();
                ladder.setId(queryResult.getString(queryResult.getColumnIndex(COLUMN_ID)));
                ladder.setLobby(queryResult.getString(queryResult.getColumnIndex(COLUMN_LOBBY)));
                ladder.setRank(queryResult.getString(queryResult.getColumnIndex(COLUMN_RANK)));
                ladder.setUid(queryResult.getString(queryResult.getColumnIndex(COLUMN_UID)));
                ladder.setDisplay_name(queryResult.getString(queryResult.getColumnIndex(COLUMN_DISPLAY_NAME)));
                ladder.setRating(queryResult.getString(queryResult.getColumnIndex(COLUMN_RATING)));
                ladder.setWins(queryResult.getString(queryResult.getColumnIndex(COLUMN_WINS)));
                ladder.setLosses(queryResult.getString(queryResult.getColumnIndex(COLUMN_LOSSES)));
                ladder.setStreak(queryResult.getString(queryResult.getColumnIndex(COLUMN_STREAK)));
                ladderList.add(ladder);
            }while (queryResult.moveToNext());
        }
        return ladderList;

    }
    @Override
    public List<Ladder> findObjects(String query, String... columns) {
        return null;
    }

    @Override
    public int updateObject(Ladder object) {
        return 0;
    }

    @Override
    public int updateObjects(List<Ladder> objects) {
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
    protected ContentValues getContentValues(Ladder object) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID          , object.getId());
        values.put(COLUMN_LOBBY       , object.getLobby());
        values.put(COLUMN_RANK        , object.getRank());
        values.put(COLUMN_UID         , object.getUid());
        values.put(COLUMN_DISPLAY_NAME, object.getDisplay_name());
        values.put(COLUMN_RATING      , object.getRating());
        values.put(COLUMN_WINS        , object.getWins());
        values.put(COLUMN_LOSSES      , object.getLosses());
        values.put(COLUMN_STREAK      , object.getStreak());
        return values;
    }

    @Override
    public void onDestroy() {
        close();
    }
}
