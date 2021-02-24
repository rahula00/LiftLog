package com.example.liftlog;

import androidx.annotation.NonNull;

public class ExerciseStats {
    public int exercise;
    public int weight;
    public int reps;
    public int sets;
    public boolean trigger_max_change;

    public ExerciseStats(int id, int weightassigned, int numreps, int numsets){
        exercise = id;
        weight = weightassigned;
        reps = numreps;
        sets = numsets;
        trigger_max_change = false;
    }
    public ExerciseStats(int id, int weightassigned, int numreps, int numsets, boolean trigger){
        exercise = id;
        weight = weightassigned;
        reps = numreps;
        sets = numsets;
        trigger_max_change = trigger;
    }
    public ExerciseStats copy()
    {
        return new ExerciseStats(exercise, weight, reps, sets, trigger_max_change);
    }
    @NonNull
    @Override
    public String toString() {
        return "\nExercise:" + exercise+"\nWeight:" + weight+"\nReps:" + reps+"\nSets:" + sets + "\nTrigger:"+ trigger_max_change;
    }
}
