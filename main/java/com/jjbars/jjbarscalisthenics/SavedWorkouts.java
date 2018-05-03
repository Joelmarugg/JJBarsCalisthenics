package com.jjbars.jjbarscalisthenics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import static com.jjbars.jjbarscalisthenics.CreateCustomWorkout.rvadapter2;

public class SavedWorkouts extends AppCompatActivity {

    Button goBack;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_workouts);

        recyclerView = findViewById(R.id.saved_workouts_recyclerView);


        goBack = (Button) findViewById(R.id.back_home_button);
        goBack.setOnClickListener(e->{
            startActivity(new Intent(this, Home.class));
        });


    }
}
