package com.example.lenadena.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lenadena.Abstract.DenaDataBase;
import com.example.lenadena.Client.DenaDatabaseClient;
import com.example.lenadena.R;
import com.example.lenadena.adapter.DenaAdapter;
import com.example.lenadena.adapter.OnDenaItemClick;
import com.example.lenadena.model.Dena;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DenaFragement extends Fragment implements OnDenaItemClick {

    //Widget
    RecyclerView denaRv;
    LinearLayout givemoney;
    Context context;
    List<Dena> denalist;

    DenaDataBase denaDataBase;
    DenaAdapter adapter;

    OnDenaItemClick onDenaItemClick;
    public DenaFragement() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new getDataValue().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dena_fragement, container, false);
        context = view.getContext();

        init();

        initializeWidget(view);
        displayList();

        return view;
    }

    private void init() {
        onDenaItemClick = this;
    }

    private void displayList() {
        denaDataBase = DenaDataBase.getInstance(context);
        new getDataValue().execute();
    }

    private void initializeWidget(View view) {
        denaRv = view.findViewById(R.id.denaRv);
        givemoney = view.findViewById(R.id.no_give_money);

        denaRv.setLayoutManager(new LinearLayoutManager(getContext()));
        denalist = new ArrayList<>();

    }

    @Override
    public void onResume() {
        super.onResume();
        new getDataValue().execute();
    }

    @Override
    public void onDenaItemClick(final int pos) {
        new AlertDialog.Builder(context)
                .setTitle("Select Options")
                .setItems(new String[]{"Delete", "Update"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                DenaDatabaseClient.getInstance(getContext())
                                        .getDenaDataBase()
                                        .denaDao()
                                        .delete(denalist.get(pos));
                                denalist.remove(pos);
                        }
                    }
                }).show();
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

            if (denas != null && denas.size() > 0) {
                givemoney.setVisibility(View.GONE);

                adapter = new DenaAdapter(getContext(), denalist, onDenaItemClick);
                denaRv.setHasFixedSize(true);
                denaRv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
    }

}
