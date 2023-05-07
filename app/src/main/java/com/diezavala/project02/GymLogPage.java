package com.diezavala.project02;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.diezavala.project02.DB.AppDataBase;
import com.diezavala.project02.DB.UserDAO;
import com.diezavala.project02.GL.GymLogDAO;
import com.diezavala.project02.GL.GymLogDataBase;
import com.diezavala.project02.databinding.ActivityGymLogBinding;

import java.util.List;

public class GymLogPage extends AppCompatActivity {
    private static final String USER_ID_KEY = "com.diezavala.project02.userIdKey";
    private static final String PREFERENCES_KEY = "com.diezavala.project02.PREFERENCES_KEY";

    users user;
    UserDAO userDAO;
    private int userId = -1;


    ActivityGymLogBinding binding;

    TextView mainDisplay;

    EditText exercise;
    EditText weight;
    EditText reps;

    Button submit;

    GymLogDAO gymLogDAO;

    List<GymLogItem> GymLogList;

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
        setContentView(R.layout.activity_gym_log);

        binding = ActivityGymLogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainDisplay = binding.mainGymLogDisplay;
        exercise = binding.mainExerciseEditText;
        weight = binding.mainWeightEditText;
        reps = binding.mainRepsEditText;
        submit = binding.mainSubmitButton;

        mainDisplay.setMovementMethod(new ScrollingMovementMethod());


        getDatabase();
        userId = getIntent().getIntExtra(USER_ID_KEY, -1);
        user = userDAO.getUserByUSerId(userId);

        gymLogDAO = Room.databaseBuilder(this, GymLogDataBase.class, GymLogDataBase.GL_DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .GymLogDAO();
        refreshDisplay();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitGymLog();
                refreshDisplay();
            }
        });
    }

    private void submitGymLog(){
        String exerciseSub = exercise.getText().toString();
        double weightSub = Double.parseDouble(weight.getText().toString());
        int repsSub = Integer.parseInt(reps.getText().toString());

        GymLogItem log = new GymLogItem(exerciseSub, repsSub, weightSub, userId);

        gymLogDAO.insert(log);

    }

    private void refreshDisplay(){
        userId = getIntent().getIntExtra(USER_ID_KEY, -1);
        System.out.println("User Id: " + userId);
        GymLogList = gymLogDAO.getLogByUserId(userId);
//        GymLogList = gymLogDAO.getGymLogs();
        if(!GymLogList.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for(GymLogItem log: GymLogList){
                sb.append(log.toString());
            }
            mainDisplay.setText(sb.toString());
        }else{
            mainDisplay.setText("No Logs");
        }
    }

//    public static Intent gymLogIntent(Context context, int userId){

    public static Intent intentFactory(Context context, int userId){
        Intent intent = new Intent(context, GymLogPage.class);
        intent.putExtra(USER_ID_KEY, userId);

        return intent;
    }

    private void getDatabase() {
        userDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build().getDAO();
    }

}