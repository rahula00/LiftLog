package com.example.liftlog;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class NSuns extends Routine{
    static final int divisor     =      5;
    static final int bench       =      0;
    static final int c_g_bench   =      1;
    static final int deadlift    =      2;
    static final int front_squat =      3;
    static final int oh_press    =      4;
    static final int squat       =      5;
    static final int s_deadlift  =      6;
    static final boolean push    =      true;

    static final String nsun_descr = "nSuns 5/3/1 is a linear progression powerlifting program" +
            " that was inspired by Jim Wendler’s 5/3/1 strength program. It progresses on a" +
            " weekly basis, making it well suited for late stage novice and early intermediate" +
            " lifters. It is known for its challenging amount of volume. Those who stick with " +
            "it tend to find great results from the additional work capacity.\n" +
            "EXPERIENCE LEVEL: BEGINNER, INTERMEDIATE\n" +
            "WEEKS: INDEFINITE\n" +
            "PERIODIZATION: LINEAR PERIODIZATION\n" +
            "POWERLIFTING MEET PREP PROGRAM: NO\n" +
            "PROGRAM GOAL: HIGH VOLUME, POWERLIFTING, STRENGTH\n" +
            "USES RPE:NO\n" +
            "USES 1RM PERCENTAGE(%):YES";
    static final ArrayList<LinkedList<ExerciseStats>> exercises = new ArrayList<LinkedList<ExerciseStats>>() {
        {
            add(new LinkedList<ExerciseStats>() {
                {
                    add(new ExerciseStats(bench, 0, 8, 1));
                    add(new ExerciseStats(bench, 0, 6, 1));
                    add(new ExerciseStats(bench, 0, 4, 1));
                    add(new ExerciseStats(bench, 0, 4, 1));
                    add(new ExerciseStats(bench, 0, 4, 1));
                    add(new ExerciseStats(bench, 0, 5, 1));
                    add(new ExerciseStats(bench, 0, 6, 1));
                    add(new ExerciseStats(bench, 0, 7, 1));
                    add(new ExerciseStats(bench, 0, 8, 1));
                    add(new ExerciseStats(oh_press, 0, 6, 1));
                    add(new ExerciseStats(oh_press, 0, 5, 1));
                    add(new ExerciseStats(oh_press, 0, 3, 1));
                    add(new ExerciseStats(oh_press, 0, 5, 1));
                    add(new ExerciseStats(oh_press, 0, 7, 1));
                    add(new ExerciseStats(oh_press, 0, 4, 1));
                    add(new ExerciseStats(oh_press, 0, 6, 1));
                    add(new ExerciseStats(oh_press, 0, 8, 1));
                }
            });
            add(new LinkedList<ExerciseStats>() {
                {
                    add(new ExerciseStats(squat, 0, 5, 1));
                    add(new ExerciseStats(squat, 0, 3, 1));
                    add(new ExerciseStats(squat, 0, 1, 1, push));
                    add(new ExerciseStats(squat, 0, 3, 1));
                    add(new ExerciseStats(squat, 0, 3, 1));
                    add(new ExerciseStats(squat, 0, 3, 1));
                    add(new ExerciseStats(squat, 0, 5, 1));
                    add(new ExerciseStats(squat, 0, 5, 1));
                    add(new ExerciseStats(squat, 0, 5, 1));
                    add(new ExerciseStats(s_deadlift, 0, 5, 1));
                    add(new ExerciseStats(s_deadlift, 0, 5, 1));
                    add(new ExerciseStats(s_deadlift, 0, 3, 1));
                    add(new ExerciseStats(s_deadlift, 0, 5, 1));
                    add(new ExerciseStats(s_deadlift, 0, 7, 1));
                    add(new ExerciseStats(s_deadlift, 0, 4, 1));
                    add(new ExerciseStats(s_deadlift, 0, 6, 1));
                    add(new ExerciseStats(s_deadlift, 0, 8, 1));
                }
            });
            add(new LinkedList<ExerciseStats>() {
                {
                    add(new ExerciseStats(bench, 0, 5, 1));
                    add(new ExerciseStats(bench, 0, 3, 1));
                    add(new ExerciseStats(bench, 0, 1, 1, push));
                    add(new ExerciseStats(bench, 0, 3, 1));
                    add(new ExerciseStats(bench, 0, 3, 1));
                    add(new ExerciseStats(bench, 0, 3, 1));
                    add(new ExerciseStats(bench, 0, 5, 1));
                    add(new ExerciseStats(bench, 0, 5, 1));
                    add(new ExerciseStats(bench, 0, 5, 1));
                    add(new ExerciseStats(c_g_bench, 0, 6, 1));
                    add(new ExerciseStats(c_g_bench, 0, 5, 1));
                    add(new ExerciseStats(c_g_bench, 0, 3, 1));
                    add(new ExerciseStats(c_g_bench, 0, 5, 1));
                    add(new ExerciseStats(c_g_bench, 0, 7, 1));
                    add(new ExerciseStats(c_g_bench, 0, 4, 1));
                    add(new ExerciseStats(c_g_bench, 0, 6, 1));
                    add(new ExerciseStats(c_g_bench, 0, 8, 1));
                }
            });
            add(new LinkedList<ExerciseStats>() {
                {
                    add(new ExerciseStats(deadlift, 0, 5, 1));
                    add(new ExerciseStats(deadlift, 0, 3, 1));
                    add(new ExerciseStats(deadlift, 0, 1, 1, push));
                    add(new ExerciseStats(deadlift, 0, 3, 1));
                    add(new ExerciseStats(deadlift, 0, 5, 1));
                    add(new ExerciseStats(deadlift, 0, 3, 1));
                    add(new ExerciseStats(deadlift, 0, 5, 1));
                    add(new ExerciseStats(deadlift, 0, 3, 1));
                    add(new ExerciseStats(deadlift, 0, 5, 1));
                    add(new ExerciseStats(front_squat, 0, 5, 1));
                    add(new ExerciseStats(front_squat, 0, 5, 1));
                    add(new ExerciseStats(front_squat, 0, 3, 1));
                    add(new ExerciseStats(front_squat, 0, 5, 1));
                    add(new ExerciseStats(front_squat, 0, 7, 1));
                    add(new ExerciseStats(front_squat, 0, 4, 1));
                    add(new ExerciseStats(front_squat, 0, 6, 1));
                    add(new ExerciseStats(front_squat, 0, 8, 1));
                }
            });
        }
    };
    static final ArrayList<ArrayList<Double>> weight_modifiers = new ArrayList<ArrayList<Double>>() {{
        //Monday
        add(new ArrayList<Double>() {
            {
                //Bench modifiers
                add(.65);
                add(.75);
                add(.85);
                add(.85);
                add(.85);
                add(.8);
                add(.75);
                add(.7);
                add(.65);
                //OHP modifiers
                add(.5);
                add(.6);
                add(.7);
                add(.7);
                add(.7);
                add(.7);
                add(.7);
                add(.7);
            }
        });
        //Tuesday
        add(new ArrayList<Double>() {
            {
                //Squat modifiers
                add(.75);
                add(.85);
                add(.95);
                add(.9);
                add(.85);
                add(.8);
                add(.75);
                add(.7);
                add(.65);
                //Sumo modifiers
                add(.5);
                add(.6);
                add(.7);
                add(.7);
                add(.7);
                add(.7);
                add(.7);
                add(.7);
            }
        });
        //Thursday
        add(new ArrayList<Double>() {
            {
                //bench modifiers
                add(.75);
                add(.85);
                add(.95);
                add(.9);
                add(.85);
                add(.8);
                add(.75);
                add(.7);
                add(.65);
                //CG modifiers
                add(.4);
                add(.5);
                add(.6);
                add(.6);
                add(.6);
                add(.6);
                add(.6);
                add(.6);
            }
        });
        //Friday
        add(new ArrayList<Double>() {
            {
                //Deadlift modifiers
                add(.75);
                add(.85);
                add(.95);
                add(.9);
                add(.85);
                add(.8);
                add(.75);
                add(.7);
                add(.65);
                //Front Squat modifiers
                add(.35);
                add(.45);
                add(.55);
                add(.55);
                add(.55);
                add(.55);
                add(.55);
                add(.55);
            }
        });
    }};
    static final String[] names = {"Monday","Tuesday","Thursday","Friday"};
    static final String[] descrs = {
            "Bench Press and Overhead Press",
            "Squat and Sumo Deadlift",
            "Bench Press and Close Grip Bench Press",
            "Deadlift and Front Squat"
    };

    public NSuns() {
        super();
        this.name = "nSuns 5/3/1";
        this.description = nsun_descr;
        init_workouts();
    }

    public NSuns(int new_id, String n_routine, String n_descr, ArrayList<Workout> n_wkouts) {
        super(new_id, n_routine, n_descr, n_wkouts);
    }

    public void init_workouts() {
        for (int i = 0; i < 4; ++i) {
            this.workouts.add(new Workout(0, names[i], descrs[i], exercises.get(i)));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void init_workout(Workout to_init, HashMap<String, Integer> user_max) {
        int training_max1, training_max2;
        switch ((int) to_init.id) {
            case 0: {
                training_max1 = (int) Math.floor(.90 * user_max.get(String.valueOf(bench)));
                training_max2 = (int) Math.floor(.90 * user_max.get(String.valueOf(oh_press)));
                break;
            }
            case 1: {
                training_max1 = (int) Math.floor(.90 * user_max.get(String.valueOf(squat)));
                training_max2 = (int) Math.floor(.90 * user_max.get(String.valueOf(s_deadlift)));
                break;
            }
            case 2: {
                training_max1 = (int) Math.floor(.90 * user_max.get(String.valueOf(bench)));
                training_max2 = (int) Math.floor(.90 * user_max.get(String.valueOf(c_g_bench)));
                break;
            }
            case 3: {
                training_max1 = (int) Math.floor(.90 * user_max.get(String.valueOf(deadlift)));
                training_max2 = (int) Math.floor(.90 * user_max.get(String.valueOf(front_squat)));
                break;
            }
            default : { return; }
        }
        set_workout_max(to_init, training_max1, training_max2);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private static void set_workout_max(Workout to_init, int train_max1, int train_max2) {
        ArrayList<Double> modifiers = weight_modifiers.get((int) to_init.id);
        int index = 0;
        to_init.statsList.forEach(exercise -> {
            int curr_max = index < 9 ? train_max1 : train_max2;
            exercise.weight = (int) Math.floor(modifiers.get(index) * curr_max);
            //ensure that weight set is divisble evenly by 5
            int remainder = exercise.weight % divisor;
            if (remainder != 0) {
                exercise.weight = exercise.weight + (divisor - remainder);
            }
        });
    }

    public int suggest_increase(int reps_completed) {
        switch (reps_completed) {
            case 0:
            case 1: {
                return 0;
            }
            case 2:
            case 3: {
                return 5;
            }
            case 4:
            case 5: {
                return 10;
            }
            default : {
                return 15;
            }
        }
    }
}
