package com.voobly.ratings.ui.home.twitch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.voobly.ratings.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwitchFragment extends Fragment {

    public static TwitchFragment newInstance() {

        Bundle args = new Bundle();

        TwitchFragment fragment = new TwitchFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public TwitchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_twitch, container, false);
    }

}
