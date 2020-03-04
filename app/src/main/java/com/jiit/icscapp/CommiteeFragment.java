package com.jiit.icscapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class CommiteeFragment extends Fragment {

    private String[] comm = {"Advisory Committee", "Technical Program Committee", "Organising Committee" };

    TextView advisory, technical, organising;
    private DatabaseReference databaseReference;
    LinearLayout ll, ll2, ll3, ll4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root =  inflater.inflate(R.layout.fragment_commitee, container, false);

        ll = root.findViewById(R.id.impPeople);
        ll2 = root.findViewById(R.id.hariompage);
        ll3 = root.findViewById(R.id.shwetapage);
        ll4 = root.findViewById(R.id.vikrampage);

        advisory = root.findViewById(R.id.advisory);
        technical = root.findViewById(R.id.technical);
        organising = root.findViewById(R.id.organising);
        final RecyclerView advisoryR = root.findViewById(R.id.rec_advisory);
        advisoryR.setHasFixedSize(true);
        advisoryR.setLayoutManager(new LinearLayoutManager(getContext()));
        final RecyclerView technicalR = root.findViewById(R.id.rec_tech);
        technicalR.setHasFixedSize(true);
        technicalR.setLayoutManager(new LinearLayoutManager(getContext()));
        final RecyclerView organisingR = root.findViewById(R.id.rec_organising);
        organisingR.setHasFixedSize(true);
        organisingR.setLayoutManager(new LinearLayoutManager(getContext()));

        databaseReference = FirebaseDatabase.getInstance().getReference("COMMITTEES");
        databaseReference.keepSynced(true);

        advisory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (advisoryR.getVisibility()==View.VISIBLE){
                    advisoryR.setVisibility(View.GONE);
                    //advisory.setCompoundDrawables(null, null, getResources().getDrawable(R.drawable.ic_arrow_drop_down_black_24dp), null);
                }
                else{
                    //advisory.setCompoundDrawables(null, null, getResources().getDrawable(R.drawable.ic_arrow_drop_up_black_24dp), null);

                    setUpAdvisory(root, databaseReference.child("ADVISORY"), advisoryR);
                }
            }
        });
        technical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (technicalR.getVisibility()==View.VISIBLE){
                    technicalR.setVisibility(View.GONE);
                    //technical.setCompoundDrawables(null, null, getResources().getDrawable(R.drawable.ic_arrow_drop_down_black_24dp), null);
                }
                else{
                    //technical.setCompoundDrawables(null, null, getResources().getDrawable(R.drawable.ic_arrow_drop_up_black_24dp), null);

                    setUpAdvisory(root, databaseReference.child("TECHNICAL"), technicalR);
                }
            }
        });


        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.jiit.ac.in/director-sector-%E2%80%93-128";
                Intent web = new Intent(Intent.ACTION_VIEW);
                web.setData(Uri.parse(url));
                startActivity(web);
            }
        });

        ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.jiit.ac.in/prof-shweta-srivastava";
                Intent web = new Intent(Intent.ACTION_VIEW);
                web.setData(Uri.parse(url));
                startActivity(web);

            }
        });

        ll4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.jiit.ac.in/dr-vikram-karwal";
                Intent web = new Intent(Intent.ACTION_VIEW);
                web.setData(Uri.parse(url));
                startActivity(web);

            }
        });




        organising.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (organisingR.getVisibility()==View.VISIBLE){
                    organisingR.setVisibility(View.GONE);
                    //technical.setCompoundDrawables(null, null, getResources().getDrawable(R.drawable.ic_arrow_drop_down_black_24dp), null);
                }
                else{
                    //technical.setCompoundDrawables(null, null, getResources().getDrawable(R.drawable.ic_arrow_drop_up_black_24dp), null);

                    setUpOrg(root, databaseReference.child("ORGANISING"), organisingR);
                }

                if(ll.getVisibility() == View.VISIBLE){
                    ll.setVisibility(View.GONE);
                }

                else{
                    ll.setVisibility(View.VISIBLE);
                }
            }
        });
        return root;
    }

    private void setUpOrg(View root, DatabaseReference organising, final RecyclerView r) {
        r.setVisibility(View.VISIBLE);
        organising.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<committee_class> committee_classes = new ArrayList<>();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    committee_class c = new committee_class();
                    c.setname(dataSnapshot1.getValue(String.class));
                    c.setdesg(" ");
                    committee_classes.add(c);

                }

                RecyclerAdapterForCommittee recyclerAdapterForGallery = new RecyclerAdapterForCommittee(committee_classes, getContext());
                recyclerAdapterForGallery.notifyDataSetChanged();
                r.setAdapter(recyclerAdapterForGallery);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void setUpAdvisory(View root, DatabaseReference databaseReference, final RecyclerView r) {

        r.setVisibility(View.VISIBLE);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<committee_class> committee_classes = new ArrayList<>();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    committee_class c = dataSnapshot1.getValue(committee_class.class);
                    committee_classes.add(c);

                }

                RecyclerAdapterForCommittee recyclerAdapterForGallery = new RecyclerAdapterForCommittee(committee_classes, getContext());
                recyclerAdapterForGallery.notifyDataSetChanged();
                r.setAdapter(recyclerAdapterForGallery);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}


