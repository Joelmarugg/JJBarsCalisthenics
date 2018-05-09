package com.jjbars.jjbarscalisthenics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.text.StringSearch;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class show_workout extends AppCompatActivity {

    static RecyclerView recyclerViewiu;
    static RecyclerView.Adapter rvadapteriu;
    static RecyclerView.LayoutManager rvLayoutManageriu;
    String workoutName;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_workout);

        recyclerViewiu = findViewById(R.id.show_workout_recyclerview);
        recyclerViewiu.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerViewiu.setHasFixedSize(true);
        rvLayoutManageriu = new LinearLayoutManager(this);
        recyclerViewiu.setLayoutManager(rvLayoutManageriu);

        getIncomingIntent();


    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("name")){
            String workoutName = getIntent().getStringExtra("name");
            goToShowWorkout(workoutName);

        }
    }



    public static void goToShowWorkout(String workout){



        ArrayList<String> toShow = new ArrayList<>();

        for(Workout w : SavedWorkouts.savedWorkouts){
            if (w.getTitle().equals(workout)){

                toShow = w.getExercises();
            }
        }
        rvadapteriu = new SRvAdapterKlasse(toShow);
        recyclerViewiu.setAdapter(rvadapteriu);


    }
}
