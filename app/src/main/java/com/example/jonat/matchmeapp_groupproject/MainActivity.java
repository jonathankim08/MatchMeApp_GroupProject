package com.example.jonat.matchmeapp_groupproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

//Login Page

public class MainActivity extends Activity implements View.OnClickListener{

    private Button loginButton, registerButton;
    private EditText usernameEditText, passwordEditText;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerButton);
        usernameEditText = (EditText)findViewById(R.id.usernameEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);

        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }



    @Override
    public void onClick(View view) {

        // catch the values in edittext
        String email = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Figureout which button has been clicked
        if (view == loginButton){
            //call signin method
            signIn(email, password);
            //sendToHome();

        } else if (view == registerButton){
            createAccount(email, password);
            //sendToRegistration();
        }

    }


    private void createAccount(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // If signin fails, display a message to the user. If signin succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Registration Failed",
                                    Toast.LENGTH_SHORT).show();
                            usernameEditText.setText("");
                            passwordEditText.setText("");

                        } else {
                            Toast.makeText(MainActivity.this, "Registration Successful",
                                    Toast.LENGTH_SHORT).show();

                            sendToRegistration();
                        }

                    }
                });

    }

    private void sendToRegistration() {
        Intent RegistrationIntent = new Intent(this, RegistrationActivity.class);
        RegistrationIntent.putExtra("Username", usernameEditText.getText().toString());
        this.startActivity(RegistrationIntent);
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)  {

                        // If signin fails, display a message to the user. If signin succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {

                            usernameEditText.setText("");
                            passwordEditText.setText("");

                            Toast.makeText(MainActivity.this, "Authentication Failed",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(MainActivity.this, "Authentication Successful",
                                    Toast.LENGTH_SHORT).show();
                            sendToHome();
                        }

                    }
                });

    }

    private void sendToHome() {
        Intent HomeIntent = new Intent(this, HomepageActivity.class);
        HomeIntent.putExtra("Username", usernameEditText.getText().toString());
        this.startActivity(HomeIntent);
    }
}