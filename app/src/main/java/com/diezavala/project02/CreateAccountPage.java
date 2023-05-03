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
import com.diezavala.project02.databinding.ActivityCreateAccountPageBinding;

public class CreateAccountPage extends AppCompatActivity {
    ActivityCreateAccountPageBinding binding;
    TextView createText;
    EditText usernameText;
    EditText passwordText;
    EditText adminCodeText;

    Button createAccountButton;

    private UserDAO userDAO;
    private String usernameString;
    private String passwordString;
    private String adminCodeInt;
    private users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_page);

        binding = ActivityCreateAccountPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createText = binding.createAccountTextView;
        usernameText = binding.createUsernameTextEdit;
        passwordText = binding.createPasswordTextEdit;
        adminCodeText = binding.adminCodeText;
        createAccountButton = binding.createAccountButton;

        getDatabase();

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValuesFromDisplay();
                if(!checkForUserInDatabase()){
                    addUserToDatabase();
                }else{
                    userAlreadyExists();
                    System.out.println("user already exists method() called succesfully");
                }
            }
        });

        createText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = MainActivity.intentFactory(getApplicationContext());
                startActivity(intent);
                return false;
            }
        });
    }

    private void userAlreadyExists() {
        System.out.println("Toast call:");
        Toast.makeText(CreateAccountPage.this, "This user already exists, try another name" , Toast.LENGTH_SHORT).show();
    }

    private void addUserToDatabase(){
        if(adminCodeInt.equals("123") || adminCodeInt.equals("9911")){
            user = new users(usernameString, passwordString, 1);
            userDAO.insert(user);
            Toast.makeText(this, "Admin" + usernameString + " added", Toast.LENGTH_SHORT).show();
        }else{
            user = new users(usernameString, passwordString, 0);
            userDAO.insert(user);
            Toast.makeText(this, "User " + usernameString + " added", Toast.LENGTH_SHORT).show();
        }
    }
    private void getValuesFromDisplay(){
        usernameString = usernameText.getText().toString();
        passwordString = passwordText.getText().toString();
        adminCodeInt = adminCodeText.getText().toString();
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
        Intent intent = new Intent(context, CreateAccountPage.class);
        return intent;
    }


}