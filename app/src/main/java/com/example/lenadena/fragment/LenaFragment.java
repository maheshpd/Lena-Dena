package com.example.lenadena.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lenadena.Client.LenaDatabaseClient;
import com.example.lenadena.R;
import com.example.lenadena.adapter.LenaAdapter;
import com.example.lenadena.model.Lena;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LenaFragment extends Fragment {

    //widget
    RecyclerView lenaRV;

    List<Lena> lenalist;
    public LenaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lena, container, false);

        //initial
        initView(view);

        new getDataValue().execute();
        return view;

    }

    private void initView(View view) {
        lenaRV = view.findViewById(R.id.lenaRv);
    }

    @Override
    public void onResume() {
        super.onResume();
        new getDataValue().execute();
    }

    class getDataValue extends AsyncTask<Void, Void, List<Lena>> {

        @Override
        protected List<Lena> doInBackground(Void... voids) {
            lenalist = LenaDatabaseClient.getInstance(getContext())
                    .getLenaDataBase()
                    .lenaDao()
                    .getAllLena();
            return lenalist;
        }

        @Override
        protected void onPostExecute(List<Lena> lenas) {
            super.onPostExecute(lenas);
            LenaAdapter adapter = new LenaAdapter(getContext(), lenas);
            lenaRV.setLayoutManager(new LinearLayoutManager(getContext()));
            lenaRV.setAdapter(adapter);
            lenaRV.setHasFixedSize(true);
            adapter.notifyDataSetChanged();
        }
    }
}
