package com.example.lenadena.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lenadena.R;
import com.example.lenadena.model.SectionModel;

import java.util.ArrayList;

public class SectionRecyclerViewAdapter extends RecyclerView.Adapter<SectionRecyclerViewAdapter.SectionViewHolder> {

    Context context;
    ArrayList<SectionModel> sectionModelArrayList;

    public SectionRecyclerViewAdapter(Context context, ArrayList<SectionModel> sectionModelArrayList) {
        this.context = context;
        this.sectionModelArrayList = sectionModelArrayList;
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_custom_row_layout, parent, false);
        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int position) {
        SectionModel sm = sectionModelArrayList.get(position);
        holder.dateTxt.setText(sm.getDate());
        holder.totalAmtTxt.setText("Total:-" + sm.getTotalPrice() + "Rs.");

        ItemRecyclerViewAdapter adapter = new ItemRecyclerViewAdapter(context, sm.getItemModelArrayList());
        holder.itemRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.itemRecyclerView.setHasFixedSize(true);
        holder.itemRecyclerView.setNestedScrollingEnabled(true);
        holder.itemRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return sectionModelArrayList.size();
    }

    public class SectionViewHolder extends RecyclerView.ViewHolder {
        public TextView dateTxt, totalAmtTxt;
        public RecyclerView itemRecyclerView;

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);

            dateTxt = itemView.findViewById(R.id.date);
            totalAmtTxt = itemView.findViewById(R.id.totalAmount);
            itemRecyclerView = itemView.findViewById(R.id.itemRv);

        }
    }
}
