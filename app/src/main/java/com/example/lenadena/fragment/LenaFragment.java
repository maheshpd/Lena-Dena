package com.example.lenadena.fragment;


import android.content.Context;
import android.os.AsyncTask;
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
import com.example.lenadena.client.DatabaseClient;

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
    LenaAdapter adapter;

    public LenaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lena, container, false);
        context = view.getContext();

        //initial
        initView(view);
        new getLenaTask().execute();
        return view;

    }

    private void initView(View view) {
        lenaRV = view.findViewById(R.id.lenaRv);
        takemoney = view.findViewById(R.id.no_take_money);
    }

    class getLenaTask extends AsyncTask<Void, Void, List<Lena>> {
        @Override
        protected List<Lena> doInBackground(Void... voids) {
            List<Lena> lenaList = DatabaseClient.getInstance(context)
                    .getLenaRoomDatabase()
                    .lenaDao()
                    .getAllData();
            return lenaList;
        }

        @Override
        protected void onPostExecute(List<Lena> lenas) {
            super.onPostExecute(lenas);

            adapter = new LenaAdapter(getContext(), lenas);
            lenaRV.setLayoutManager(new LinearLayoutManager(getContext()));
            lenaRV.setHasFixedSize(true);
            lenaRV.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

}
