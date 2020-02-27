package com.example.icscapp;

import android.content.Context;
import android.graphics.Color;
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
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.icscapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

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

        mlayoutManager =new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);

        madapter = new ItemAdapter(history);
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
                            adapterPrev s = dataSnapshot1.getValue(adapterPrev.class);
                            assert s != null;
                            //Log.d("Tag", "onDataChange: "+s.getName());
                            list.add(s);
                        }
                        ItemAdapter r= new ItemAdapter(list);
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