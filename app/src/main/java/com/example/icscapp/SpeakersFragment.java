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

import java.util.ArrayList;


public class SpeakersFragment extends Fragment {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    private OnFragmentInteractionListener mListener;
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
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Speakers> speakersArrayList = new ArrayList<>();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    Speakers s = dataSnapshot1.getValue(Speakers.class);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
