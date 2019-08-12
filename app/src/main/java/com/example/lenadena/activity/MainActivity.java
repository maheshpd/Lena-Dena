package com.example.lenadena.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.lenadena.R;


import com.example.lenadena.adapter.TabsAccessorAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Widget
    FloatingActionButton addBtn;
    Dialog popupDialog;
    private TabLayout mTab;
    TabsAccessorAdapter mytabAccessprAdapter;
    private ViewPager myViewPager;

    Button addLenaBtn,addDenaBtn,addDailyExpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //int widget
        initWidget();
        initPopup();
    }

    public void initWidget(){
        addBtn = findViewById(R.id.addBtn);
        myViewPager = findViewById(R.id.homeviewpager);
        mytabAccessprAdapter = new TabsAccessorAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(mytabAccessprAdapter);

        mTab = findViewById(R.id.tabLayout);
        mTab.setupWithViewPager(myViewPager);

        addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id==R.id.addBtn){
            popupDialog.show();
        } else if (id == R.id.Add_Lena) {
            AddLenaView();
            popupDialog.dismiss();
        } else if (id == R.id.Add_Dena) {
            AddDenaView();
            popupDialog.dismiss();
        } else if (id == R.id.daily_expanse) {
            dailyExpanse();
            popupDialog.dismiss();
        }
    }

    private void dailyExpanse() {
        Intent intent = new Intent(MainActivity.this,DailyExpense.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void AddDenaView() {
        Intent intent = new Intent(MainActivity.this,AddDena.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void AddLenaView() {
        Intent intent = new Intent(MainActivity.this,AddLena.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void initPopup() {
        popupDialog = new Dialog(this);
        popupDialog.setContentView(R.layout.dialog_box);
        popupDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);

        addLenaBtn = popupDialog.findViewById(R.id.Add_Lena);
        addDenaBtn = popupDialog.findViewById(R.id.Add_Dena);
        addDailyExpBtn = popupDialog.findViewById(R.id.daily_expanse);

        addLenaBtn.setOnClickListener(this);
        addDenaBtn.setOnClickListener(this);
        addDailyExpBtn.setOnClickListener(this);

    }
}
