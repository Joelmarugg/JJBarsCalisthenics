package com.jjbars.jjbarscalisthenics;

public class thenxObjects {
    String exercise;

    public thenxObjects(String exercise){
        this.exercise = exercise;
    }
    public thenxObjects(String... exercise){
        this.exercise = "<u><b>"+exercise[0]+"</b></u>";
    }
    public String getExercise() {return exercise;}
}
