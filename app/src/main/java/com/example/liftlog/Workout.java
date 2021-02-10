package com.example.liftlog;

import java.util.LinkedList;
import java.util.Queue;

public class Workout {
    public int id;
    public String name;
    public String description;
    public Queue<ExerciseStats> statsList;

    public Workout(int id, String workoutName, String description,  Queue<ExerciseStats> exercisesStats)
    {
        this.id = id;
        this.name = workoutName;
        this.description = description;

        this.statsList = new LinkedList<>();
        for(ExerciseStats i : exercisesStats)
            statsList.add(i.copy());
    }

    public Workout copy()
    {
        return new Workout(id, name, description, statsList);
    }
}
