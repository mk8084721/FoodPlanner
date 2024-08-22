package com.example.foodplanner.unUsed;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterFragment extends Fragment {

    FirebaseAuth mAuth;
    EditText emailTxt;
    EditText passwordTxt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button registerBtn = view.findViewById(R.id.register);
        emailTxt = view.findViewById(R.id.registerEmailTxt);
        passwordTxt = view.findViewById(R.id.registerPasswordTxt);

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
                        Toast.makeText(getContext(),"User Register Successfully",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(),"Register Error : "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}