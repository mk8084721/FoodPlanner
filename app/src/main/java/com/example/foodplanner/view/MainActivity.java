package com.example.foodplanner.view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodplanner.R;
import com.example.foodplanner.database.AppDataBase;
import com.example.foodplanner.database.FoodPlannerDao;
import com.example.foodplanner.model.User;

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
        AppDataBase dataBase = AppDataBase.getInstance(this);
        new Thread(){
            @Override
            public void run() {
                User user = new User("m@k.com","mohamed","11");
                user.setId(2L);
                user.setEmail("a@k.com");
                dataBase.getFoodPlannerDao().updateUser(user);
                user.setId(3L);
                user.setEmail("b@k.com");
                dataBase.getFoodPlannerDao().updateUser(user);

            }
        }.start();

    }
}