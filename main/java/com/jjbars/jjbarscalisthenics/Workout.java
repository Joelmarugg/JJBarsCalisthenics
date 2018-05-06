package com.jjbars.jjbarscalisthenics;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Workout {

   private ArrayList<String> Exercises = new ArrayList<>();
   private String Title;

   public Workout(String title){
       this.Title=title;
   }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }


    public void addExercise(String s){
        this.Exercises.add(s);

    }
    public void deleteExercise(String s){
        for(int i =0; i<Exercises.size();i++){
            if(Exercises.get(i).equals(s))
                Exercises.remove(i);
        }
    }


    public ArrayList<String> getExercises() {
        return Exercises;
    }


}
