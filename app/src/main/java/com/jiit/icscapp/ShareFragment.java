package com.jiit.icscapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.jiit.icscapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ShareFragment extends Fragment {


    private ImageView qr;
    FirebaseStorage firebaseStorage;
    StorageReference store,ref;
    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_share, container, false);

        qr = root.findViewById(R.id.qrcode);

        store = firebaseStorage.getInstance().getReference("qrcodes");
        ref = store.child("frame.png");




        try{
            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
//                    Glide.with(getActivity().getApplicationContext()).asBitmap().load(uri).addListener(new RequestListener<Bitmap>() {
//                        @Override
//                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
//                           Log.d("TAG", "onLoadFailed: "+e);
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
//                            qr.setImageBitmap(resource);
//                            Glide.with(getActivity().getApplicationContext()).load(resource).into(qr);
//                            Log.d("TAG", "onResourceReady: "+resource);
//                            return false;
//                        }
//                    }).submit();
                    Glide.with(getActivity().getApplicationContext()).load(uri).into(qr);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

        }catch(Exception e){

        }



        return root;


    }



    /*private void download() {
        store = firebaseStorage.getInstance().getReference("qrcodes");
        ref = store.child("frame.png");

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(getActivity(),"qrcode",".png", DIRECTORY_DOWNLOADS,url );

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }
    public void downloadFiles(Context context, String filename, String fileExt, String DestinationDir, String url){

        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);

        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, DestinationDir, filename + fileExt);

        downloadManager.enqueue(request);

    }*/
}