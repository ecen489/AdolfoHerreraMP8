package com.example.mp82;

import android.content.Intent;
import android.provider.Contacts;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText editUsername, editPasswordText;
    private Button loginButton, signUpButton;
    private FirebaseAuth mAuth;

    private FirebaseUser user = null;

    Intent ShopIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpInterface();

        mAuth = FirebaseAuth.getInstance();

        ShopIntent = new Intent(this, UserLoginActivity.class);
    }


    private void setUpInterface() {
        editUsername = findViewById(R.id.userName);
        editPasswordText = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.login);
        signUpButton = findViewById(R.id.signUp);
    }

    public void loginUser(View v) {
        String username = editUsername.getText().toString().trim();
        String password = editPasswordText.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Login Successfull!",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(user);

                            startActivity(ShopIntent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void createUser(View v) {
        String username = editUsername.getText().toString().trim();
        String password = editPasswordText.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Sign in success, update Contacts.Intents.UI with the signed-in user's information
                            //Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(MainActivity.this, "SUCCESS.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(ShopIntent);
                            finish();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }
}
