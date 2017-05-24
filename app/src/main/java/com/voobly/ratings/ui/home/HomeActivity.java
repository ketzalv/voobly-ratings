package com.voobly.ratings.ui.home;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;

import com.voobly.ratings.R;
import com.voobly.ratings.data.DataManagerEvent;
import com.voobly.ratings.data.model.Lobby;
import com.voobly.ratings.ui.base.factoryactivities.BaseActivity;
import com.voobly.ratings.ui.home.ChatFragment.ChatFragment;
import com.voobly.ratings.ui.home.twitch.TwitchFragment;
import com.voobly.ratings.ui.ladders.ListLadderFragment;
import com.voobly.ratings.ui.lobbies.ListLobbyFragment;
import com.voobly.ratings.ui.rank.RankFragment;
import com.voobly.ratings.utils.CustomTypefaceSpan;

import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_GO_RANKING;
import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_LADDER_ELEMENT;
import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_LOBBY_ELEMENT;

public class HomeActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private HomeFragment homeFragment;
    private TwitchFragment twitchFragment;
    private ChatFragment chatFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setIdsContainers(R.id.coordinatorContainer, R.id.fragmentContainer, R.id.progressView, R.id.errorView, R.id.fragmentContainer);
        showMainViewActivity();
        initFragments();
        loadFragment(homeFragment);
        //loadFragment(HomeFragment.newInstance());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        setTypeFace(navigation);
    }

    private void initFragments() {
        homeFragment = HomeFragment.newInstance();
        twitchFragment = TwitchFragment.newInstance();
        chatFragment = ChatFragment.newInstance();
    }

    @Override
    public void onEvent(DataManagerEvent data) {
        super.onEvent(data);
        switch (data.getEvent()){
            case EVENT_LOBBY_ELEMENT:
                if(data.getData() instanceof Lobby){
                    loadFragment(ListLadderFragment.newInstance((Lobby) data.getData()), true);
                }else{
                    throw new RuntimeException("need lobby");
                }
                break;
            case EVENT_LADDER_ELEMENT:
                if(data.getData() instanceof String){
                    loadFragment(RankFragment.newInstance((String) data.getData()), true);
                }else{
                    throw new RuntimeException("need id ladder");
                }
                break;
            case EVENT_GO_RANKING:
                BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
                loadFragment(ListLobbyFragment.newInstance(), DIRECTION.FADE, true);
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                loadFragment(homeFragment, DIRECTION.FADE);
                return true;
            case R.id.navigation_twitch:
                loadFragment(twitchFragment, DIRECTION.FADE);
                return true;
            case R.id.navigation_chat:
                loadFragment(chatFragment, DIRECTION.FADE);
                return true;
        }
        return false;
    }
    private void setTypeFace(BottomNavigationView navigationView){
        Menu menu = navigationView.getMenu();
        for(int i = 0; i < menu.size(); i++){
            MenuItem item = menu.getItem(i);
            applyFont(item);
        }
    }

    private void applyFont(MenuItem item) {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/comfortaa/ComfortaaRegular.ttf");
        SpannableString mNewTitle = new SpannableString(item.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        item.setTitle(mNewTitle);
    }
}
