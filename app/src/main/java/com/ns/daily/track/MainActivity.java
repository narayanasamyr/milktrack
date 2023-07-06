package com.ns.daily.track;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    TextView displayMonth;

    GridView gridView;

    List<Date> dates = new ArrayList<>();

    Calendar calendar = Calendar.getInstance();

    Button prev;

    Button next;

    int MAX_DAYS = 42; // 6 * 7

    SimpleDateFormat displayMonthFormat = new SimpleDateFormat("MMMM yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.calendargrid);

        prev = findViewById(R.id.prev);

        next = findViewById(R.id.next);

        displayMonth = findViewById(R.id.displayMonth);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Daily Track", "onClick: prev");
                calendar.add(Calendar.MONTH, -1);
                setUpCalendar();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Daily Track", "onClick: next");
                calendar.add(Calendar.MONTH, 1);
                setUpCalendar();
            }
        });


        setUpCalendar();
    }

    private void setUpCalendar(){
        dates.clear();

        Calendar currentCalendar = (Calendar) calendar.clone();

        currentCalendar.set(Calendar.DAY_OF_MONTH, 1);

        int previousMonthDays = currentCalendar.get(Calendar.DAY_OF_WEEK) - 1;

        currentCalendar.add(Calendar.DATE, -previousMonthDays);

        while (dates.size() <= MAX_DAYS) {
            dates.add(currentCalendar.getTime());
            currentCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        MilkCalendarAdapter milkCalendarAdapter = new MilkCalendarAdapter(getApplicationContext(),
                calendar, dates);

        gridView.setAdapter(milkCalendarAdapter);

        displayMonth.setText(displayMonthFormat.format(calendar.getTime()));
    }
}