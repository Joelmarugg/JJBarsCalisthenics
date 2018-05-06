package com.jjbars.jjbarscalisthenics;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;


class WRvAdapterKlasse extends RecyclerView.Adapter<WRvAdapterKlasse.WViewHolderKlasse> {

    private ArrayList<String> workoutNames;
    private Context context;


    public class WViewHolderKlasse extends RecyclerView.ViewHolder {

        TextView workout;



        public WViewHolderKlasse(View itemView) {
            super(itemView);
            this.workout = (TextView) itemView.findViewById(R.id.textViewItem);

        }

    }


    public WRvAdapterKlasse(ArrayList<String> workoutNames) {
        this.workoutNames = workoutNames;
    }


    @NonNull
    @Override
    public WViewHolderKlasse onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, null);

        context = itemView1.getContext();

        return new WViewHolderKlasse(itemView1);
    }

    @Override
    public void onBindViewHolder(@NonNull WViewHolderKlasse holder, int position) {

        holder.workout.setText(Html.fromHtml(workoutNames.get(position)));



        holder.itemView.setOnClickListener(vv -> {

            String WorkoutName =  workoutNames.get(position);
            final Intent intent = new Intent(context,show_workout.class);
            intent.putExtra("name",WorkoutName);
            context.startActivity(intent);
            System.out.println(workoutNames.get(position));


        });


    }

    @Override
    public int getItemCount() {
        return workoutNames.size();
    }


}
