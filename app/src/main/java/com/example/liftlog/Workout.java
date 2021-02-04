package com.example.liftlog;

import java.util.ArrayList;
import java.util.LinkedList;
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

    public Workout copy()
    {
        List<Integer> list = new ArrayList<>(triggers);

        Queue<ExerciseStats> queue = new LinkedList<>();
        for(ExerciseStats i : statsList)
            queue.add(i.copy());

        return new Workout(id, name, description, list, queue);
    }


}
