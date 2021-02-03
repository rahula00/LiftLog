package com.example.liftlog;

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
        this.workouts = workouts;
    }
}
