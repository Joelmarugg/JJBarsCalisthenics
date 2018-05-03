package com.jjbars.jjbarscalisthenics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Home extends AppCompatActivity {

    Button openCreateCustomWorkout;
    Button openGenerateRandomWorkout;
    Button openSaveWorkouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        openCreateCustomWorkout =(Button)findViewById(R.id.create_custom_workout_button);
        openGenerateRandomWorkout =(Button)findViewById(R.id.generate_random_workout_button);
        openSaveWorkouts =(Button)findViewById(R.id.saved_workouts_button);

        openCreateCustomWorkout.setOnClickListener(e->{
            startActivity(new Intent(this, CreateCustomWorkout.class));
        });

        openGenerateRandomWorkout.setOnClickListener(e->{
            startActivity(new Intent(this, GenerateRandomWorkout.class));
        });

        openSaveWorkouts.setOnClickListener(e->{
            startActivity(new Intent(this, SavedWorkouts.class));
        });

    }
}
