package com.example.lenadena.fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lenadena.R;
import com.example.lenadena.adapter.SectionRecyclerViewAdapter;
import com.example.lenadena.client.DatabaseClient;
import com.example.lenadena.model.Daily;
import com.example.lenadena.model.SectionModel;

import java.util.List;

import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyExpanseFragment extends Fragment {
    private static final String TAG = "DailyExpanseFragment";

    RecyclerView sectionRv;
    Context context;
    RealmQuery<Daily> query;
    RealmResults<Daily> result1;
    RealmResults<Daily> results;
    SectionRecyclerViewAdapter adapter;
    String date;

    public DailyExpanseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daily_expanse, container, false);
        context = view.getContext();
        init(view);
        new getDailyTask().execute();
        return view;
    }

    private void init(View view) {
        sectionRv = view.findViewById(R.id.sectionRv);
        sectionRv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        sectionRv.setHasFixedSize(true);
        sectionRv.setNestedScrollingEnabled(true);
    }

    class getDailyTask extends AsyncTask<Void, Void, List<SectionModel>> {

        @Override
        protected List<SectionModel> doInBackground(Void... voids) {
            List<SectionModel> dailyList = DatabaseClient.getInstance(context)
                    .getLenaRoomDatabase()
                    .daliyDao()
//                    .getCreateDate();
                    .getCreateDate();

            return dailyList;
        }

        @Override
        protected void onPostExecute(List<SectionModel> sectionModels) {
            super.onPostExecute(sectionModels);
            String createDate;
            for (int i = 0; i < sectionModels.size(); i++) {
                createDate = sectionModels.get(i).getDate();
            }

            adapter = new SectionRecyclerViewAdapter(getContext(), sectionModels);
            sectionRv.setLayoutManager(new LinearLayoutManager(getContext()));
            sectionRv.setHasFixedSize(true);
            sectionRv.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }
    }

}
