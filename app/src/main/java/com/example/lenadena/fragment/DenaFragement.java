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
import com.example.lenadena.adapter.DenaAdapter;
import com.example.lenadena.client.DatabaseClient;
import com.example.lenadena.model.Dena;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DenaFragement extends Fragment {

    //Widget
    RecyclerView denaRv;
    LinearLayout givemoney;
    Context context;
    DenaAdapter adapter;

    public DenaFragement() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dena_fragement, container, false);
        context = view.getContext();
        initializeWidget(view);
        new getDenaTask().execute();
        return view;
    }

    private void initializeWidget(View view) {
        denaRv = view.findViewById(R.id.denaRv);
        givemoney = view.findViewById(R.id.no_give_money);
    }

    class getDenaTask extends AsyncTask<Void, Void, List<Dena>> {
        @Override
        protected List<Dena> doInBackground(Void... voids) {
            List<Dena> denaList = DatabaseClient.getInstance(context)
                    .getLenaRoomDatabase()
                    .denaDao()
                    .getAllData();
            return denaList;
        }

        @Override
        protected void onPostExecute(List<Dena> denas) {
            super.onPostExecute(denas);
            adapter = new DenaAdapter(getContext(), denas);
            denaRv.setLayoutManager(new LinearLayoutManager(getContext()));
            denaRv.setHasFixedSize(true);
            denaRv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}
