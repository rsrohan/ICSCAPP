package com.example.icscapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class Commitee extends Fragment {

    private String[] comm = {"Advisory Committee", "Technical Program Committee", "Organising Committee" };
    private Spinner comm_spinner;
    private RecyclerView rv;
    private ArrayAdapter adapter;
    int comm_select_pos;
    private RecyclerView mrecyclerView;
    private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager mlayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_commitee, container, false);

/*
        comm_spinner = root.findViewById(R.id.comm_options);
        rv = root.findViewById(R.id.comm_list);
        adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,comm);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        comm_spinner.setAdapter(adapter);

        comm_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                comm_select_pos = comm_spinner.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
*/


        return root;
    }
}


