package com.example.windows.myapplication.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.desarrollodroide.libraryfragmenttransactionextended.FragmentTransactionExtended;
import com.example.windows.myapplication.R;
import com.example.windows.myapplication.fragment.LoginFragment;
import com.example.windows.myapplication.fragment.SignupFragment;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnDataPass ,SignupFragment.onSignup{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.login_container,LoginFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onCreateAccClicked(boolean clicked) {
        if(clicked)
        {
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.login_container,SignupFragment.newInstance())
                    .commit();

        }
    }

    @Override
    public void onSignupSuccess(boolean success) {
        if(success)
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.login_container,LoginFragment.newInstance())
                    .commit();
        }
    }
}
