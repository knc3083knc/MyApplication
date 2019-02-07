package com.example.windows.myapplication.fragment;

import android.content.Context;
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
import android.widget.Toast;

import com.example.windows.myapplication.R;
import com.example.windows.myapplication.dao.AppProfile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("unused")
public class SignupFragment extends Fragment {

    EditText editEmail;
    EditText editFname;
    EditText editLname;
    EditText editPass;
    Button btnSignup;
    onSignup onSignup;
    //firebase
    FirebaseAuth firebaseAuth;
    FirebaseFirestore db ;


    public SignupFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static SignupFragment newInstance() {
        SignupFragment fragment = new SignupFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onSignup = (onSignup) context;
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
        View rootView = inflater.inflate(R.layout.fragment_signup, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here

        editEmail = (EditText) rootView.findViewById(R.id.signupEmail);
        editFname = (EditText) rootView.findViewById(R.id.signupFname);
        editLname = (EditText) rootView.findViewById(R.id.signupLname);
        editPass = (EditText) rootView.findViewById(R.id.signupPass);
        btnSignup = (Button) rootView.findViewById(R.id.btn_signup);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editEmail.getText().toString().trim().length()==0)
                {
                    editEmail.setError("Please Enter Email");
                }
                if(editPass.getText().toString().trim().length()==0)
                {
                    editPass.setError("Please Enter Password");
                }
                if(editFname.getText().toString().trim().length()==0)
                {
                    editFname.setError("Please Enter First Name");
                }
                if(editLname.getText().toString().trim().length()==0)
                {
                    editLname.setError("Please Enter Last Name");
                }
                if(editFname.getText().toString().trim().length()>0
                        &&editLname.getText().toString().trim().length()>0
                        &&editPass.getText().toString().trim().length()>0
                        &&editEmail.getText().toString().trim().length()>0)
                {
                    signupFirebase();
                }
            }
        });


    }

    private void signupFirebase() {
        firebaseAuth.createUserWithEmailAndPassword(editEmail.getText().toString(),editPass.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        AppProfile profile = new AppProfile();
                        profile.setEmail(editEmail.getText().toString().trim());
                        profile.setPass(editPass.getText().toString().trim());
                        profile.setFname(editFname.getText().toString().trim());
                        profile.setLname(editLname.getText().toString().trim());
                        db.collection("AppProfile").document(editEmail.getText().toString().trim()).set(profile);
                        onSignup.onSignupSuccess(true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
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

    public interface onSignup
    {
        public void onSignupSuccess(boolean bool);
    }

}
