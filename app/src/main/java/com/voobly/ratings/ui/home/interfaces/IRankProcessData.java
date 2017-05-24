package com.voobly.ratings.ui.home.interfaces;

import com.voobly.ratings.data.model.Ladder;
import com.voobly.ratings.ui.base.interfaces.IProcessData;

import java.util.List;

/**
 * Created by jvazquez on 02/05/2017.
 */

public interface IRankProcessData extends IProcessData {
    void getLobbies();

    void getLadder(String ladder);
    List<String> findLadders(List<String> ladders);

    List<Ladder> getLaddersById(String id);
}
