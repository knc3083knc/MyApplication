package com.example.windows.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.windows.myapplication.R;
import com.example.windows.myapplication.dao.DailyAPOD;
import com.example.windows.myapplication.manager.ApiService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@SuppressWarnings("unused")
public class MainApodFragment extends Fragment {


    ImageView ivApod;
    TextView tvTitle;
    TextView tvDate;
    DailyAPOD apod;

    public MainApodFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static MainApodFragment newInstance(DailyAPOD apod) {
        MainApodFragment fragment = new MainApodFragment();
        Bundle args = new Bundle();
        args.putParcelable("apod",apod);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apod = getArguments().getParcelable("apod");
        if(apod ==null)
        {
            apod = new DailyAPOD();
        }


        init(savedInstanceState);
        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_apod_main, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
        tvDate = (TextView) rootView.findViewById(R.id.tv_date);
        ivApod = (ImageView) rootView.findViewById(R.id.iv_apod);

        Glide.with(getContext())
                .load(apod.getHdUrl())
                .apply(new RequestOptions().placeholder(R.drawable.placehold))
                .into(ivApod);
        tvTitle.setText(apod.getTitle());
        tvDate.setText(apod.getDate());
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
