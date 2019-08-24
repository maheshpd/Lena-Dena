package com.example.lenadena.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lenadena.R;
import com.example.lenadena.model.Dena;

import java.util.List;
import java.util.Random;

public class DenaAdapter extends RecyclerView.Adapter<DenaAdapter.MyDenaViewHolder> {

    Context context;
    List<Dena> list;

    public DenaAdapter(Context context, List<Dena> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyDenaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyDenaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDenaViewHolder holder, int position) {
        Dena dena = list.get(position);
        holder.type.setText(dena.getType());
        holder.name.setText(dena.getName());
        holder.dateRemaning.setText(dena.getTime());
        holder.desc.setText(dena.getDescription());
        holder.phone.setText(dena.getPhone());
        holder.amt.setText("Amt. " + dena.getAmount() + " Rs.");
        holder.firstChar.setText(dena.getName().substring(0, 1));

        Random mRandom = new Random();
        final int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
        ((GradientDrawable) holder.firstChar.getBackground()).setColor(color);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyDenaViewHolder extends RecyclerView.ViewHolder {

        TextView type, dateRemaning, name, desc, phone, amt, firstChar;

        public MyDenaViewHolder(@NonNull View itemView) {
            super(itemView);

            type = itemView.findViewById(R.id.type);
            dateRemaning = itemView.findViewById(R.id.time_remaing);
            name = itemView.findViewById(R.id.name_txt);
            desc = itemView.findViewById(R.id.descr);
            phone = itemView.findViewById(R.id.phoneno);
            amt = itemView.findViewById(R.id.amt);
            firstChar = itemView.findViewById(R.id.firstChar);
        }
    }
}
