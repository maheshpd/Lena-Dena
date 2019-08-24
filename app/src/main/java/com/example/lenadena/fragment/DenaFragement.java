package com.example.lenadena.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lenadena.Client.DenaDatabaseClient;
import com.example.lenadena.R;
import com.example.lenadena.adapter.DenaAdapter;
import com.example.lenadena.model.Dena;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DenaFragement extends Fragment {

    //Widget
    RecyclerView denaRv;


    List<Dena> denalist;
    public DenaFragement() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dena_fragement, container, false);
        denaRv = view.findViewById(R.id.denaRv);

        new getDataValue().execute();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new getDataValue().execute();
    }

    class getDataValue extends AsyncTask<Void, Void, List<Dena>> {

        @Override
        protected List<Dena> doInBackground(Void... voids) {
            denalist = DenaDatabaseClient.getInstance(getContext())
                    .getDenaDataBase()
                    .denaDao()
                    .getAllDena();
            return denalist;
        }

        @Override
        protected void onPostExecute(List<Dena> denas) {
            super.onPostExecute(denas);
            DenaAdapter adapter = new DenaAdapter(getContext(), denas);
            denaRv.setLayoutManager(new LinearLayoutManager(getContext()));
            denaRv.setAdapter(adapter);
            denaRv.setHasFixedSize(true);
            adapter.notifyDataSetChanged();
        }
    }
}
