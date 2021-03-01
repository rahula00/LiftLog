package com.example.liftlog;

import java.util.List;
import java.util.ArrayList;


public class Routine{
    private static int id_count = 0;
    public int id;
    public String name;
    public String description;
    public List<Workout> workouts;

    public Routine() {
        //this constructor is meant to be overridden
        id = id_count;
        ++id_count;
        name = "Routine";
        description = "description";
        workouts = new ArrayList<Workout>();
    }

    public Routine(int new_id, String new_routineName, String new_description, List<Workout> new_workouts)
    {
        //this will make sure that the id is indeed unique to the routines
        //it is actually a strange behavior that the id is given to the Routine
        //instead, we should have relied on the constructor to make one for us
        //perhaps using a random number generator with uniform distribution
        this.id = id > 0 ? (id + id_count) : id_count;
        id_count = id > 0 ? (id_count + id) : (id_count + 1);
        this.name = new_routineName;
        this.description = new_description;

        this.workouts = new ArrayList<Workout>();
        for(Workout i : new_workouts)
            this.workouts.add(i.copy());
    }

    Routine copy()
    {
        return new Routine(id, name, description, workouts);
    }

    ArrayList<Workout> copyWorkouts()
    {
        ArrayList<Workout> workout_queue = new ArrayList<>();
        for(Workout i : workouts)
            workout_queue.add(i.copy());
        return workout_queue;
    }
}
