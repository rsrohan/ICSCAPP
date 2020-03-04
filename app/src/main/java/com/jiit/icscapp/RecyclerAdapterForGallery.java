package com.jiit.icscapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class RecyclerAdapterForGallery extends RecyclerView.Adapter<RecyclerAdapterForGallery.MyHolder> {
    Context context;
    Uri uriImage;
    ArrayList<String> StringArrayList;
    StorageReference storageReference;

    Activity activity;
    public RecyclerAdapterForGallery(ArrayList<String> StringArrayList, Context context, Activity activity) {

        this.context = context;
        this.StringArrayList = StringArrayList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.recyclerviewfor_gallery, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {

        final String string =StringArrayList.get(position);
        //Log.d("Tag", "onBindViewHolder: "+String.getName());

        Glide.with(context).load(string).into(holder.image);

       

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageDialog(string);
            }
        });

       

    }

   

    @Override
    public int getItemCount() {
        return StringArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView image;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.galleryimage);

        }
    }
    private void showImageDialog(String imagePath) {

        if (imagePath!=null)
        {
            ViewReceiptImage viewReceiptImage = new ViewReceiptImage();
            viewReceiptImage.showDialog(activity, imagePath);
        }
    }

}
