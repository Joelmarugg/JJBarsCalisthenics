package com.jjbars.jjbarscalisthenics;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


import static com.jjbars.jjbarscalisthenics.CreateCustomWorkout.good;

public class RvAdapterKlasse extends RecyclerView.Adapter<RvAdapterKlasse.ViewHolderKlasse> {

    private ArrayList<String> dataset;



    public class ViewHolderKlasse extends RecyclerView.ViewHolder{

        TextView exercise;




        public ViewHolderKlasse(View itemView) {
            super(itemView);
            this.exercise = (TextView) itemView.findViewById(R.id.textViewItem);


        }
    }

    public RvAdapterKlasse(ArrayList <String> data){this.dataset = data;}


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolderKlasse onCreateViewHolder(ViewGroup viewGroup, int i) {

        View itemView1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, null);


        return new ViewHolderKlasse(itemView1);
    }

    @Override
    public void onBindViewHolder(ViewHolderKlasse viewHolderKlasse, final int i) {

        viewHolderKlasse.exercise.setText(Html.fromHtml(dataset.get(i)));



                viewHolderKlasse.itemView.setOnClickListener(vv -> {
                            CreateCustomWorkout.setSelectedList(dataset.get(i));
                            CreateCustomWorkout.delSelectedList(dataset.get(i));
                });





    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
