package com.example.liftlog;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Routine{
    public int id;
    public String name;
    public String description;
    public String imageUrl;
    public List<Workout> workouts;

    public Routine(int new_id, String new_routineName, String new_description, String new_imageUrl, List<Workout> new_workouts)
    {
        this.id = new_id;
        this.name = new_routineName;
        this.description = new_description;
        this.imageUrl = new_imageUrl;

        this.workouts = new LinkedList<>();
        for(Workout i : new_workouts)
            this.workouts.add(i.copy());
    }

    Routine copy()
    {
        return new Routine(id, name, description, imageUrl, workouts);
    }

    Queue<Workout> copyWorkouts()
    {
        Queue<Workout> workout_queue = new LinkedList<>();
        for(Workout i : workouts)
            workout_queue.add(i.copy());
        return workout_queue;
    }
}
