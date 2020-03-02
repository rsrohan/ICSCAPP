package com.jiit.icscapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class poster_data_fragment extends Fragment {


    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    private OnFragmentInteractionListener mListener;
    int day;



    public poster_data_fragment(int x) {
        // Required empty public constructor
        day = x;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_poster_data_fragment, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("SCHEDULES").child(""+day);
        databaseReference.keepSynced(true);
        recyclerView = v.findViewById(R.id.recyclerViewForPosters);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressBar = v.findViewById(R.id.progressbar);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try{
                    ArrayList<Schedules> schedulesArrayList = new ArrayList<>();
                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        Log.d(TAG, "onDataChange: "+dataSnapshot1.getValue());
                        Schedules s = dataSnapshot1.getValue(Schedules.class);
                        schedulesArrayList.add(s);
                    }
                    RecyclerAdapterForPosters r = new RecyclerAdapterForPosters(schedulesArrayList, getContext(), getActivity());
                    r.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);

                    recyclerView.setAdapter(r);
                }catch (Exception e){}


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return v;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
