package com.example.windows.myapplication.activity;

import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows.myapplication.R;
import com.example.windows.myapplication.dao.AppProfile;
import com.example.windows.myapplication.dao.DailyAPOD;
import com.example.windows.myapplication.fragment.FavMusicAddFragment;
import com.example.windows.myapplication.fragment.FavMusicFragment;
import com.example.windows.myapplication.fragment.Fragment2;
import com.example.windows.myapplication.fragment.LoginFragment;
import com.example.windows.myapplication.fragment.MainFragment;
import com.example.windows.myapplication.manager.ApiService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    TextView tvTest;
    ProgressBar pbApod;
    ApiService apiService;
    Retrofit retrofit;
    DailyAPOD apod;
    NavigationView navigationView;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    boolean mState = false; // setting state

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        if (firebaseAuth.getCurrentUser() != null) {
            tvTest.setText("WELCOME ! \n" + firebaseAuth.getCurrentUser().getEmail());
        }

        if (savedInstanceState == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.nasa.gov/planetary/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiService = retrofit.create(ApiService.class);

            Call<DailyAPOD> call = apiService.getDailyAPOD("AnMiysPCGT1a0dUPuUV9AFiZfnuDNG0mChXgYbN7");
            call.enqueue(new Callback<DailyAPOD>() {
                @Override
                public void onResponse(Call<DailyAPOD> call, Response<DailyAPOD> response) {
                    apod = response.body();
                    pbApod.setVisibility(View.GONE);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, MainFragment.newInstance(apod))
                            .commit();
                }

                @Override
                public void onFailure(Call<DailyAPOD> call, Throwable t) {
                    pbApod.setVisibility(View.GONE);
                    apod = new DailyAPOD();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, MainFragment.newInstance(apod))
                            .commit();
                    Toast.makeText(MainActivity.this, t.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            });


        }


    }

    private void init() {
        firebaseAuth = FirebaseAuth.getInstance();

        firebaseFirestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firebaseFirestore.collection("AppProfile")
                .document(firebaseAuth.getCurrentUser().getEmail());

        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        AppProfile profile = documentSnapshot.toObject(AppProfile.class);
                        Log.d("AppProfile", profile.getFname() + "\n" + profile.getLname());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("AppProfile", e.getMessage().toString());

                    }
                });

        tvTest = (TextView) findViewById(R.id.tvTest);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.openmenu, R.string.closemenu);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pbApod = (ProgressBar) findViewById(R.id.pbApod);
        navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setCheckable(true);
                drawerLayout.closeDrawers();
                if (item.getItemId() == R.id.itemApod) {
                    mState = false;
                    invalidateOptionsMenu();
                    getSupportActionBar().setTitle(R.string.apod);
                    invalidateOptionsMenu();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, MainFragment.newInstance(apod))
                            .commit();
                } else if (item.getItemId() == R.id.item2) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, Fragment2.newInstance())
                            .commit();
                } else if (item.getItemId() == R.id.itemFavMusic) {
                    mState = true;
                    invalidateOptionsMenu();
                    getSupportActionBar().setTitle(R.string.favMusic);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, FavMusicFragment.newInstance())
                            .commit();
                }
                return true;
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.favmusic_menu, menu);

        if (!mState)
        {
            for (int i = 0; i < menu.size(); i++)
                menu.getItem(i).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("MenuItemClicked","ID :"+item.getTitle() +" ID2: "+R.id.itemFavMusic);
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        else if(item.getTitle().equals("Add Fav Music"))
        {
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.main_container, FavMusicAddFragment.newInstance())
                    .commit();
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}
