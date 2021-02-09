package com.example.liftlog;

import android.util.Log;
import android.util.Pair;

import java.lang.reflect.Array;
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

    public void initialize(User user)
    {
        //Ensure passed in data is usable, lest it crash
        if(multipliers.size() == 0)
        {
            Log.i("workout: "+name, "Null size mult list, breaking.");
            return;
        }
        if(statsList.size() == multipliers.size())
        {
            int index = 0;
            for(ExerciseStats e: statsList)
            {
                double multiplier_value = multipliers.get(index);
                if(multiplier_value >= 2.5)
                {
                    e.weight = (int)multiplier_value;
                }
                else
                {
                    ArrayList array_holder = user.user_max.get(e.exercise);
                    Pair pair_holder = (Pair) array_holder.get(array_holder.size() -1);
                    int max_weight = (int) pair_holder.second;
                    e.weight = (int) (max_weight * multiplier_value);
                }
                index++;
            }
        }
        else
        {
            Log.i("workout: "+name, "Multipliers and Exercises don't line up");
        }
    }
}
