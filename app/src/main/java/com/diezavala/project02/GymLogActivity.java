package com.diezavala.project02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.diezavala.project02.DB.AppDataBase;
import com.diezavala.project02.DB.GymLogDAO;
//import com.diezavala.project02.databinding.ActivityGymLogBinding;
import com.diezavala.project02.databinding.ActivityGymlogBinding;

//import com.diezavala.project02.databinding.ActivityMainBinding;

import java.util.List;

public class GymLogActivity extends AppCompatActivity {
    ActivityGymlogBinding binding;

    TextView mainDisplay;
    EditText exercise;
    EditText weight;
    EditText reps;

    Button submit;

    GymLogDAO gymLogDAO;
    List<GymLog> gymLogList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding=ActivityGymlogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mainDisplay = binding.mainGymLogDisplay;
        exercise=binding.mainExerciseEditText;
        weight=binding.mainWeightEditText;
        reps=binding.mainRepsEditText;
        submit=binding.mainSubmitButton;

        mainDisplay.setMovementMethod(new ScrollingMovementMethod());

//        gymLogDAO= AppDataBase.getInstance(this).GymLogDAO();
        gymLogDAO= Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries().build().GymLogDAO();
        refreshDisplay();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitGymLog();
                refreshDisplay();
            }
        });
    }             //ENd of onCreate

    private void submitGymLog(){
        String Lexersise = exercise.getText().toString() ;
        double Lweight =Double.parseDouble(weight.getText().toString());
        int Lreps=Integer.parseInt(reps.getText().toString());
        GymLog log = new GymLog(Lexersise,Lweight,Lreps);
        gymLogDAO.insert(log);
    }

    private void refreshDisplay(){
        gymLogList= gymLogDAO.getGymLogs();
        if(!gymLogList.isEmpty()){
            StringBuilder sb= new StringBuilder();
            for( GymLog log: gymLogList){
                sb.append(log.toString());
            }
            mainDisplay.setText(sb.toString());
        }else{
            mainDisplay.setText(R.string.no_logs_message);
        }
    }
}