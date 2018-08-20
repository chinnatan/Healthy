package com.healthy.a59070040.healthy;

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
import android.widget.TextView;
import android.widget.Toast;

public class BMIFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        return inflater.inflate(R.layout.fragment_bmi, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initCalculateBtn();
    }

    void initCalculateBtn() {
        Button _calculateBtn = getView().findViewById(R.id.bmi_calculate_btn);

        _calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView _bmiShow = getView().findViewById(R.id.bmi_show);
                TextView _bmiResult = getView().findViewById(R.id.bmi_result);
                EditText _height = getView().findViewById(R.id.bmi_height);
                EditText _weight = getView().findViewById(R.id.bmi_weight);
                String _heightStr = _height.getText().toString();
                String _weightStr = _weight.getText().toString();

                if(_heightStr.isEmpty() || _weightStr.isEmpty()) {
                    Toast.makeText(getActivity(), "กรุณาระบุข้อมูลให้ครบถ้วน", Toast.LENGTH_LONG).show();
                    Log.d("BMI_USER", "FIELD NAME IS EMPTY");
                } else {
                    double _resultBMI;
                    double _convertHeight, _convertWeight;
                    _convertHeight = Float.parseFloat(_heightStr) / 100;
                    _convertWeight = Float.parseFloat(_weightStr);
                    _resultBMI = _convertWeight / (_convertHeight*_convertHeight);
                    _bmiShow.setText("Your BMI");
                    _bmiResult.setText(String.format("%.2f", _resultBMI));
                    Log.d("BMI_USER", "BMI IS VALUE");
                }
            }
        });
    }
}
