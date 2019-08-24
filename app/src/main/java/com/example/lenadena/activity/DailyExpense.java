package com.example.lenadena.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lenadena.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DailyExpense extends AppCompatActivity {

    //Widget
    Button add_lena_date_btn;


    //String
    String sname, sdesc, stime, samt, sphone, stype, screateDate;

    Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_expense);

        //Current Date
        date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        screateDate = df.format(date);

        initWidget();
    }

    private void initWidget() {
        add_lena_date_btn = findViewById(R.id.)
    }
}
