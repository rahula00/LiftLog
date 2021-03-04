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

public class RoutinesAdapter extends ArrayAdapter<Routine> {
    private Context mContext;
    private int mResource;
    public RoutinesAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Routine> objects){
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent, false);
        TextView routineName = convertView.findViewById((R.id.routinesName));
        TextView routineDesc = convertView.findViewById(R.id.routinesDesc);
        routineName.setText(getItem(position).name);
        routineDesc.setText(getItem(position).description);
        return convertView;
    }
}
