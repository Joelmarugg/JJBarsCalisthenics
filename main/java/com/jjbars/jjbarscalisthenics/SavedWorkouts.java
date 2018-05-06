package com.jjbars.jjbarscalisthenics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;
import static com.jjbars.jjbarscalisthenics.CreateCustomWorkout.rvadapter2;

public class SavedWorkouts extends AppCompatActivity {

    Button goBack;
    static ArrayList<Workout> savedWorkouts = new ArrayList<>();
    ArrayList<String> workoutNames = new ArrayList<>();
    static RecyclerView recyclerView;
    static RecyclerView.Adapter rvadapter;
    static RecyclerView.LayoutManager rvLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_workouts);

        savedWorkouts = workout_saveloader.loadData(this);

        recyclerView = findViewById(R.id.saved_workouts_recyclerView);
        recyclerView.setHasFixedSize(true);
        rvLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvLayoutManager);

        for( Workout w : savedWorkouts){
            workoutNames.add(w.getTitle());
        }
        rvadapter = new WRvAdapterKlasse(workoutNames);
        recyclerView.setAdapter(rvadapter);


        goBack = (Button) findViewById(R.id.back_home_button);
        goBack.setOnClickListener(e->{
            startActivity(new Intent(this, Home.class));
        });


    }



}
