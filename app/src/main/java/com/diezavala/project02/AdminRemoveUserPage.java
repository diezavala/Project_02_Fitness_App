package com.diezavala.project02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.diezavala.project02.DB.AppDataBase;
import com.diezavala.project02.DB.UserDAO;
import com.diezavala.project02.databinding.ActivityAdminRemoveUserPageBinding;
import com.diezavala.project02.databinding.ActivityAdminViewUserPageBinding;

import java.util.List;

public class AdminRemoveUserPage extends AppCompatActivity {

    private ActivityAdminRemoveUserPageBinding binding;

    UserDAO userDAO;

    List<users> usersList;
    EditText enterUsername;
    Button backButton;
    Button searchButton;
    Button removeButton;
    private static final String USER_ID_KEY = "com.diezavala.project02.userIdKey";
    users user;
    private static final String PREFERENCES_KEY = "com.diezavala.project02.PREFERENCES_KEY";
    private int userId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_remove_user_page);

        binding = ActivityAdminRemoveUserPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        enterUsername= binding.searchUsers;
        backButton = binding.removeToHub;
        searchButton = binding.searchButton;
        removeButton = binding.removeUserButton;

        userDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .getDAO();
        userId = getIntent().getIntExtra(USER_ID_KEY, -1);
        user = userDAO.getUserByUSerId(userId);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = AdminHub.adminHubIntent(getApplicationContext(), user.getLogId());
                startActivity(i);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: make button find user by username from enterUsername edit text
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Make button deactivate selected user
            }
        });

    }
    public static Intent adminRemoveIntent(Context context, int userId) {
        Intent intent = new Intent(context, AdminRemoveUserPage.class);
        intent.putExtra(USER_ID_KEY, userId);

        return intent;
    }
}