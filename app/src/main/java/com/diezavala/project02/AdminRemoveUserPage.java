package com.diezavala.project02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView userDisplay;
    Button backButton;
    Button searchButton;
    Button removeButton;
    private static final String USER_ID_KEY = "com.diezavala.project02.userIdKey";
    users user;
    users selectedUser;
    private static final String PREFERENCES_KEY = "com.diezavala.project02.PREFERENCES_KEY";
    private int userId = -1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.user_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.buttons:
                Toast.makeText(this, "More User Options Selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logout:
                Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show();
                Intent logOutIntent = LogInPage.intentFactory(getApplicationContext());
                startActivity(logOutIntent);
                return true;
            case R.id.welcome:
                Toast.makeText(this, "Going to Welcome Page", Toast.LENGTH_SHORT).show();
                Intent welcomeIntent = WelcomeUserActivity.intentFactory(getApplicationContext(), userId);
                startActivity(welcomeIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

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
        userDisplay = binding.selectedUserDisplay;

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
                String username = enterUsername.getText().toString();
                selectedUser = userDAO.getUserByUsername(username);
                if(selectedUser != null){
                    userDisplay.setText(selectedUser.toString());
                } else{
                    userDisplay.setText("No User: "+enterUsername.getText().toString());
                }
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedUser == null){
                    userDisplay.setText("Please Select Valid User");
                } else{
                    userDAO.delete(selectedUser);
                    userDisplay.setText("User Deleted");
                }
            }
        });

    }
    public static Intent adminRemoveIntent(Context context, int userId) {
        Intent intent = new Intent(context, AdminRemoveUserPage.class);
        intent.putExtra(USER_ID_KEY, userId);

        return intent;
    }
}