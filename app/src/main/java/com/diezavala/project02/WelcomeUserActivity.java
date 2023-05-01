package com.diezavala.project02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.diezavala.project02.DB.AppDataBase;
import com.diezavala.project02.DB.UserDAO;
import com.diezavala.project02.databinding.ActivityMainBinding;
import com.diezavala.project02.databinding.ActivityWelcomeUserBinding;

import java.util.List;

public class WelcomeUserActivity extends AppCompatActivity {

//    ActivityWelcomeUserBinding binding;
    Button returnButton;
    TextView welcomeUser;
    UserDAO userDAO;
    private static final String USER_ID_KEY = "com.diezavala.project02.userIdKey";
    private static final String PREFERENCES_KEY = "com.diezavala.project02.PREFERENCES_KEY";

    private int userId = -1;

    public users user;
    public users user1 = new users("user1", "user1", 0);
    public users admin1 = new users("admin1", "password1", 1);
    ActivityWelcomeUserBinding binding;
    Button goToGymLog;
    Button goToAdminPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_user);
        binding = ActivityWelcomeUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        goToGymLog = binding.goToGymLogPage;
        goToAdminPage = binding.goToAdminPage;
        welcomeUser = binding.welcomeUserTextView;

//        returnButton = binding.returnBackButton;

        getDatabase();
        checkForUser();
        isAdmin();
        welcomeUser.setText("Hello " + user.getUsername());


        goToGymLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeUserActivity.this, GymLogPage.class));
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
            users adminUser = new users("admin2","admin2", 1);
            userDAO.insert(adminUser);
            userDAO.insert(user1);
            userDAO.insert(admin1);
        }
//        Intent intent = LogInPage.intentFactory(this);
//        startActivity(intent);
    }

    public static Intent intentFactory(Context context, int userId){
        Intent intent = new Intent(context, WelcomeUserActivity.class);
        intent.putExtra(USER_ID_KEY, userId);

        return intent;
    }

    public void isAdmin(){
        user = userDAO.getUserByUSerId(userId);
        if(user.getIsAdmin() == 1){
            goToAdminPage.setVisibility(View.VISIBLE);
        }
    }


}