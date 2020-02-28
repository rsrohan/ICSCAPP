package com.example.icscapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapterForSchedules extends RecyclerView.Adapter<RecyclerAdapterForSchedules.MyHolder> {
    Context context;
    ArrayList<Schedules> SchedulesArrayList;

    Activity activity;

    public RecyclerAdapterForSchedules(ArrayList<Schedules> SchedulesArrayList, Context context, Activity activity) {

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
        if (!holder.otherinfo.toString().contains("Venue") || !holder.otherinfo.toString().contains(" "))
        {
            holder.linearLayout.setBackground(context.getResources().getDrawable(R.drawable.buttons_colorprimary_bg));
        }
        if (schedules.getPapername().trim().toLowerCase().equals("tea break")||schedules.getPapername().trim().toLowerCase().equals("lunch"))
        {
            //holder.papername.setTextColor(Color.parseColor("#000000"));
            holder.linearLayout.setBackground(context.getResources().getDrawable(R.drawable.bg4));
        }
        if (schedules.getOtherinfo().trim().length()>12)
        {
            holder.linearLayout.setBackground(context.getResources().getDrawable(R.drawable.sidenavbar));

        }

    }


    @Override
    public int getItemCount() {
        return SchedulesArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView paperid, papername, timing, otherinfo;
        LinearLayout linearLayout;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            paperid = itemView.findViewById(R.id.paperId);
            papername = itemView.findViewById(R.id.paperName);
            timing = itemView.findViewById(R.id.timing);
            otherinfo = itemView.findViewById(R.id.otherInfo);
            linearLayout = itemView.findViewById(R.id.linearlayout);
        }
    }

}
