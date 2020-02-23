package com.example.lenadena.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lenadena.Common;
import com.example.lenadena.R;
import com.example.lenadena.activity.AddDena;
import com.example.lenadena.adapter.DenaAdapter;
import com.example.lenadena.adapter.OnDenaItemClick;
import com.example.lenadena.helper.MyDenaHelper;

import io.realm.Realm;
import io.realm.RealmChangeListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class DenaFragement extends Fragment implements OnDenaItemClick {

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        new getDataValue().execute();
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

        init();

        initializeWidget(view);
//        displayList();

        return view;
    }

    private void init() {
        onDenaItemClick = this;
    }

   /* private void displayList() {
        denaDataBase = DenaDataBase.getInstance(context);
        new getDataValue().execute();
    }*/

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

//        denalist = new ArrayList<>();

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

    /* @Override
    public void onResume() {
        super.onResume();
        new getDataValue().execute();
    }*/

    @Override
    public void onDenaItemClick(final int pos) {
        new AlertDialog.Builder(context)
                .setTitle("Select Options")
                .setItems(new String[]{"Delete", "Update"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:

//                                AsyncTask.execute(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        DenaDatabaseClient.getInstance(getContext())
//                                                .getDenaDataBase()
//                                                .denaDao()
//                                                .delete(Common.denalist.get(pos));
//                                        Common.denalist.remove(pos);
//                                    }
//                                });

                            case 1:
                                Intent intent = new Intent(context, AddDena.class);
                                Common.position = pos;
                                Common.posFromDena = true;
                                startActivity(intent);




                        }
                    }
                }).show();
    }

   /* class getDataValue extends AsyncTask<Void, Void, List<Dena>> {


        @Override
        protected List<Dena> doInBackground(Void... voids) {
            Common.denalist = DenaDatabaseClient.getInstance(getContext())
                    .getDenaDataBase()
                    .denaDao()
                    .getAllDena();
            return Common.denalist;
        }

        @Override
        protected void onPostExecute(List<Dena> denas) {
            super.onPostExecute(denas);

            if (denas != null && denas.size() > 0) {
                givemoney.setVisibility(View.GONE);

                adapter = new DenaAdapter(getContext(), Common.denalist, onDenaItemClick);
                denaRv.setHasFixedSize(true);
                denaRv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
    }*/


}
