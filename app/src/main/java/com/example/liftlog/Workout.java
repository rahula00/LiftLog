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
    private int threshold = 5;                       //Threshold for weight modifier vs real weight

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
        //Ensure the lists match in size, as they should.
        if(statsList.size() == multipliers.size())
        {
            int index = 0;
            //For every exercisestat in the workout
            //Assign the correct weight. If the multiplier is > threshold, then assign the given weight directly
            // If less, then access the users user_max, pull the appropriate exercise max and modify it by the modifier.
            for(ExerciseStats e: statsList)
            {
                double multiplier_value = multipliers.get(index);
                if(multiplier_value >= threshold)
                {
                    e.weight = (int)multiplier_value;
                }
                else
                {
                    try
                    {
                        ArrayList array_holder = user.user_max.get(e.exercise);
                        Pair pair_holder = (Pair) array_holder.get(array_holder.size() - 1);
                        int max_weight = (int) pair_holder.second;
                        e.weight = (int) (max_weight * multiplier_value);
                    }
                    catch(Exception ex)
                    {
                        Log.d("workout: "+name, ex.toString());
                    }
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
