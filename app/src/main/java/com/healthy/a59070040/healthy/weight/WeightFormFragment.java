package com.healthy.a59070040.healthy.weight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.healthy.a59070040.healthy.R;

public class WeightFormFragment extends Fragment {

    FirebaseFirestore _mStore;
    FirebaseAuth _mAuth;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _mStore = FirebaseFirestore.getInstance();
        _mAuth = FirebaseAuth.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight_form, container, false);
    }

    void initSaveButton() {
        Button _btn = getView().findViewById(R.id.weight_form_save_btn);
        _btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText _date = getView().findViewById(R.id.weight_form_weighing_date);
                EditText _weight = getView().findViewById(R.id.weight_form_weight);

                String _dateStr = _date.getText().toString();
                String _weightStr = _weight.getText().toString();
                String _uidStr = _mAuth.getCurrentUser().getUid();

                Weight _dataWeight = new Weight(_dateStr, Integer.valueOf(_weightStr), "UP");

                _mStore.collection("myfitness").document(_uidStr)
                        .collection("weight").document(_dateStr)
                        .set(_dataWeight).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "ERROR - " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

}
