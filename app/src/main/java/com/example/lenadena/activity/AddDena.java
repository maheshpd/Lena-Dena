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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lenadena.Common;
import com.example.lenadena.R;
import com.example.lenadena.client.DatabaseClient;
import com.example.lenadena.model.Dena;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;

public class AddDena extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_CONTACT = 1121;

    //Widget
    Button add_dena_date_btn, saveBtn;
    private int mYear, mMonth, mDay, mHour, mMinute;
    EditText edtName, edtDesc, edtAmt, edtPhone;
    CheckBox remindMe_cbk;

    //String
    String sname, sdesc, stime, samt, sphone, stype, screateDate;

    List<Dena> denalist;

    Date date;

    //Realm
    Realm realm;

    Dena dena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dena);

        realm = Realm.getDefaultInstance();

        //initWidget
        initWidget();


        if (Common.posFromDena == true) {
            final int pos = Common.position;
//            dena = realm.where(Dena.class).equalTo("id", pos).findFirst();


            edtName.setText(dena.getName());
            edtDesc.setText(dena.getDescription());
            add_dena_date_btn.setText(dena.getTime());
            edtAmt.setText(dena.getAmount());
            edtPhone.setText(dena.getPhone());
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
        dena.setName(edtName.getText().toString().trim());
        dena.setDescription(edtDesc.getText().toString().trim());
        dena.setAmount(edtAmt.getText().toString().trim());
        dena.setPhone(edtPhone.getText().toString().trim());
        dena.setTime(add_dena_date_btn.getText().toString().trim());
        dena.setCreateDate(screateDate);
        Common.posFromDena = false;

        startActivity(new Intent(AddDena.this, MainActivity.class));
        finish();
        realm.commitTransaction();


    }


    private void initWidget() {
        edtName = findViewById(R.id.add_dena_name);
        edtDesc = findViewById(R.id.add_dena_description);
        add_dena_date_btn = findViewById(R.id.add_dena_date_btn);
        edtAmt = findViewById(R.id.add_dena_amount);
        edtPhone = findViewById(R.id.add_dena_phoneno);
        saveBtn = findViewById(R.id.add_dena_save_btn);
        remindMe_cbk = findViewById(R.id.reminme_cbk);

        //Current Date
        date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        screateDate = df.format(date);


        add_dena_date_btn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);

        ImageButton contactBtn = findViewById(R.id.contact_btn);
        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(AddDena.this)
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

        if (id == R.id.add_dena_date_btn) {
            //Get current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    add_dena_date_btn.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();

        } else if (id == R.id.add_dena_save_btn) {
            sname = edtName.getText().toString().trim();
            sdesc = edtDesc.getText().toString().trim();
            samt = edtAmt.getText().toString().trim();
            sphone = edtPhone.getText().toString().trim();
            stime = add_dena_date_btn.getText().toString();


            if (TextUtils.isEmpty(sname)) {
                Toast.makeText(this, "Enter Dena's name ", Toast.LENGTH_SHORT).show();
                edtName.requestFocus();
            } else if (TextUtils.isEmpty(sdesc)) {
                Toast.makeText(this, "Enter Description", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(samt)) {
                Toast.makeText(this, "Enter amount", Toast.LENGTH_SHORT).show();
            } else {
//                new saveDena().execute();
//                saveDena();
                new DenaTask().execute();
            }

        }
    }

    private void saveDena() {
       /* realm.executeTransactionAsync(new Realm.Transaction() {

            @Override
            public void execute(Realm realm) {

                Number maxId = realm.where(Dena.class).max("id");
                int newKey = (maxId == null) ? 1 : maxId.intValue() + 1;

                Dena dena = realm.createObject(Dena.class, newKey);
                dena.setName(sname);
                dena.setDescription(sdesc);
                dena.setAmount(samt);
                dena.setType("Dena");
                dena.setTime(stime);
                dena.setCreateDate(screateDate);
                dena.setPhone(sphone);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                startActivity(new Intent(AddDena.this, MainActivity.class));
                finish();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(AddDena.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    class DenaTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Dena dena = new Dena();
            dena.setName(sname);
            dena.setDescription(sdesc);
            dena.setAmount(samt);
            dena.setType("Dena");
            dena.setTime(stime);
            dena.setCreateDate(screateDate);
            dena.setPhone(sphone);

            DatabaseClient.getInstance(getApplicationContext()).getLenaRoomDatabase()
                    .denaDao().insert(dena);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(AddDena.this, "Data Added", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
    }


}
