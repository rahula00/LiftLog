package com.example.liftlog;

import java.util.List;
import java.util.Queue;

public class Workout {
    public int id;
    public String name;
    public String description;
    public List<Integer> triggers;                          //The ids of the exercises to increase max weight
    public Queue<ExerciseStats> statsList;               //The exercise list

    public Workout(int id, String workoutName, String description, List<Integer> triggers, Queue<ExerciseStats> exercisesStats)
    {
        this.id = id;
        this.name = workoutName;
        this.description = description;
        this.triggers = triggers;
        this.statsList = exercisesStats;
    }
}
