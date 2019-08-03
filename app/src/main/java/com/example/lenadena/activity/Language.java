package com.example.lenadena.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lenadena.R;

public class Language extends AppCompatActivity implements View.OnClickListener {

    //Widget
    Button english_btn, hindi_btn, marathi_btn, gujrati_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        //initializer
        initializer();


    }

    private void initializer() {
        //widget
        english_btn = findViewById(R.id.language_englis_btn);
        hindi_btn = findViewById(R.id.language_hindi_btn);
        marathi_btn = findViewById(R.id.language_marathi_btn);
        gujrati_btn = findViewById(R.id.language_gujrati_btn);

        english_btn.setOnClickListener(this);
        hindi_btn.setOnClickListener(this);
        marathi_btn.setOnClickListener(this);
        gujrati_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.language_hindi_btn) {
            Toast.makeText(this, "Hindi Btn Click", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.language_englis_btn) {
            Toast.makeText(this, "English Btn Click", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.language_gujrati_btn) {
            Toast.makeText(this, "Gujrati Btn Click", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.language_marathi_btn) {
            Toast.makeText(this, "Marathi Btn Click", Toast.LENGTH_SHORT).show();
        }
    }
}
