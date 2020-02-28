package com.jiit.icscapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiit.icscapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class PrevConfFragment extends Fragment {

    SliderView sliderView;
    private static final String TAG = "tag";
    TextView year;
    private SliderAdapterExample adapter;
    DatabaseReference databaseReference, ref;
    RecyclerView rv;
    RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager mlayoutManager;
    ArrayList<adapterPrev> history = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_prev_conf, container, false);
        year = v.findViewById(R.id.year);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference("previousEditions");
        databaseReference.keepSynced(true);

        rv = v.findViewById(R.id.recycler1);
        rv.setHasFixedSize(true);

        mlayoutManager =new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);

        //madapter = new ItemAdapter(history, getContext());
        rv.setLayoutManager(mlayoutManager);
        //rv.setAdapter(madapter);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<adapterPrev> list = new ArrayList<>();
                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                        {
                            Log.d("Tag", "onDataChange: "+dataSnapshot1.getValue());

                            adapterPrev s = dataSnapshot1.getValue(adapterPrev.class);
                            assert s != null;
                            list.add(s);
                        }
                        ItemAdapter r= new ItemAdapter(list, getContext());
                        r.notifyDataSetChanged();
                        //progressBar.setVisibility(View.GONE);
                        rv.setAdapter(r);
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