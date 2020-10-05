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
import com.example.lenadena.model.Daily;
import com.example.lenadena.model.ItemModel;
import com.example.lenadena.model.SectionModel;

import java.util.ArrayList;
import java.util.List;

public class SectionRecyclerViewAdapter extends RecyclerView.Adapter<SectionRecyclerViewAdapter.SectionViewHolder> {

    Context context;
    List<Daily> sectionModelArrayList;
    List<SectionModel> sectionModelList = new ArrayList<>();
    public SectionRecyclerViewAdapter(Context context, List<SectionModel> sectionModelList) {
        this.context = context;
        this.sectionModelList = sectionModelList;
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_custom_row_layout, parent, false);
        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int position) {
        Daily sm = sectionModelArrayList.get(position);
        String sectionOneName = null;
        ArrayList<ItemModel> sectionList = new ArrayList<>();
        for (int i = 0; i <sectionModelArrayList.size()  ; i++) {

            sectionOneName = sectionModelArrayList.get(i).getCreateDate();

            if (sectionOneName.equals(sectionModelArrayList.get(i).getCreateDate())) {
                String amount = sectionModelArrayList.get(i).getAmount();
                String description = sectionModelArrayList.get(i).getDescription();

                sectionList.add(new ItemModel(description, amount));
            }


        }

        sectionModelList.add(new SectionModel(sectionOneName, "", sectionList));


//        holder.dateTxt.setText(sm.getDate());
//        holder.totalAmtTxt.setText("Total:-" + sm.getTotalPrice() + "Rs.");

        ItemRecyclerViewAdapter adapter = new ItemRecyclerViewAdapter(context, sectionModelList);
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
