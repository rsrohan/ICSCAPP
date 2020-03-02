package com.jiit.icscapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapterForPosters extends RecyclerView.Adapter<RecyclerAdapterForPosters.MyHolder> {
    Context context;
    ArrayList<Schedules> SchedulesArrayList;

    Activity activity;

    public RecyclerAdapterForPosters(ArrayList<Schedules> SchedulesArrayList, Context context, Activity activity) {

        this.context = context;
        this.SchedulesArrayList = SchedulesArrayList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_for_schedule, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {

        final Schedules schedules = SchedulesArrayList.get(position);
        Log.d("Tag", "onBindViewHolder: " + schedules.getPapername());

        holder.paperid.setText(schedules.getPaperid());
        holder.papername.setText(schedules.getPapername());
        holder.timing.setText(schedules.getTiming());
        holder.otherinfo.setText(schedules.getOtherinfo());
//        if (!holder.otherinfo.toString().contains("Venue") || !holder.otherinfo.toString().contains(" "))
//        {
//            holder.linearLayout.setBackground(context.getResources().getDrawable(R.drawable.img_light_5));
//        }
//        if (schedules.getPapername().trim().toLowerCase().equals("tea break")||schedules.getPapername().trim().toLowerCase().equals("lunch"))
//        {
//            holder.linearLayout.setBackground(context.getResources().getDrawable(R.drawable.img2_11));
//            //holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_free_breakfast_black_24dp));
//        }
//        if (schedules.getOtherinfo().trim().length()>12 && !schedules.getOtherinfo().trim().toLowerCase().contains("venue")&&!schedules.getOtherinfo().trim().toLowerCase().contains("auditorium"))
//        {
//            holder.linearLayout.setBackground(context.getResources().getDrawable(R.drawable.sidenavbar2));
//        }
        if (schedules.getPapername().trim().toLowerCase().contains("postersession"))
        {
            holder.linearLayout.setBackground(context.getResources().getDrawable(R.drawable.img_light_5));

        }else{
            holder.linearLayout.setBackground(context.getResources().getDrawable(R.drawable.sidenavbar2));
        }

    }


    @Override
    public int getItemCount() {
        return SchedulesArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView paperid, papername, timing, otherinfo;
        LinearLayout linearLayout;

        ImageView imageView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            paperid = itemView.findViewById(R.id.paperId);
            papername = itemView.findViewById(R.id.paperName);
            timing = itemView.findViewById(R.id.timing);
            otherinfo = itemView.findViewById(R.id.otherInfo);
            linearLayout = itemView.findViewById(R.id.linearlayout);
            imageView = itemView.findViewById(R.id.image);
        }
    }

}
