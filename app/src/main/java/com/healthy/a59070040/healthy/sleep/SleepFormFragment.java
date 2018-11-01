package com.healthy.a59070040.healthy.sleep;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.healthy.a59070040.healthy.R;

public class SleepFormFragment extends Fragment {

    private SQLiteDatabase db;
    private Sleep sleep;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep_form, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        db = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);
        sleep = Sleep.getSleepInstance();

        initSaveBtn();
        initBackBtn();
        if(sleep != null) {
            loadData();
        }
    }

    private void initSaveBtn() {
        Button _saveBtn = getView().findViewById(R.id.frg_sleep_form_saveBtn);
        _saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
            }
        });
    }

    private void initBackBtn() {
        Button _backBtn = getView().findViewById(R.id.sleep_form_backBtn);
        _backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new SleepFragment()).commit();
            }
        });
    }

    private void setData() {
        EditText _dateTxt = getView().findViewById(R.id.frg_sleep_form_date);
        EditText _timetosleepHourTxt = getView().findViewById(R.id.frg_sleep_form_timetosleep_hour);
        EditText _timetosleepMinTxt = getView().findViewById(R.id.frg_sleep_form_timetosleep_min);
        EditText _timetowakeupHourTxt = getView().findViewById(R.id.frg_sleep_form_timetowakeup_hour);
        EditText _timetowakeupMinTxt = getView().findViewById(R.id.frg_sleep_form_timetowakeup_min);

        String _dateStr = _dateTxt.getText().toString();
        String _timetosleepHourStr = _timetosleepHourTxt.getText().toString();
        String _timetosleepMinStr = _timetosleepMinTxt.getText().toString();
        String _timetowakeupHourStr = _timetowakeupHourTxt.getText().toString();
        String _timetowakeupMinStr = _timetowakeupMinTxt.getText().toString();

        if(_dateStr.isEmpty() || _timetosleepHourStr.isEmpty() || _timetosleepMinStr.isEmpty() || _timetowakeupHourStr.isEmpty() || _timetowakeupMinStr.isEmpty()) {
            Toast.makeText(getActivity(), "กรุณาใส่ข้อมูลให้ครบถ้วน", Toast.LENGTH_LONG).show();
            Log.d("FIELDEMPTY_SLEEPFORMFRAGMENT", "field is empty");
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("currentdate", _dateStr);
            contentValues.put("timetosleephour", _timetosleepHourStr);
            contentValues.put("timetosleepmin", _timetosleepMinStr);
            contentValues.put("timetowakeuphour", _timetowakeupHourStr);
            contentValues.put("timetowakeupmin", _timetowakeupMinStr);

            int timetosleepHourInt = Integer.parseInt(_timetosleepHourStr);
            int timetosleepMinInt = Integer.parseInt(_timetosleepMinStr);
            int timetowakeupHourInt = Integer.parseInt(_timetowakeupHourStr);
            int timetowakeupMinInt = Integer.parseInt(_timetowakeupMinStr);

            int calculateHour = Math.abs(24 - timetosleepHourInt);
            int calculateMin = Math.abs(00 - timetosleepMinInt);

            int resultHour = calculateHour + timetowakeupHourInt;
            int resultMin = calculateMin + timetowakeupMinInt;

            String resultMinStr;
            if(resultMin == 0) {
                resultMinStr = "00";
                contentValues.put("counttime", resultHour + ":" + resultMinStr);
            } else {
                contentValues.put("counttime", resultHour + ":" + resultMin);
            }

            if(sleep.getPrimaryId() == 0) {
                db.insert("sleep", null, contentValues);
            } else {
                db.update("sleep", contentValues, "_id=" + sleep.getPrimaryId(), null);
            }

            Toast.makeText(getActivity(), "เพิ่มข้อมูลเรียบร้อย", Toast.LENGTH_LONG).show();
            Log.d("ADDTODATABASE_SLEEPFORMFRAGMENT", "add to database");
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new SleepFragment()).commit();
        }
    }

    private void loadData() {
        EditText _dateTxt = getView().findViewById(R.id.frg_sleep_form_date);
        EditText _timetosleepHourTxt = getView().findViewById(R.id.frg_sleep_form_timetosleep_hour);
        EditText _timetosleepMinTxt = getView().findViewById(R.id.frg_sleep_form_timetosleep_min);
        EditText _timetowakeupHourTxt = getView().findViewById(R.id.frg_sleep_form_timetowakeup_hour);
        EditText _timetowakeupMinTxt = getView().findViewById(R.id.frg_sleep_form_timetowakeup_min);

        _dateTxt.setText(sleep.getCurrentDate());
        _timetosleepHourTxt.setText(sleep.getTimetosleepHour());
        _timetosleepMinTxt.setText(sleep.getTimetosleepMin());
        _timetowakeupHourTxt.setText(sleep.getTimetowakeupHour());
        _timetowakeupMinTxt.setText(sleep.getTimetowakeupMin());
    }
}
