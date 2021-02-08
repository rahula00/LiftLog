package com.example.liftlog;

import java.util.LinkedList;
import java.util.Queue;


public class Routine{
    public int id;
    public String name;
    public String description;
    public String imageUrl;
    public Queue<Workout> workouts;           //The ids of the workouts in the routine

    public Routine(int id, String routineName, String description, String imageUrl, Queue<Workout> workouts)
    {
        this.id = id;
        this.name = routineName;
        this.description = description;
        this.imageUrl = imageUrl;

        Queue<Workout> queue = new LinkedList<>();
        for(Workout i : workouts)
            queue.add(i.copy());

        this.workouts = queue;
    }

    Routine copy()
    {
        Queue<Workout> queue = new LinkedList<>();
        for(Workout i : workouts)
            queue.add(i.copy());

        return new Routine(id, name, description, imageUrl, queue);
    }
}
