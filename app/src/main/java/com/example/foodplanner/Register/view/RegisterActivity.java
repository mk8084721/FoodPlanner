package com.example.foodplanner.Register.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodplanner.Login.view.LoginActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.Register.Repo.RegisterRepoImpl;
import com.example.foodplanner.Register.presenter.RegisterPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText emailTxt;
    EditText passwordTxt;
    RegisterPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        presenter = new RegisterPresenter(RegisterRepoImpl.getInstance(getBaseContext()));
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        Button registerBtn = findViewById(R.id.register);
        emailTxt = findViewById(R.id.registerEmailTxt);
        passwordTxt = findViewById(R.id.registerPasswordTxt);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser(emailTxt.getText().toString(),passwordTxt.getText().toString());
            }
        });


    }

    private void createUser(String email, String passwod){
        if (TextUtils.isEmpty(email)){
            emailTxt.setError("Email Can't Be Empty");
            emailTxt.requestFocus();
        }else if (TextUtils.isEmpty(passwod)){
            passwordTxt.setError("Email Can't Be Empty");
            passwordTxt.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,passwod).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Log.i("Register", "Register Activity: email : "+email);
                        Toast.makeText(RegisterActivity.this,"User Register Successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }else {
                        Toast.makeText(RegisterActivity.this,"Register Error : "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}