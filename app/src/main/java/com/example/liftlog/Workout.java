package com.example.liftlog;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Workout {
    public int id;
    public String name;
    public String description;
    public List<Double> multipliers;
    public Queue<ExerciseStats> statsList;           //The exercise list

    public Workout(int id, String workoutName, String description,  List<Double> multipliers, Queue<ExerciseStats> exercisesStats)
    {
        this.id = id;
        this.name = workoutName;
        this.description = description;
        this.multipliers = new ArrayList<>(multipliers);

        Queue<ExerciseStats> queue = new LinkedList<>();
        for(ExerciseStats i : exercisesStats)
            queue.add(i.copy());

        this.statsList = queue;
    }

    public Workout copy()
    {
        List<Double> list2 = new ArrayList<>(multipliers);

        Queue<ExerciseStats> queue = new LinkedList<>();
        for(ExerciseStats i : statsList)
            queue.add(i.copy());

        return new Workout(id, name, description, list2, queue);
    }

    public void initialize()
    {
        if(statsList.size() == multipliers.size())
        {
            return;
        }
    }
}
