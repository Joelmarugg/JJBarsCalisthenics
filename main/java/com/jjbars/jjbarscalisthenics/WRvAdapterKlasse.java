package com.jjbars.jjbarscalisthenics;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Iterator;

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

        holder.itemView.setOnLongClickListener((View vv) -> {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("Do you want to delete " + workoutNames.get(position) +" ?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(context,"deleted workout for you",Toast.LENGTH_SHORT).show();
                            ArrayList<Workout> list;
                            list = workout_saveloader.loadData(context);

                            Iterator<Workout> i = list.iterator();
                            while (i.hasNext()){
                                if (( i.next().getTitle().equals(workoutNames.get(position)))){
                                    i.remove();
                                    break;
                                }
                            }

                            workout_saveloader.saveData(context,list);

                            dialog.cancel();
                            workoutNames.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, list.size());

                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(context,"not deleted workout",Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();

            return true;
        });




    }

    @Override
    public int getItemCount() {
        return workoutNames.size();
    }


}
