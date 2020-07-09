package com.example.lenadena.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lenadena.R;
import com.example.lenadena.client.DatabaseClient;
import com.example.lenadena.model.Daily;
import com.example.lenadena.model.Dena;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;

public class DailyExpense extends AppCompatActivity {

    //Widget
    Button add_lena_date_btn, daily_expanse_btn;
    EditText daily_expanse_description, daily_expanse_amount;

    //String
    String sdescription, samount, screateDate;

    //Realm
    Realm realm;

    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_expense);

        //Current Date
        date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        screateDate = df.format(date);

        realm = Realm.getDefaultInstance();

        initWidget();
    }

    private void initWidget() {
        add_lena_date_btn = findViewById(R.id.add_lena_date_btn);
        daily_expanse_description = findViewById(R.id.daily_expanse_description);
        daily_expanse_amount = findViewById(R.id.daily_expanse_amount);
        daily_expanse_btn = findViewById(R.id.daily_expanse_btn);
        add_lena_date_btn.setText(screateDate);

        daily_expanse_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sdescription = daily_expanse_description.getText().toString().trim();
                samount = daily_expanse_amount.getText().toString();

                if (TextUtils.isEmpty(sdescription)) {
                    Toast.makeText(DailyExpense.this, "Enter description", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(samount)) {
                    Toast.makeText(DailyExpense.this, "Enter amount", Toast.LENGTH_SHORT).show();
                } else {
                    new DailyTask().execute();
                }
            }
        });
    }

    class DailyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Daily daily = new Daily();
            daily.setDescription(sdescription);
            daily.setCreateDate(screateDate);
            daily.setAmount(samount);

            DatabaseClient.getInstance(getApplicationContext()).getLenaRoomDatabase()
                    .daliyDao().insert(daily);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(DailyExpense.this, "Data Added", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
    }

}
