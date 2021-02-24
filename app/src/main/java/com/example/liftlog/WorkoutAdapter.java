package com.example.liftlog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class WorkoutAdapter extends ArrayAdapter<Workout> {
    private Context mContext;
    private int mResource;
    public WorkoutAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Workout> objects){
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent, false);
        TextView day = convertView.findViewById((R.id.day));
        TextView numExercises = convertView.findViewById(R.id.numExercises);
        TextView workoutDesc = convertView.findViewById((R.id.workoutDesc));
        day.setText("Day " + (position+1));
        System.out.println(getItem(position).statsList.size());
        numExercises.setText("" + getItem(position).statsList.size());
        workoutDesc.setText(getItem(position).description);
        return convertView;
    }
}
