package com.example.icscapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class RecyclerAdapterForSpeakers extends RecyclerView.Adapter<RecyclerAdapterForSpeakers.MyHolder> {
    Context context;
    ArrayList<Speakers> speakersArrayList;

    Activity activity;
    public RecyclerAdapterForSpeakers(ArrayList<Speakers> speakersArrayList, Context context, Activity activity) {

        this.context = context;
        this.speakersArrayList = speakersArrayList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_for_speakers, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {

        final Speakers speakers =speakersArrayList.get(position);
        Log.d("Tag", "onBindViewHolder: "+speakers.getName());

        try{
            Glide.with(context).asBitmap().load("gs://icsc-app-b1ddb.appspot.com/james.jpg").addListener(new RequestListener<Bitmap>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                    Log.d("TAG", "onLoadFailed: "+e);
                    return false;
                }

                @Override
                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                    holder.image.setImageBitmap(resource);
                    Log.d("TAG", "onResourceReady: "+resource);
                    return false;
                }
            }).submit();
            //holder.image.setImageURI(Uri.parse(speakers.getImage()));

        }catch (Exception e){
            Log.d("TAG", "onBindViewHolder: "+e);
        }
        holder.name.setText(speakers.getName());
        holder.from.setText(speakers.getFrom());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetailsofSpeaker(speakers);
            }
        });
    }

    private void showDetailsofSpeaker(Speakers speakers) {

       showAlert(speakers.getAbout());
    }

    @Override
    public int getItemCount() {
        return speakersArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView name, from;
        CircleImageView image;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageOfSpeaker);
            name = itemView.findViewById(R.id.nameOfSpeaker);
            from = itemView.findViewById(R.id.aboutSpeaker);
        }
    }
    public void showAlert(String s){


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View alertDialogView = inflater.inflate(R.layout.about_speaker_dialog, null);
        alertDialog.setView(alertDialogView);

        TextView textDialog = (TextView) alertDialogView.findViewById(R.id.aboutSpeaker);
        textDialog.setText(s);

        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();

    }
}