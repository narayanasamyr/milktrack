package com.ns.daily.track;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MilkCalendarAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;

    List<Date> dates;

    Calendar current;

    public MilkCalendarAdapter(@NonNull Context context, Calendar current, List<Date> dates) {
        inflater = (LayoutInflater.from(context));
        this.dates = dates;
        this.current = current;
    }


    @Override
    public int getCount() {
        return 42;
    }

    @Override
    public Object getItem(int position) {
        return dates.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.calendarcell, null);
        Date date = dates.get(position);
        Calendar monthCalendar = Calendar.getInstance();
        monthCalendar.setTime(date);
        int day = monthCalendar.get(Calendar.DAY_OF_MONTH);
        TextView dayView = view.findViewById(R.id.calendar_day);
        dayView.setText(String.valueOf(day));
        return view;
    }
}
