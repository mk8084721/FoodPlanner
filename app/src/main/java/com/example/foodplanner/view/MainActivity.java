package com.example.foodplanner.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodplanner.Login.view.LoginActivity;
import com.example.foodplanner.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getSupportActionBar().hide();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));

//        AppDataBase dataBase = AppDataBase.getInstance(this);
//        new Thread(){
//            @Override
//            public void run() {
//                User user = new User("m@k.com","mohamed","11");
//                user.setId(2L);
//                user.setEmail("a@k.com");
//                dataBase.getFoodPlannerDao().updateUser(user);
//                user.setId(3L);
//                user.setEmail("b@k.com");
//                dataBase.getFoodPlannerDao().updateUser(user);
//
//            }
//        }.start();

    }
}