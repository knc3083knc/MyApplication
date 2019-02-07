package com.example.windows.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.windows.myapplication.R;
import com.example.windows.myapplication.adapter.FavMusicListAdapter;
import com.example.windows.myapplication.manager.DBHelper;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("unused")
public class FavMusicFragment extends Fragment {
    FavMusicListAdapter favMusicListAdapter;
    ListView favMusicListView;
    List<String> favMusicList;
    DBHelper mHelper;

    public FavMusicFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static FavMusicFragment newInstance() {
        FavMusicFragment fragment = new FavMusicFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fav_music, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        favMusicListAdapter = new FavMusicListAdapter();
        favMusicListView = rootView.findViewById(R.id.listFavMusic);
        favMusicList = new ArrayList<String>();
        mHelper = new DBHelper(getContext());
        favMusicList = mHelper.getFavMusicList();
        favMusicListAdapter.setFavList(favMusicList);
        favMusicListView.setAdapter(favMusicListAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

}
