package com.jjbars.jjbarscalisthenics;

import java.util.ArrayList;

public class Workout {

   private ArrayList<String> Exercises = new ArrayList<>();

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    String Title;
    public Workout(){}

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
