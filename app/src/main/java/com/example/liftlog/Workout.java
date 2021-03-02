package com.example.liftlog;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Workout {
    private static long id_counter = 0;
    public long id;
    public String name;
    public String description;
    public LinkedList<ExerciseStats> statsList;

    public Workout(long id, String workoutName, String description,  LinkedList<ExerciseStats> exercisesStats)
    {
        //see my comment in Routine.Java
        this.id = id > 0 ? (id + id_counter) : id_counter;
        id_counter = id > 0 ? (id_counter + id) : (id_counter + 1);
        this.name = workoutName;
        this.description = description;

        this.statsList = new LinkedList<>();
        for(ExerciseStats i : exercisesStats)
            statsList.add(i.copy());
    }

    public Workout(long id, String workoutName, String description)
    {
        this.id = id;
        this.name = workoutName;
        this.description = description;

        this.statsList = new LinkedList<ExerciseStats>();
    }

    public Workout copy()
    {
        return new Workout(id, name, description, statsList);
    }

    @NonNull
    @Override
    public String toString() {
        return "Id:"+id + "\nName:"+ name+ "\nDesc:"+ description+ "\nArray:"+ statsList.toString();
    }
}
