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
        AppDataBase dataBase = AppDataBase.getInstance(this);
        FoodPlannerDao dao = dataBase.getFoodPlannerDao();
        new Thread(){
            @Override
            public void run() {
                User user = new User("ahmed@k.com","11");
                user.setId(2L);
                user.setEmail("m@k.com");
                dao.updateUser();
                user.setId(3L);
                user.setEmail("a@k.com");
                dao.updateUser();
                user.setId(4L);
                user.setEmail("b@k.com");
                dao.updateUser();



            }
        }.start();

    }
}