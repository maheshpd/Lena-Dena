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
import com.example.lenadena.adapter.DenaAdapter;
import com.example.lenadena.adapter.OnDenaItemClick;
import com.example.lenadena.helper.MyDenaHelper;

import io.realm.Realm;
import io.realm.RealmChangeListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class DenaFragement extends Fragment {

    //Widget
    RecyclerView denaRv;
    LinearLayout givemoney;
    Context context;

    Realm realm;

    //    DenaDataBase denaDataBase;
    DenaAdapter adapter;

    MyDenaHelper helper;
    RealmChangeListener realmChangeListener;

    OnDenaItemClick onDenaItemClick;
    public DenaFragement() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dena_fragement, container, false);
        context = view.getContext();

        realm = Realm.getDefaultInstance();
        helper = new MyDenaHelper(realm);
        helper.selectFromDB();

        initializeWidget(view);
        return view;
    }

    private void initializeWidget(View view) {
        denaRv = view.findViewById(R.id.denaRv);
        givemoney = view.findViewById(R.id.no_give_money);

        if (helper.justRefresh() != null && helper.justRefresh().size() > 0) {
            givemoney.setVisibility(View.GONE);

            adapter = new DenaAdapter(getContext(), helper.justRefresh());
            denaRv.setLayoutManager(new LinearLayoutManager(getContext()));
            denaRv.setHasFixedSize(true);
            denaRv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

        refresh();
    }

    private void refresh() {
        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange(Object o) {
                adapter = new DenaAdapter(getContext(), helper.justRefresh());
                denaRv.setAdapter(adapter);
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
