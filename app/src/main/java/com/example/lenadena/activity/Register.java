package com.example.lenadena.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenadena.R;

public class Register extends AppCompatActivity implements View.OnClickListener {

    //Widget
    EditText edt_phoneno;
    TextView time_txt;
    Button submit_btn;

    //String
    String phoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initialize widget
        initializer();

    }

    private void initializer() {

        //initialize widget
        edt_phoneno = findViewById(R.id.register_phoneno);
        time_txt = findViewById(R.id.register_time);
        submit_btn = findViewById(R.id.register_submit);

        submit_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.register_submit) {
            phoneno = edt_phoneno.getText().toString();
            if (TextUtils.isEmpty(phoneno)) {
                Toast.makeText(this, "Enter phone no", Toast.LENGTH_SHORT).show();
            } else {

            }
        }
    }
}
