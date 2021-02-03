package com.example.liftlog;

import java.util.Queue;


public class Routine {
    public int id;
    public String name;
    public String description;
    public Queue<Integer> workouts;           //The ids of the workouts in the routine

    public Routine(int id, String routineName, String description, Queue<Integer> workouts)
    {
        this.id = id;
        this.name = routineName;
        this.description = description;
        this.workouts = workouts;
    }
}
