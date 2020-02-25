package com.example.lenadena.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lenadena.R;
import com.example.lenadena.adapter.LenaAdapter;
import com.example.lenadena.helper.MyLenaHelper;
import com.example.lenadena.model.Lena;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class LenaFragment extends Fragment {

    //widget
    RecyclerView lenaRV;
    LinearLayout takemoney;
    Context context;
    List<Lena> lenalist;

    Realm realm;

    LenaAdapter adapter;

    MyLenaHelper helper;
    RealmChangeListener realmChangeListener;

    public LenaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lena, container, false);
        context = view.getContext();

        realm = Realm.getDefaultInstance();
        helper = new MyLenaHelper(realm);
        helper.selectFromDB();

        //initial
        initView(view);

//        new getDataValue().execute();
        return view;

    }

    private void initView(View view) {
        lenaRV = view.findViewById(R.id.lenaRv);
        takemoney = view.findViewById(R.id.no_take_money);

        if (helper.justRefresh() != null && helper.justRefresh().size() > 0) {
            takemoney.setVisibility(View.GONE);

            adapter = new LenaAdapter(getContext(), helper.justRefresh());
            lenaRV.setLayoutManager(new LinearLayoutManager(getContext()));
            lenaRV.setHasFixedSize(true);
            lenaRV.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        refresh();
    }

    private void refresh() {
        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange(Object o) {
                adapter = new LenaAdapter(getContext(), helper.justRefresh());
                lenaRV.setAdapter(adapter);
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.removeChangeListener(realmChangeListener);
        realm.close();
    }
}
