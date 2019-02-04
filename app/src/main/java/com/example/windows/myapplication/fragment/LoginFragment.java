package com.example.windows.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows.myapplication.R;
import com.example.windows.myapplication.activity.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


@SuppressWarnings("unused")
public class LoginFragment extends Fragment {

    //View
    EditText emailLogin;
    EditText passLogin;
    Button btnLogin;
    TextView tvCreateNewAcc;

    ProgressBar progressBar;

    //Interface
    OnDataPass onDataPass;

    //Firebase
    FirebaseAuth firebaseAuth;

    public LoginFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onDataPass = (OnDataPass) context;
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
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(final View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        emailLogin = (EditText) rootView.findViewById(R.id.log_email);
        passLogin = (EditText) rootView.findViewById(R.id.log_pass);
        btnLogin = (Button) rootView.findViewById(R.id.login_btn);
        tvCreateNewAcc = (TextView) rootView.findViewById(R.id.tv_create);

        progressBar = (ProgressBar) rootView.findViewById(R.id.pbLogin);

        firebaseAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailLogin.getText().toString().trim().length()==0)
                {
                    emailLogin.setError("Please Enter Email");
                }
                if(passLogin.getText().toString().trim().length()==0)
                {
                    passLogin.setError("Please Enter Password");
                }
                if(emailLogin.getText().toString().trim().length()>0&&passLogin.getText().toString().trim().length()>0)
                {
                    progressBar.setVisibility(View.VISIBLE);
                    signinFirebase();
                }

            }
        });

        tvCreateNewAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LoginFrag", "CLICKED");
                onDataPass.onCreateAccClicked(true);
            }
        });

    }

    private void signinFirebase() {
        firebaseAuth.signInWithEmailAndPassword(emailLogin.getText().toString(), passLogin.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        progressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        getActivity().startActivity(intent);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
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

    public interface OnDataPass {
        public void onCreateAccClicked(boolean clicked);
    }
}
