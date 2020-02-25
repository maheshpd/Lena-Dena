package com.example.lenadena.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lenadena.R;
import com.example.lenadena.adapter.SectionRecyclerViewAdapter;
import com.example.lenadena.model.ItemModel;
import com.example.lenadena.model.SectionModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyExpanseFragment extends Fragment {

    RecyclerView sectionRv;
    Context context;

    public DailyExpanseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daily_expanse, container, false);

        init(view);
        context = view.getContext();
//        context.getApplicationContext();

        return view;
    }

    private void init(View view) {

        sectionRv = view.findViewById(R.id.sectionRv);
        populateRecyclerView();
        sectionRv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        sectionRv.setHasFixedSize(true);
        sectionRv.setNestedScrollingEnabled(true);

    }

    //populate recycler view
    private void populateRecyclerView() {
        ArrayList<SectionModel> sectionModelArrayList = new ArrayList<>();
        //for loop for sections
        for (int i = 1; i <= 5; i++) {
            ArrayList<ItemModel> itemArrayList = new ArrayList<>();
            //for loop for items
            for (int j = 1; j <= 10; j++) {
//                itemArrayList.add("Item","1234");
//                itemArrayList.add(new SectionModel("25/02/2020", "20",itemArrayList));
                itemArrayList.add(new ItemModel("Hii", "20"));
            }

            //add the section and items to array list
            sectionModelArrayList.add(new SectionModel("25/02/2020", "20", itemArrayList));
        }

        SectionRecyclerViewAdapter adapter = new SectionRecyclerViewAdapter(context, sectionModelArrayList);
        sectionRv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}
