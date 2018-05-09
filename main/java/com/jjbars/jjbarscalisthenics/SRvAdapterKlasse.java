package com.jjbars.jjbarscalisthenics;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


class SRvAdapterKlasse extends RecyclerView.Adapter<SRvAdapterKlasse.SViewHolderKlasse> {


    public ArrayList<String> toShow;
    public Context context;

    public class SViewHolderKlasse extends RecyclerView.ViewHolder {

        TextView workout;
        EditText reps_number;



        public SViewHolderKlasse(View itemView) {
            super(itemView);
            this.workout = (TextView) itemView.findViewById(R.id.textExercise);
            this.reps_number = (EditText) itemView.findViewById(R.id.reps_number);
        }

    }




    public SRvAdapterKlasse(ArrayList<String> toShow) {
        this.toShow=toShow;
    }

    @NonNull
    @Override
    public SViewHolderKlasse onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.reps_layout, null);

        context = itemView1.getContext();

        return new SViewHolderKlasse(itemView1);
    }

    @Override
    public void onBindViewHolder(@NonNull SViewHolderKlasse holder, int position) {

        holder.workout.setText(Html.fromHtml(toShow.get(position)));

        holder.itemView.setOnClickListener(vv -> {
            //final Intent intent = new Intent(context,show_workout.class);
           // context.startActivity(intent);
            //System.out.println(workoutNames.get(position));
            //SavedWorkouts.goToShowWorkout(workoutNames.get(position));
            //go to workout activity
        });

    }

    @Override
    public int getItemCount() {
        return toShow.size();
    }
}
