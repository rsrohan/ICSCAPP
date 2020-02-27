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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class ScheduleDataFragment extends Fragment {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    private OnFragmentInteractionListener mListener;

    int day;

    public ScheduleDataFragment(int x) {
        day = x;
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_schedule_data, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("SCHEDULES").child(""+day);
        databaseReference.keepSynced(true);
        recyclerView = v.findViewById(R.id.recyclerViewForSchedules);
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
                    RecyclerAdapterForSchedules r = new RecyclerAdapterForSchedules(schedulesArrayList, getContext(), getActivity());
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
