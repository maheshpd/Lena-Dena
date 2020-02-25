package com.example.lenadena.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lenadena.Common;
import com.example.lenadena.R;
import com.example.lenadena.activity.AddDena;
import com.example.lenadena.model.Dena;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

public class DenaAdapter extends RecyclerView.Adapter<DenaAdapter.MyDenaViewHolder> {

    private Context context;
    private List<Dena> list;
    private Handler handler;
    private Runnable runnable;
    private LayoutInflater layoutInflater;

    Realm realm;

    public DenaAdapter(Context context, List<Dena> list) {
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyDenaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        realm = Realm.getDefaultInstance();
        return new MyDenaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDenaViewHolder holder, final int position) {
        Dena dena = list.get(position);
        holder.type.setText(dena.getType());
        holder.name.setText(dena.getName());

        holder.desc.setText(dena.getDescription());
        holder.phone.setText(dena.getPhone());
        holder.amt.setText("Amt. " + dena.getAmount() + " Rs.");
        holder.firstChar.setText(dena.getName().substring(0, 1));

        Random mRandom = new Random();
        final int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
        ((GradientDrawable) holder.firstChar.getBackground()).setColor(color);

        String createdDateString = dena.getCreateDate();
        String expireDateString = dena.getTime();


        countDownStart(holder, expireDateString);

        Dena dena1 = list.get(position);
        final int numPosition = dena1.getId();

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Select Options")
                        .setItems(new String[]{"Delete", "Update"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                switch (i) {
                                    case 0:

                                        Delete(position);
                                        break;
                                    case 1:
                                        Intent intent = new Intent(context, AddDena.class);
                                        Common.position = numPosition;
                                        Common.posFromDena = true;
                                        context.startActivity(intent);
                                        break;

                                }
                            }
                        }).show();
                return true;
            }
        });

    }

    private void Delete(final int position) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Dena> rows = realm.where(Dena.class).equalTo("id", position).findAll();
                rows.deleteAllFromRealm();
                list.remove(position);
            }
        });
    }


    public void countDownStart(final MyDenaViewHolder holder, final String expiredate) {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    // Please here set your event date//YYYY-MM-DD
                    Date futureDate = dateFormat.parse(expiredate);
                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime()
                                - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        holder.dateRemaning.setText("" + String.format("%02d", days) + " Day Left " + String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
//                        txtTimerHour.setText("" + String.format("%02d", hours));
//                        txtTimerMinute.setText(""
//                                + String.format("%02d", minutes));
//                        txtTimerSecond.setText(""
//                                + String.format("%02d", seconds));
                    } else {
                        holder.dateRemaning.setText("Time Over");
                        holder.dateRemaning.setTextColor(R.color.red);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyDenaViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/ {

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
