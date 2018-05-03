package com.jjbars.jjbarscalisthenics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class SavedWorkouts extends AppCompatActivity {

    Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_workouts);

        goBack = (Button) findViewById(R.id.back_home_button);
        goBack.setOnClickListener(e->{
            startActivity(new Intent(this, Home.class));
        });


    }
}
