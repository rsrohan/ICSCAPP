package com.jiit.icscapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapterForCommittee extends RecyclerView.Adapter<RecyclerAdapterForCommittee.MyHolder> {
    Context context;
    ArrayList<committee_class> committee_classes;

    public RecyclerAdapterForCommittee(ArrayList<committee_class> urls, Context context) {

        this.context = context;
        this.committee_classes = urls;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_for_advisory, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {

        holder.name.setText(committee_classes.get(position).getname());

        String s = committee_classes.get(position).getdesg();
        if (s.equals(" "))
        {
            holder.desg.setVisibility(View.GONE);
        }else {
            holder.desg.setText(s);

        }

    }



    @Override
    public int getItemCount() {
        return committee_classes.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView name, desg;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            desg = itemView.findViewById(R.id.desg);


        }
    }

}
