package com.jiit.icscapp;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mzelzoghbi.zgallery.ZGallery;
import com.mzelzoghbi.zgallery.ZGrid;
import com.mzelzoghbi.zgallery.entities.ZColor;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class GalleryFragment extends Fragment {


    RecyclerView recyclerView;
    ArrayList<String> urls;
    SliderView sliderView, sliderView2;
    private SliderAdapterExample adapter, adapter2;

    StorageReference firebaseStorage;
    DatabaseReference databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        //sliderView = root.findViewById(R.id.imageSlider);

        //sliderView2 = root.findViewById(R.id.imageSlider2);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final ArrayList<String> imageArray = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("COUNT_FOR_GALLERY_IMAGE");

        try {


            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    try{
                        int x = Integer.parseInt(String.valueOf(dataSnapshot.getValue(Long.class)));
                        //setImageSlider(x);

                        //setImageSlider2(x);
                        firebaseStorage = FirebaseStorage.getInstance().getReference("gallery");
                        try {
                            for (int i = 1; i <= x; i++) {

                                firebaseStorage.child(i + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {

                                        imageArray.add(uri.toString());

                                        RecyclerAdapterForGallery r= new RecyclerAdapterForGallery(imageArray, getContext(), getActivity());
                                        r.notifyDataSetChanged();
                                        //progressBar.setVisibility(View.GONE);
                                        recyclerView.setAdapter(r);


                                    }
                                });
                                //Log.d(TAG, "renewItems: "+storageReference.child(""+i+".jpg").getDownloadUrl().toString());
                            }


                        } catch (Exception e) {
                            Log.d(TAG, "renewItems: " + e);
                        }

                    }catch (Exception e)
                    {

                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }catch (Exception e){}

        return root;
    }



    private void setImageSlider(int x) {

        firebaseStorage = FirebaseStorage.getInstance().getReference("gallery");

        adapter = new SliderAdapterExample(getContext());
        renewItems2(firebaseStorage, 1,x/2);
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(5);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

    }
    private void setImageSlider2(int x) {

        firebaseStorage = FirebaseStorage.getInstance().getReference("gallery");

        adapter2 = new SliderAdapterExample(getContext());
        renewItems(firebaseStorage, (x/2)+1, x);
        sliderView2.setSliderAdapter(adapter2);
        sliderView2.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView2.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView2.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView2.setIndicatorSelectedColor(Color.WHITE);
        sliderView2.setIndicatorUnselectedColor(Color.GRAY);
        sliderView2.setScrollTimeInSec(5);
        sliderView2.setAutoCycle(true);
        sliderView2.startAutoCycle();

    }

    public void renewItems(final StorageReference storageReference, int start,int x) {


        final List<SliderItem> sliderItemList = new ArrayList<>();

        try {
            for (int i = start; i <= x; i++) {

                storageReference.child(i + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        SliderItem sliderItem = new SliderItem();
                        sliderItem.setDescription("");
                        sliderItem.setImageUrl(uri.toString());
                        sliderItemList.add(sliderItem);
                        Log.d(TAG, "onSuccess: " + uri.toString());
                        adapter.renewItems(sliderItemList);
                    }
                });
                //Log.d(TAG, "renewItems: "+storageReference.child(""+i+".jpg").getDownloadUrl().toString());
            }
        } catch (Exception e) {
            Log.d(TAG, "renewItems: " + e);
        }

    }
    public void renewItems2(final StorageReference storageReference, int start,int x) {


        final List<SliderItem> sliderItemList = new ArrayList<>();

        try {
            for (int i = start; i <= x; i++) {

                storageReference.child(i + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        SliderItem sliderItem = new SliderItem();
                        sliderItem.setDescription("");
                        sliderItem.setImageUrl(uri.toString());
                        sliderItemList.add(sliderItem);
                        Log.d(TAG, "onSuccess: " + uri.toString());
                        adapter2.renewItems(sliderItemList);
                    }
                });
                //Log.d(TAG, "renewItems: "+storageReference.child(""+i+".jpg").getDownloadUrl().toString());
            }
        } catch (Exception e) {
            Log.d(TAG, "renewItems: " + e);
        }

    }

}