package com.example.icscapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class GalleryFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

//        ViewPager vp= root.findViewById(R.id.imageSlider);
//        ImageAdapter adapter = new ImageAdapter(getActivity());
//        vp.setAdapter(adapter);

        return root;
    }
}