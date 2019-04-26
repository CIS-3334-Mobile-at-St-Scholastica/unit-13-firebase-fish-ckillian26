package edu.css.unit13_firebasefish_2018;

import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    Button btnCreate, btnLogin;
    EditText etEmail, etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.editTextEmail);
        etPassword = (EditText) findViewById(R.id.editTextPassword);

        // set up the create account button listener
        btnCreate = (Button) findViewById(R.id.buttonCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String password = etPassword.getText().toString();
                String email = etEmail.getText().toString();
                createAccount(email, password);
            }
        });
        // set up the login button listener
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String password = etPassword.getText().toString();
                String email = etEmail.getText().toString();
                signIn(email, password);

            }
        });
    }
    private void signIn(String email, String password){
        //sign in the recurrent user with email and password previously created.
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() { //add to listener
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) { //when failed
                    Toast.makeText(LoginActivity.this, "SignIn--Authentication failed.",Toast.LENGTH_LONG).show();
                } else {
                    //return to MainActivity is login works
                    finish();
                }
            }
        });
    }

    private void createAccount(String email, String password) {
        //create account for new users
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {  //update listener.
                if (!task.isSuccessful()) { //when failed
                    Toast.makeText(LoginActivity.this, "createAccount--Authentication failed.", Toast.LENGTH_LONG).show();
                } else {
                    //return to MainActivity is login works
                    finish();
                }
            }
        });
    }
}
