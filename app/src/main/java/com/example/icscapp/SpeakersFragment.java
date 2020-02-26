package com.example.icscapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class SpeakersFragment extends Fragment {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    public SpeakersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =LayoutInflater.from(getContext()).inflate(R.layout.fragment_speakers, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference("SPEAKERS");
        databaseReference.keepSynced(true);
        recyclerView = v.findViewById(R.id.recyclerViewForSpeakers);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressBar = v.findViewById(R.id.progressbar);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<Speakers> speakersArrayList = new ArrayList<>();
                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                        {
                            Speakers s = dataSnapshot1.getValue(Speakers.class);
                            assert s != null;
                            Log.d("Tag", "onDataChange: "+s.getName());
                            speakersArrayList.add(s);
                        }
                        RecyclerAdapterForSpeakers r= new RecyclerAdapterForSpeakers(speakersArrayList, getContext(), getActivity());
                        r.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setAdapter(r);
                        //Toast.makeText(getContext(), ""+speakersArrayList.size(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        return v;
    }





}
