package com.example.icscapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class RecyclerAdapterForGallery extends RecyclerView.Adapter<RecyclerAdapterForGallery.MyHolder> {
    Context context;
    ArrayList<String> urls;

    public RecyclerAdapterForGallery(ArrayList<String> urls, Context context) {

        this.context = context;
        this.urls = urls;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.gallery_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {

        
    }



    @Override
    public int getItemCount() {
        return urls.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView image;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.galleryimage);

        }
    }

}
