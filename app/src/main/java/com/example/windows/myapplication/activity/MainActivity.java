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
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows.myapplication.R;
import com.example.windows.myapplication.dao.DailyAPOD;
import com.example.windows.myapplication.fragment.Fragment2;
import com.example.windows.myapplication.fragment.LoginFragment;
import com.example.windows.myapplication.fragment.MainFragment;
import com.example.windows.myapplication.manager.ApiService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
    FirebaseAuth firebaseAuth;
    ProgressBar pbApod;
    ApiService apiService;
    Retrofit retrofit;
    DailyAPOD apod;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        tvTest = (TextView) findViewById(R.id.tvTest);
        init();

        if (firebaseAuth.getCurrentUser() != null) {
            tvTest.setText("WELCOME ! \n" + firebaseAuth.getCurrentUser().getEmail());
            getSupportActionBar().setTitle(R.string.apod);
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
                }

                @Override
                public void onFailure(Call<DailyAPOD> call, Throwable t) {
                    Toast.makeText(MainActivity.this, t.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            });


        }


    }

    private void init() {

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
                if(item.getItemId() == R.id.itemApod)
                {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, MainFragment.newInstance(apod))
                            .commit();
                }
                else if(item.getItemId() == R.id.item2)
                {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, Fragment2.newInstance())
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
