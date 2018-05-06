package com.jjbars.jjbarscalisthenics;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class workout_saveloader {

    public static void saveData(Context c, ArrayList<Workout> savelist){
        SharedPreferences sharedPreferences = c.getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(savelist);
        editor.putString("Workouts",json);
        editor.apply();
    }

    public static ArrayList<Workout> loadData(Context c){

        SharedPreferences sharedPreferences = c.getSharedPreferences("shared preferences", MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString("Workouts",null);
        Type type = new TypeToken<ArrayList<Workout>>() {}.getType();
        ArrayList<Workout> savedWorkouts = gson.fromJson(json,type);

        if(savedWorkouts== null){
            savedWorkouts = new ArrayList<>();
        }
        return savedWorkouts;
    }}
