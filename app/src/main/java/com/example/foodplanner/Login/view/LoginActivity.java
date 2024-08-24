package com.example.foodplanner.Login.view;

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

import com.example.foodplanner.R;
import com.example.foodplanner.Register.view.RegisterActivity;
import com.example.foodplanner.Login.presenter.LoginPresenter;
import com.example.foodplanner.WeekPlan.model.PlanMeal;
import com.example.foodplanner.database.AppDataBase;
import com.example.foodplanner.database.LocalRepoImpl;
import com.example.foodplanner.view.HomeActivity;
import com.example.foodplanner.unUsed.ILogin;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity implements ILogin {
    private static final int RC_SIGN_IN = 123;
    private GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth mAuth;
    EditText emailTxt;
    EditText passwordTxt;
    LoginPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        presenter= new LoginPresenter(this , LocalRepoImpl.getInstance(this));


        String email = presenter.readSharedPreferance(this);
        if(email!=null){
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("userEmail",email);
            startActivity(intent);
        }else {
            Log.i("JTAG", "onCreate: ");
            presenter.insertEmptyPlanDays();
        }

        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();

        Button loginBtn = findViewById(R.id.loginBtn);
        Button registerBtn = findViewById(R.id.registerBtn);
        Button googleLoginBtn = findViewById(R.id.googleLoginBtn);
        emailTxt= findViewById(R.id.emailTxt);
        passwordTxt= findViewById(R.id.passwordTxt);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
        // Configure Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.google_id_from)) // Ensure this matches with your Firebase project
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        googleLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInWithGoogle();
            }
        });

    }
    private void loginUser(){
        String email = emailTxt.getText().toString();
        String passwod = passwordTxt.getText().toString();
        if (TextUtils.isEmpty(email)){
            emailTxt.setError("Email Can't Be Empty");
            emailTxt.requestFocus();
        }else if (TextUtils.isEmpty(passwod)){
            passwordTxt.setError("Password Can't Be Empty");
            passwordTxt.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,passwod).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        presenter.writeInSharedPreferance(LoginActivity.this,1,email);
                        presenter.insertEmptyPlanDays();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("userEmail",email);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this,"User Login Successfully",Toast.LENGTH_SHORT).show();
                        Log.i("TAG", "onComplete: "+task.getResult().getUser().getUid());
                        Log.i("TAG", "onComplete: "+task.getResult().getUser().getEmail());

                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }else {
                        Toast.makeText(LoginActivity.this,"Login Error : "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void signInWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    firebaseAuthWithGoogle(account.getIdToken());
                }
            } catch (ApiException e) {
                Toast.makeText(this, "Google sign in failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            String email = user.getEmail();
                            presenter.writeInSharedPreferance(LoginActivity.this, 1, email);
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            intent.putExtra("userEmail", email);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "Google sign-in successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}