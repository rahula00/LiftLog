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

public class WorkoutExerciseAdapter extends ArrayAdapter<Exercise> {
    private Context mContext;
    private int mResource;
    public WorkoutExerciseAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Exercise> objects){
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
        TextView exerciseName = convertView.findViewById(R.id.exerciseName);
        TextView exerciseDesc = convertView.findViewById((R.id.exerciseDesc));
        day.setText("Day " + (position+1));
        exerciseName.setText(getItem(position).name);
        exerciseDesc.setText(getItem(position).instructions);
        return convertView;
    }
}
