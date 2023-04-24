package com.diezavala.project02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.diezavala.project02.databinding.ActivityCreateAccountPageBinding;

public class CreateAccountPage extends AppCompatActivity {

    ActivityCreateAccountPageBinding binding;
    TextView createText;
    EditText usernameText;
    EditText passwordText;

    Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_page);

        binding = ActivityCreateAccountPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createText = binding.createAccountTextView;
        usernameText = binding.createUsernameTextEdit;
        passwordText = binding.createPasswordTextEdit;
        createAccountButton = binding.createAccountButton;

        createText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                startActivity(new Intent(CreateAccountPage.this,MainActivity.class));
                return true;
            }
        });
    }
}