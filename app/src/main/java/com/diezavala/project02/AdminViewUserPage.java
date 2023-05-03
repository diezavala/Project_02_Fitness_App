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
import android.widget.TextView;
import android.widget.Toast;

import com.diezavala.project02.DB.AppDataBase;
import com.diezavala.project02.DB.UserDAO;
import com.diezavala.project02.databinding.ActivityAdminViewUserPageBinding;

import java.util.List;

public class AdminViewUserPage extends AppCompatActivity {
    private ActivityAdminViewUserPageBinding binding;

    UserDAO userDAO;

    List<users> usersList;
    TextView usersDisplay;
    Button backButton;

    private static final String USER_ID_KEY = "com.diezavala.project02.userIdKey";
    users user;
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
        setContentView(R.layout.activity_admin_view_user_page);

        binding = ActivityAdminViewUserPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        usersDisplay= binding.viewUserDisplay;
        backButton = binding.viewToHubButton;

        userDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .getDAO();
        refreshDisplay();
        userId = getIntent().getIntExtra(USER_ID_KEY, -1);
        user = userDAO.getUserByUSerId(userId);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = AdminHub.adminHubIntent(getApplicationContext(), user.getLogId());
                startActivity(i);
            }
        });
    }


    private void refreshDisplay(){
        usersList = userDAO.getALlUsers();
        if(!usersList.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for(users user: usersList){
                sb.append(user.toString());
            }
            usersDisplay.setText(sb.toString());
        }else{
            usersDisplay.setText("No Users");
        }
    }

    public static Intent adminViewIntent(Context context, int userId) {
        Intent intent = new Intent(context, AdminViewUserPage.class);
        intent.putExtra(USER_ID_KEY, userId);

        return intent;
    }

}