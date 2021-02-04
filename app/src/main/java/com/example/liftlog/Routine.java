package com.example.liftlog;

import java.util.LinkedList;
import java.util.Queue;


public class Routine {
    public int id;
    public String name;
    public String description;
    public String imageUrl;
    public Queue<Integer> workouts;           //The ids of the workouts in the routine

    public Routine(int id, String routineName, String description, String imageUrl, Queue<Integer> workouts)
    {
        this.id = id;
        this.name = routineName;
        this.description = description;
        this.imageUrl = imageUrl;

        Queue<Integer> queue = new LinkedList<>();
        queue.addAll(workouts);

        this.workouts = queue;
    }

    public Routine copy()
    {
        Queue<Integer> queue = new LinkedList<>();
        queue.addAll(workouts);
        return new Routine(id, name, description, imageUrl, queue);
    }
}
