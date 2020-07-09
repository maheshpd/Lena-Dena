package com.example.lenadena.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lenadena.Common;
import com.example.lenadena.R;
import com.example.lenadena.client.DatabaseClient;
import com.example.lenadena.model.Lena;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;

public class AddLena extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_CONTACT = 1121;

    //Widget
    Button add_lena_date_btn, saveBtn;
    private int mYear, mMonth, mDay, mHour, mMinute;
    EditText edtName, edtDesc, edtAmt, edtPhone;

    //String
    String sname, sdesc, stime, samt, sphone, stype, screateDate;

    Date date;

    //Realm
    Realm realm;

    Lena lena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lena);

        realm = Realm.getDefaultInstance();

        //initWidget
        initWidget();

        if (Common.posFromLena == true) {
            final int pos = Common.position;
//            lena = realm.where(Lena.class).equalTo("id", pos).findFirst();

            edtName.setText(lena.getName());
            edtDesc.setText(lena.getDescription());
            add_lena_date_btn.setText(lena.getTime());
            edtAmt.setText(lena.getAmount());
            edtPhone.setText(lena.getPhone());
            saveBtn.setText("Update");

            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    update();
                }
            });
        }
    }

    private void update() {
        realm.beginTransaction();
        lena.setName(edtName.getText().toString().trim());
        lena.setDescription(edtDesc.getText().toString().trim());
        lena.setAmount(edtAmt.getText().toString().trim());
        lena.setPhone(edtPhone.getText().toString().trim());
        lena.setTime(add_lena_date_btn.getText().toString().trim());
        lena.setCreateDate(screateDate);

        Common.posFromDena = false;

        startActivity(new Intent(AddLena.this, MainActivity.class));
        finish();
        realm.commitTransaction();
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
        ImageButton contactBtn = findViewById(R.id.contact_btn);
        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(AddLena.this)
                        .withPermission(Manifest.permission.READ_CONTACTS).withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {

                        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                        startActivityForResult(intent, PICK_CONTACT);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();
            }
        });

        /*remindMe_cbk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        DenaDatabaseClient.getInstance(AddDena.this)
                                .getDenaDataBase()
                                .denaDao()
                                .delete(Common.denalist.get(pos));
                        Common.denalist.remove(pos);
                    }
                });

            }


        });*/
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
                    add_lena_date_btn.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
            }, mYear, mMonth, mDay);
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
                new LenaTask().execute();
            }

        }
    }

    class LenaTask extends AsyncTask<Void, Void, Void> {

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

            DatabaseClient.getInstance(getApplicationContext()).getLenaRoomDatabase()
                    .lenaDao().insert(lena);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(AddLena.this, "Data Added", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
    }

}
