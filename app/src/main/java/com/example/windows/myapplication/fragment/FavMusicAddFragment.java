package com.example.windows.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.windows.myapplication.R;
import com.example.windows.myapplication.dao.FavMusic;
import com.example.windows.myapplication.manager.DBHelper;


@SuppressWarnings("unused")
public class FavMusicAddFragment extends Fragment {

    EditText editMusic;
    EditText editArtist;
    EditText editAlbum;
    EditText editDescription;
    Button btnAddFavMusic;
    DBHelper dbHelper;

    public FavMusicAddFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static FavMusicAddFragment newInstance() {
        FavMusicAddFragment fragment = new FavMusicAddFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_fav_music_add, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        dbHelper = new DBHelper(getContext());
        editMusic = rootView.findViewById(R.id.editMusic);
        editArtist = rootView.findViewById(R.id.editArtist);
        editAlbum = rootView.findViewById(R.id.editAlbum);
        editDescription = rootView.findViewById(R.id.editDescription);
        btnAddFavMusic = rootView.findViewById(R.id.btn_favMusicAdd);
        btnAddFavMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavMusic favMusic = new FavMusic();
                favMusic.setMusicName(editMusic.getText().toString());
                favMusic.setArtist(editArtist.getText().toString());
                favMusic.setAlbum(editAlbum.getText().toString());
                favMusic.setDescription(editDescription.getText().toString());
                dbHelper.addFavMusic(favMusic);
                getActivity().onBackPressed();
            }
        });
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
