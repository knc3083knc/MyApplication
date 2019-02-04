package com.example.windows.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.windows.myapplication.R;
import com.example.windows.myapplication.dao.DailyAPOD;


@SuppressWarnings("unused")
public class InfoAPODFragment extends Fragment {

    DailyAPOD apod;
    TextView tvApodExplan;
    TextView tvApodCopy;
    ImageView ivApod;
    public InfoAPODFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static InfoAPODFragment newInstance(DailyAPOD apod) {
        InfoAPODFragment fragment = new InfoAPODFragment();
        Bundle args = new Bundle();
        args.putParcelable("apod",apod);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apod = getArguments().getParcelable("apod");
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_apod_info, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        tvApodCopy = rootView.findViewById(R.id.tv_apodcopyright);
        tvApodExplan = rootView.findViewById(R.id.tv_apodexplan);
        ivApod =rootView.findViewById(R.id.iv_apod_info);
        Glide.with(getContext())
                .load(apod.getUrl())
                .apply(new RequestOptions().placeholder(R.drawable.placehold))
                .into(ivApod);
        tvApodCopy.setText("Copyright : "+apod.getCopyright());
        tvApodExplan.setText("Explanation : "+apod.getExplanation());
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
