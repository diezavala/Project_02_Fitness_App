package com.diezavala.project02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.diezavala.project02.FD.FoodDAO;
import com.diezavala.project02.FD.FoodDatabase;
import com.diezavala.project02.GL.GymLogDataBase;
import com.diezavala.project02.databinding.ActivityFoodLogBinding;
import com.diezavala.project02.databinding.ActivityGymLogBinding;

import java.util.List;

public class FoodLog extends AppCompatActivity {
    private static final String USER_ID_KEY = "com.diezavala.project02.userIdKey";
    private static final String PREFERENCES_KEY = "com.diezavala.project02.PREFERENCES_KEY";
    ActivityFoodLogBinding binding;
    TextView mainDisplay;

    EditText food;
    EditText calsPS;
    EditText servings;

    Button submit;
    FoodDAO foodDAO;
    List<Food> FoodLogList;
    private users user;
    private int userId;

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
        setContentView(R.layout.activity_food_log);

        binding = ActivityFoodLogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainDisplay = binding.mainFoodLogDisplay;
        food = binding.mainFoodEditText;
        calsPS = binding.mainCalsEditText;
        servings = binding.mainServingsEditText;
        submit = binding.mainSubmitButton;

        mainDisplay.setMovementMethod(new ScrollingMovementMethod());

        foodDAO = Room.databaseBuilder(this, FoodDatabase.class, FoodDatabase.FOOD_DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .FoodDAO();
        refreshDisplay();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitFoodLog();
                refreshDisplay();
            }
        });
    }

    private void submitFoodLog() {
        String foodName = food.getText().toString();
        double servingSize = Double.parseDouble(servings.getText().toString());
        int cals = Integer.parseInt(calsPS.getText().toString());

        Food log = new Food(foodName, cals, servingSize);

        foodDAO.insert(log);
    }

    private void refreshDisplay() {
        userId = getIntent().getIntExtra(USER_ID_KEY, -1);
//        FoodLogList = foodDAO.getFoodLogById(userId);
        FoodLogList = foodDAO.getAllFoodLogs();
        if(!FoodLogList.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for(Food log: FoodLogList){
                sb.append(log.toString());
            }
            mainDisplay.setText(sb.toString());
        }else{
            mainDisplay.setText("No Logs");
        }
    }

    public static Intent intentFactory(Context context, int userId){
        Intent intent = new Intent(context, FoodLog.class);
        intent.putExtra(USER_ID_KEY, userId);

        return intent;
    }


}