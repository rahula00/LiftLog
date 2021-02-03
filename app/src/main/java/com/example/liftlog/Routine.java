package com.example.liftlog;

import java.util.List;

public class Routine {
    public int id;
    public String name;
    public String description;
    public List<Integer> workouts;           //The ids of the workouts in the routine

    public Routine(int id, String routineName, String description, List<Integer> workouts)
    {
        this.id = id;
        this.name = routineName;
        this.description = description;
        this.workouts = workouts;
    }
}
