package com.diezavala.project02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.diezavala.project02.databinding.ActivityWorkoutInfoPageBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorkoutInfoPage extends AppCompatActivity {
    ActivityWorkoutInfoPageBinding binding;

    Button nextSplit;
    Button prevSplit;
    TextView welcomeInfo;
    TextView splitName;
    TextView workoutInfo;
    List<String> splits = new ArrayList<>();
    HashMap<Integer, String> splitNames = new HashMap<>();
    int num = -1;

    String ppl =
            "Push day:\n" +
            "- Barbell bench press (4 sets of 8-12 reps)\n" +
            "- Seated dumbbell shoulder press (4 sets of 8-12 reps)\n" +
            "- Tricep pushdowns (3 sets of 10-15 reps)\n" +
            "- Lateral raises (3 sets of 10-15 reps)\n" +
            "- Cable flyes (3 sets of 10-15 reps)\n" +
            "\n" +
            "Pull day:\n" +
            "- Deadlifts (4 sets of 8-12 reps)\n" +
            "- Pull-ups (3 sets of 10-15 reps)\n" +
            "- Seated cable rows (3 sets of 10-15 reps)\n" +
            "- Dumbbell curls (3 sets of 10-15 reps)\n" +
            "- Face pulls (3 sets of 10-15 reps)\n" +
            "\n" +
            "Leg day:\n" +
            "- Squats (4 sets of 8-12 reps)\n" +
            "- Leg press (3 sets of 10-15 reps)\n" +
            "- Romanian deadlifts (3 sets of 10-15 reps)\n" +
            "- Leg curls (3 sets of 10-15 reps)\n" +
            "- Standing calf raises (3 sets of 10-15 reps)";

    String arnold =
            "Day 1: Chest and Back\n" +
            "- Barbell bench press (4 sets of 8-12 reps)\n" +
            "- Weighted pull-ups (4 sets of 8-12 reps)\n" +
            "- Incline dumbbell press (3 sets of 10-15 reps)\n" +
            "- Bent over rows (3 sets of 10-15 reps)\n" +
            "- Cable flyes (3 sets of 10-15 reps)\n" +
            "- Seated cable rows (3 sets of 10-15 reps)\n" +
            "\n" +
            "Day 2: Arms\n" +
            "- Barbell curls (4 sets of 8-12 reps)\n" +
            "- Skullcrushers (4 sets of 8-12 reps)\n" +
            "- Hammer curls (3 sets of 10-15 reps)\n" +
            "- Overhead tricep extensions (3 sets of 10-15 reps)\n" +
            "- Concentration curls (3 sets of 10-15 reps)\n" +
            "- Tricep pushdowns (3 sets of 10-15 reps)\n" +
            "\n" +
            "Day 3: Shoulders and Legs\n" +
            "- Seated dumbbell shoulder press (4 sets of 8-12 reps)\n" +
            "- Squats (4 sets of 8-12 reps)\n" +
            "- Lateral raises (3 sets of 10-15 reps)\n" +
            "- Leg press (3 sets of 10-15 reps)\n" +
            "- Upright rows (3 sets of 10-15 reps)\n" +
            "- Romanian deadlifts (3 sets of 10-15 reps)\n" +
            "\n" +
            "Day 4: Chest and Back\n" +
            "- Incline bench press (4 sets of 8-12 reps)\n" +
            "- T-bar rows (4 sets of 8-12 reps)\n" +
            "- Dumbbell flyes (3 sets of 10-15 reps)\n" +
            "- Seated cable rows (3 sets of 10-15 reps)\n" +
            "- Pec deck flyes (3 sets of 10-15 reps)\n" +
            "- Lat pulldowns (3 sets of 10-15 reps)";

    String upper =
            "Day 1: Chest and Triceps\n" +
            "- Barbell bench press (4 sets of 8-12 reps)\n" +
            "- Incline dumbbell press (4 sets of 8-12 reps)\n" +
            "- Cable flyes (3 sets of 10-15 reps)\n" +
            "- Tricep pushdowns (3 sets of 10-15 reps)\n" +
            "- Close-grip bench press (3 sets of 10-15 reps)\n" +
            "\n" +
            "Day 2: Back and Biceps\n" +
            "- Deadlifts (4 sets of 8-12 reps)\n" +
            "- Pull-ups (4 sets of 8-12 reps)\n" +
            "- Seated cable rows (3 sets of 10-15 reps)\n" +
            "- Dumbbell curls (3 sets of 10-15 reps)\n" +
            "- Hammer curls (3 sets of 10-15 reps)\n" +
            "\n" +
            "Day 3: Rest\n" +
            "\n" +
            "Day 4: Shoulders and Traps\n" +
            "- Seated dumbbell shoulder press (4 sets of 8-12 reps)\n" +
            "- Lateral raises (3 sets of 10-15 reps)\n" +
            "- Upright rows (3 sets of 10-15 reps)\n" +
            "- Face pulls (3 sets of 10-15 reps)\n" +
            "- Shrugs (3 sets of 10-15 reps)\n" +
            "\n" +
            "Day 5: Chest and Triceps\n" +
            "- Incline bench press (4 sets of 8-12 reps)\n" +
            "- Dumbbell flyes (4 sets of 8-12 reps)\n" +
            "- Cable flyes (3 sets of 10-15 reps)\n" +
            "- Tricep extensions (3 sets of 10-15 reps)\n" +
            "- Overhead tricep extensions (3 sets of 10-15 reps)\n" +
            "\n" +
            "Day 6: Back and Biceps\n" +
            "- Barbell rows (4 sets of 8-12 reps)\n" +
            "- Lat pulldowns (4 sets of 8-12 reps)\n" +
            "- Seated cable rows (3 sets of 10-15 reps)\n" +
            "- Dumbbell curls (3 sets of 10-15 reps)\n" +
            "- Concentration curls (3 sets of 10-15 reps)\n" +
            "\n" +
            "Day 7: Rest";

    String fullBody =
            "Day 1: Upper Body\n" +
            "- Barbell bench press (4 sets of 8-12 reps)\n" +
            "- Incline dumbbell press (4 sets of 8-12 reps)\n" +
            "- Cable flyes (3 sets of 10-15 reps)\n" +
            "- Seated cable rows (4 sets of 8-12 reps)\n" +
            "- Pull-ups (3 sets of 10-15 reps)\n" +
            "- Dumbbell curls (3 sets of 10-15 reps)\n" +
            "- Tricep pushdowns (3 sets of 10-15 reps)\n" +
            "\n" +
            "Day 2: Lower Body (Quad Dominant)\n" +
            "- Barbell squats (4 sets of 8-12 reps)\n" +
            "- Leg press (4 sets of 8-12 reps)\n" +
            "- Walking lunges (3 sets of 10-15 reps)\n" +
            "- Leg extensions (3 sets of 10-15 reps)\n" +
            "- Calf raises (3 sets of 10-15 reps)\n" +
            "\n" +
            "Day 3: Rest\n" +
            "\n" +
            "Day 4: Upper Body\n" +
            "- Seated dumbbell shoulder press (4 sets of 8-12 reps)\n" +
            "- Lateral raises (3 sets of 10-15 reps)\n" +
            "- Upright rows (3 sets of 10-15 reps)\n" +
            "- Deadlifts (4 sets of 8-12 reps)\n" +
            "- Pull-ups (3 sets of 10-15 reps)\n" +
            "- Hammer curls (3 sets of 10-15 reps)\n" +
            "- Overhead tricep extensions (3 sets of 10-15 reps)\n" +
            "\n" +
            "Day 5: Lower Body (Hamstring Dominant)\n" +
            "- Deadlifts (4 sets of 8-12 reps)\n" +
            "- Leg curls (4 sets of 8-12 reps)\n" +
            "- Bulgarian split squats (3 sets of 10-15 reps)\n" +
            "- Glute bridges (3 sets of 10-15 reps)\n" +
            "- Calf raises (3 sets of 10-15 reps)";
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
                startActivity(new Intent(WorkoutInfoPage.this, LogInPage.class));
                return true;
//            case R.id.gymlog:
//                Toast.makeText(this, "Going to GymLog", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(WorkoutInfoPage.this, GymLogPage.class));
//                return true;
            case R.id.welcome:
                Toast.makeText(this, "Going to Welcome Page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(WorkoutInfoPage.this, WelcomeUserActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_info_page);

        binding = ActivityWorkoutInfoPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        welcomeInfo = binding.helpfulInfoWelcome;
        splitName = binding.splitName;
        workoutInfo = binding.workoutInfoText;
        nextSplit = binding.nextSplitButton;
        prevSplit = binding.previousSplitButton;

//        updateDisplayText();

        workoutInfo.setMovementMethod(new ScrollingMovementMethod());

        splits.add(arnold);
        splits.add(ppl);
        splits.add(upper);
        splits.add(fullBody);
        splitNames.put(0, "Arnold Split");
        splitNames.put(1, "Push, Pull, Legs Split");
        splitNames.put(2, "Upper Body Split");
        splitNames.put(3, "Upper & Lower Split");
        nextSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDisplayText();
            }
        });

        prevSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDisplayTextBack();
            }
        });

    }

    //starting at -1
    private void updateDisplayText() {
        num++;
        if(num == 4){
            Toast.makeText(WorkoutInfoPage.this, "You've seen all the splits" , Toast.LENGTH_SHORT).show();
            num = 0;
            workoutInfo.setText(splits.get(num));
            splitName.setText((num+1) + ": " + splitNames.get(num));

        }else{
            workoutInfo.setText(splits.get(num));
            splitName.setText((num+1) + ": " + splitNames.get(num));
        }
    }

    private void updateDisplayTextBack() {
        if(num == -1){
            workoutInfo.setText("Click The next button");
            Toast.makeText(WorkoutInfoPage.this, "Click The Next Button First" , Toast.LENGTH_SHORT).show();
        }else {
            if(num != 0 && num < 4){
                num--;
                workoutInfo.setText(splits.get(num));
                splitName.setText((num+1) + ": " + splitNames.get(num));
            }else if(num == 0){
                num = 3;
                workoutInfo.setText(splits.get(num));
                splitName.setText((num+1) + ": " + splitNames.get(num));
            }else{
                num--;
                workoutInfo.setText(splits.get(num));
                splitName.setText((num+1) + ": " + splitNames.get(num));
            }
        }
    }

    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, WorkoutInfoPage.class);
        return intent;
    }


}