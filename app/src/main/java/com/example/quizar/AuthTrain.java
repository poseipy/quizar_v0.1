package com.example.quizar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthTrain extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText Email, Password;
    private Button loginButton;
    private ProgressBar loginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_train);

        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginProcess();
            }
        });
    }

    private void loginProcess(){
        String email, password;
        email = Email.getText().toString();
        password = Password.getText().toString();

        loginProgress.setVisibility(View.VISIBLE);

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            loginProgress.setVisibility(View.GONE);
                            Toast.makeText(AuthTrain.this, "Login Successful", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(AuthTrain.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            loginProgress.setVisibility(View.GONE);
                            Toast.makeText(AuthTrain.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}
