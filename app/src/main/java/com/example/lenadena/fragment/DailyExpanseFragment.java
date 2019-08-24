package com.example.lenadena.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.lenadena.R;

import java.util.Calendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyExpanseFragment extends Fragment {

    //Widget
    HorizontalCalendarView horizontalCalendarView;

    public DailyExpanseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daily_expanse, container, false);

        init(view);


        return view;
    }

    private void init(View view) {

        //CAlendar
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, 0);

        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DATE, 2); //2 day left

        horizontalCalendarView = view.findViewById(R.id.calanderView);
        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(view, R.id.calanderView)
                .range(startDate, endDate)
                .datesNumberOnScreen(4)
                .mode(HorizontalCalendar.Mode.DAYS)
                .defaultSelectedDate(startDate)
                .build();
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {

            }
        });
    }

}
