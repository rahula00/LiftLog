package com.example.liftlog;

public class ExerciseStats {
    public int max_weight;
    public int reps;
    public int sets;
    public int exercise_id;

    public ExerciseStats(int maxweight, int numreps, int numsets, int id){
        max_weight = maxweight;
        reps = numreps;
        sets = numsets;
        exercise_id = id;
    }
}
