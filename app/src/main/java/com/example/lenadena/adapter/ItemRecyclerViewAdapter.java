package com.example.lenadena.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lenadena.R;
import com.example.lenadena.model.Daily;
import com.example.lenadena.model.ItemModel;
import com.example.lenadena.model.SectionModel;

import java.util.ArrayList;
import java.util.List;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ItemViewHolder> {

    Context context;
    List<SectionModel> itemModelArrayList;

    public ItemRecyclerViewAdapter(Context context, List<SectionModel> itemModelArrayList) {
        this.context = context;
        this.itemModelArrayList = itemModelArrayList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_expense_item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        SectionModel daily = itemModelArrayList.get(position);

        String name = daily.date;

        /*holder.descTxt.setText(daily.getDescription());



        holder.amount.setText("Rs." + daily.getAmount());*/
    }

    @Override
    public int getItemCount() {
        return itemModelArrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView descTxt, amount;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            descTxt = itemView.findViewById(R.id.descTxt);
            amount = itemView.findViewById(R.id.priceTxt);
        }
    }
}
