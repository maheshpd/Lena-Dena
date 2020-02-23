package com.example.lenadena.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lenadena.R;
import com.example.lenadena.model.Lena;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LenaFragment extends Fragment {

    //widget
    RecyclerView lenaRV;
    LinearLayout takemoney;
    Context context;
    List<Lena> lenalist;
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

//        new getDataValue().execute();
        return view;

    }

    private void initView(View view) {
        lenaRV = view.findViewById(R.id.lenaRv);
        takemoney = view.findViewById(R.id.no_give_money);
    }

   /* @Override
    public void onResume() {
        super.onResume();
        new getDataValue().execute();
    }
*/
   /* @Override
    public void onDenaItemClick(final int pos) {
        new AlertDialog.Builder(context)
                .setTitle("Select Option")
                .setItems(new String[]{"Delete", "Update"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                AsyncTask.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        LenaDatabaseClient.getInstance(getContext())
                                                .getLenaDataBase()
                                                .lenaDao()
                                                .delete(lenalist.get(pos));
                                        lenalist.remove(pos);
                                    }
                                });

                            case 1:
                                Intent intent = new Intent(context, AddLena.class);
                                intent.putExtra("id", pos);
                                Common.posFromDena = true;
                                startActivity(intent);
                        }
                    }
                }).show();
    }*/

    /*class getDataValue extends AsyncTask<Void, Void, List<Lena>> {

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

            if (lenas != null && lenas.size() > 0) {
                takemoney.setVisibility(View.GONE);
                LenaAdapter adapter = new LenaAdapter(getContext(), lenas);
                lenaRV.setLayoutManager(new LinearLayoutManager(getContext()));
                lenaRV.setAdapter(adapter);
                lenaRV.setHasFixedSize(true);
                adapter.notifyDataSetChanged();
            }
        }
    }*/
}
