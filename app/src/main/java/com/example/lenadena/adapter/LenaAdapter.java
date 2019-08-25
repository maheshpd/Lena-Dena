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
import com.example.lenadena.model.Lena;

import java.util.List;
import java.util.Random;

public class LenaAdapter extends RecyclerView.Adapter<LenaAdapter.MyDenaViewHolder> {

    Context context;
    List<Lena> list;

    public LenaAdapter(Context context, List<Lena> list) {
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
        Lena lena = list.get(position);
        holder.type.setText(lena.getType());
        holder.name.setText(lena.getName());

        holder.desc.setText(lena.getDescription());
        holder.phone.setText(lena.getPhone());
        holder.amt.setText("Amt. " + lena.getAmount() + " Rs.");
        holder.firstChar.setText(lena.getName().substring(0, 1));

        Random mRandom = new Random();
        final int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
        ((GradientDrawable) holder.firstChar.getBackground()).setColor(color);

        String createdDateString = lena.getCreateDate();
        String expireDateString = lena.getTime();

//        int remaingDate = getCountOfDays(createdDateString,expireDateString);
//        holder.dateRemaning.setText(remaingDate);
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


//    public int getCountOfDays(String createdDateString, String expireDateString) {
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//        Date createdConvertedDate = null;
//        Date expireCovertedDate = null;
//        try {
//            createdConvertedDate = dateFormat.parse(createdDateString);
//            expireCovertedDate = dateFormat.parse(expireDateString);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//
//        Calendar start = new GregorianCalendar();
//        start.setTime(createdConvertedDate);
//
//        Calendar end = new GregorianCalendar();
//        end.setTime(expireCovertedDate);
//
//        long diff = end.getTimeInMillis() - start.getTimeInMillis();
//
//        float dayCount = (float) diff / (24 * 60 * 60 * 1000);
//
//
//        return (int) (dayCount);
//    }

}
