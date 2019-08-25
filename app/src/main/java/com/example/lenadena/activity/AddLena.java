package com.example.lenadena.activity;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lenadena.Client.LenaDatabaseClient;
import com.example.lenadena.R;
import com.example.lenadena.model.Lena;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddLena extends AppCompatActivity implements View.OnClickListener {

    //Widget
    Button add_lena_date_btn, saveBtn;
    private int mYear, mMonth, mDay, mHour, mMinute;
    EditText edtName, edtDesc, edtAmt, edtPhone;

    //String
    String sname, sdesc, stime, samt, sphone, stype, screateDate;

    Date date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lena);

        //initWidget
        initWidget();
    }

    private void initWidget() {
        edtName = findViewById(R.id.add_lena_name);
        edtDesc = findViewById(R.id.add_lena_description);
        add_lena_date_btn = findViewById(R.id.add_lena_date_btn);
        edtAmt = findViewById(R.id.add_lena_amount);
        edtPhone = findViewById(R.id.add_lena_phoneno);
        saveBtn = findViewById(R.id.add_lena_save_btn);
        add_lena_date_btn = findViewById(R.id.add_lena_date_btn);

        //Current Date
        date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        screateDate = df.format(date);

        add_lena_date_btn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
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
                    add_lena_date_btn.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                }
            },mYear,mMonth,mDay);
            datePickerDialog.show();

        } else if (id == R.id.add_lena_save_btn) {
            sname = edtName.getText().toString().trim();
            sdesc = edtDesc.getText().toString().trim();
            samt = edtAmt.getText().toString().trim();
            sphone = edtPhone.getText().toString().trim();
            stime = add_lena_date_btn.getText().toString();


            if (TextUtils.isEmpty(sname)) {
                Toast.makeText(this, "Enter Lena's name ", Toast.LENGTH_SHORT).show();
                edtName.requestFocus();
            } else if (TextUtils.isEmpty(sdesc)) {
                Toast.makeText(this, "Enter Description", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(samt)) {
                Toast.makeText(this, "Enter amount", Toast.LENGTH_SHORT).show();
            } else {
                new saveDena().execute();
            }

        }
    }

    class saveDena extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {


            Lena lena = new Lena();
            lena.setName(sname);
            lena.setDescription(sdesc);
            lena.setAmount(samt);
            lena.setType("Lena");
            lena.setTime(stime);
            lena.setCreateDate(screateDate);
            lena.setPhone(sphone);

            //Add To Database
            LenaDatabaseClient.getInstance(getApplicationContext()).getLenaDataBase().lenaDao().insert(lena);


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            add_lena_date_btn.setEnabled(true);
            finish();
            super.onPostExecute(aVoid);

        }
    }
}
