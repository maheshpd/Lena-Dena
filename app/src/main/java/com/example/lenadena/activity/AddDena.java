package com.example.lenadena.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.lenadena.R;

import java.util.Calendar;

public class AddDena extends AppCompatActivity implements View.OnClickListener {

    //Widget
    Button add_dena_date_btn;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dena);

        //initWidget
        initWidget();
    }

    private void initWidget() {
        add_dena_date_btn = findViewById(R.id.add_lena_date_btn);

        add_dena_date_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.add_lena_date_btn) {
            //Get current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        add_dena_date_btn.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                }
            },mYear,mMonth,mDay);
            datePickerDialog.show();

        }
    }


}
