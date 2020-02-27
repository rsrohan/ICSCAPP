package com.example.icscapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class RecyclerAdapterForSpeakers extends RecyclerView.Adapter<RecyclerAdapterForSpeakers.MyHolder> {
    Context context;
    Uri uriImage;
    ArrayList<Speakers> speakersArrayList;
    StorageReference storageReference;

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


        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    storageReference = FirebaseStorage.getInstance().getReference("speakers").child(speakers.getImage());
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
//                            Glide.with(context).asBitmap().load(uri).addListener(new RequestListener<Bitmap>() {
//                                @Override
//                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
//                                    Log.d("TAG", "onLoadFailed: "+e);
//                                    return false;
//                                }
//
//                                @Override
//                                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
//
//                                    try{
//                                        holder.image.setImageBitmap(resource);
//                                    }catch (Exception e){}
//                                    Log.d("TAG", "onResourceReady: "+resource);
//                                    return false;
//                                }
//                            }).submit();
                            speakers.setImage(uri.toString());
                            Glide.with(context).load(uri).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(holder.image);
                            //holder.image.setImageURI(uri);
                        }
                    });


                }catch (Exception e){
                    Log.d("TAG", "onBindViewHolder: "+e);
                }
            }
        });


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

      // showAlert(speakers.getAbout());
        String name = speakers.name;
        String image = speakers.image;
        String about = speakers.about;
        String from = speakers.from;

        Intent intent = new Intent(context, SpeakerDetails.class);
        //Bundle bundle = new Bundle()
        intent.putExtra("NAME", name );
        intent.putExtra("IMAGE", speakers.getImage());
        intent.putExtra("ABOUT", about);
        intent.putExtra("FROM", from);
        context.startActivity(intent);


    }

    @Override
    public int getItemCount() {
        return speakersArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView name, from;
        ImageView image;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageOfSpeaker);
            name = itemView.findViewById(R.id.nameOfSpeaker);
            from = itemView.findViewById(R.id.aboutSpeaker);
        }
    }
//    public void showAlert(String s){
//
//
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
//        LayoutInflater inflater = activity.getLayoutInflater();
//        View alertDialogView = inflater.inflate(R.layout.about_speaker_dialog, null);
//        alertDialog.setView(alertDialogView);
//
//        TextView textDialog = (TextView) alertDialogView.findViewById(R.id.aboutSpeaker);
//        textDialog.setText(s);
//
//        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//        alertDialog.show();
//
//    }
}
