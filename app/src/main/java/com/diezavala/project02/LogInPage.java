package com.diezavala.project02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.diezavala.project02.DB.AppDataBase;
import com.diezavala.project02.DB.UserDAO;
import com.diezavala.project02.databinding.ActivityLogInPageBinding;

public class LogInPage extends AppCompatActivity {
    ActivityLogInPageBinding binding;
    TextView logInText;
    EditText usernameText;
    EditText passwordText;

    Button logInButton;

    private UserDAO userDAO;
    private String usernameString;
    private String passwordString;
    private users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);

        binding = ActivityLogInPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        logInText = binding.logInTextView;
        usernameText = binding.usernameEnterEditText;
        passwordText = binding.passwordEnterEditText;
        logInButton = binding.logInButton;

        getDatabase();

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValuesFromDisplay();
                if(checkForUserInDatabase()){
                    if(!validatePassword()){
                        Toast.makeText(LogInPage.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent = WelcomeUserActivity.intentFactory(getApplicationContext(), user.getLogId());
                        startActivity(intent);
                    }
                }
            }
        });
        logInText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                startActivity(new Intent(LogInPage.this,MainActivity.class));
                return true;
            }
        });
    }

    private boolean validatePassword(){
        return user.getPassword().equals(passwordString);
    }

    private void getValuesFromDisplay(){
        usernameString = usernameText.getText().toString();
        passwordString = passwordText.getText().toString();
    }

    private boolean checkForUserInDatabase(){
        user = userDAO.getUserByUsername(usernameString);
        if(user == null){
            Toast.makeText(this, "no user " + usernameString + " found", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void getDatabase(){
        userDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build().getDAO();
    }


    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, LogInPage.class);
        return intent;
    }

}