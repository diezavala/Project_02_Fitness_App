package com.diezavala.project02;



import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.diezavala.project02.users;
import com.diezavala.project02.DB.AppDataBase;
import com.diezavala.project02.DB.UserDAO;
import com.diezavala.project02.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
//adding this to test github

    private static final String USER_ID_KEY = "com.diezavala.project02.userIdKey";
    private static final String PREFERENCES_KEY = "com.diezavala.project02.PREFERENCES_KEY";

    private int userId = -1;

    UserDAO userDAO;
    ActivityMainBinding binding;
    Button logIn;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        logIn = binding.logInButton;
        create = binding.createAccountButton;
        getDatabase();
//        checkForUser();
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkForUser();
                startActivity(new Intent(MainActivity.this, LogInPage.class));
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateAccountPage.class));
            }
        });
    }

    private void getDatabase() {
        userDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build().getDAO();
    }

    private void checkForUser() {
        userId = getIntent().getIntExtra(USER_ID_KEY, -1);

        if(userId != -1){
            return;
        }
        SharedPreferences preferences = this.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);

        userId = preferences.getInt(USER_ID_KEY, -1);

        if(userId != -1){
            return;
        }

        List<users> users = userDAO.getALlUsers();
        if(users.size() <= 0){
            users defaultUser = new users("diegoz","diego123", 0);
            userDAO.insert(defaultUser);
            users adminUser = new users("admin1","admin1", 1);
            userDAO.insert(adminUser);
        }
//        Intent intent = LogInPage.intentFactory(this);
//        startActivity(intent);
    }
}