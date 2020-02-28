package com.example.icscapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    StorageReference storageReference;
    Activity activity;
    ArrayList<adapterPrev> Arraylist;
    Context context;
    public static int c = 1;
    private String TAG = "tag";

    public ItemAdapter(ArrayList<adapterPrev> arraylist, Context context, Activity activity) {
        this.context = context;
        this.Arraylist = arraylist;
        this.activity = activity;
    }


    private ArrayList<adapterPrev> mitemlist;

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView year, info;
        public ImageView image;
        public Button btn;

        public ItemViewHolder(@NonNull final View itemView) {
            super(itemView);
            year = itemView.findViewById(R.id.year);
            info = itemView.findViewById(R.id.infoHistory);
            info.setVisibility(itemView.GONE);
            image = itemView.findViewById(R.id.imagehistory);
            btn = itemView.findViewById(R.id.know_more);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (info.getVisibility()==View.VISIBLE)
                    {
                        info.setVisibility(itemView.GONE);
                    }else{
                        info.setVisibility(itemView.VISIBLE);

                    }
//                    c = c * -1;
//                    if (c < 0) {
//                    }
//
//                    if (c > 0) {
//                    }

                }
            });

        }
    }

    public ItemAdapter(ArrayList<adapterPrev> ItemList, Context c) {

        mitemlist = ItemList;
        this.context = c;

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.prev_icsc_recycler, parent, false);

        //Button btn = v.findViewById(R.id.know_more);
        ItemViewHolder ivh = new ItemViewHolder(v);

        return ivh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position) {
        final adapterPrev currentItem = mitemlist.get(position);

        holder.year.setText(currentItem.getYear());
        holder.info.setText(currentItem.getInfo());


        //     activity.runOnUiThread(new Runnable() {
        ///       @Override
        //   public void run() {
        Log.d(TAG, "onBindViewHolder: " + currentItem.getimage());

        try {
            storageReference = FirebaseStorage.getInstance().getReference("prev_editions").child(currentItem.getimage());
            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Log.d(TAG, "onSuccess: " + uri);

                    Glide.with(context.getApplicationContext()).load(uri).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(holder.image);
                    //holder.image.setImageURI(uri);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "onFailure: " + e);
                }
            });


        } catch (Exception e) {
            Log.d("TAG", "onBindViewHolder: " + e);
        }
        //  }
        //  });


    }

    @Override
    public int getItemCount() {
        return mitemlist.size();
    }


}
