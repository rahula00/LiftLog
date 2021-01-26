package com.example.liftlog;

public class ExerciseStats {
    public int exercise;
    public int weight;
    public int reps;
    public int sets;

    public ExerciseStats(int id, int weightassigned, int numreps, int numsets){
        exercise = id;
        weight = weightassigned;
        reps = numreps;
        sets = numsets;
    }
}
