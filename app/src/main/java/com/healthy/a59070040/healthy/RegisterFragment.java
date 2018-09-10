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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterFragment extends Fragment {

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    public void onActivityCreated(@NonNull Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);

        initRegisterBtn();
    }

    void checkField() {
        EditText _email = getView().findViewById(R.id.register_email);
        EditText _password = getView().findViewById(R.id.register_password);
        EditText _rePassword = getView().findViewById(R.id.register_re_password);

        String _emailStr = _email.getText().toString();
        String _passwordStr = _password.getText().toString();
        String _rePasswordStr = _rePassword.getText().toString();

        if (_emailStr.isEmpty() && _passwordStr.isEmpty() && _rePasswordStr.isEmpty()) {
            Toast.makeText(getActivity(), "กรุณากรอกข้อมูล", Toast.LENGTH_LONG).show();
            Log.d("REGISTER_USER", "PLEASE FILL IN THE INFORMATION");
        } else if (_emailStr.isEmpty() || _passwordStr.isEmpty() || _rePasswordStr.isEmpty()) {
            Toast.makeText(getActivity(), "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_LONG).show();
            Log.d("REIGSTER_USER", "FIELD IS EMPTY.");
        } else if(!(_passwordStr.equals(_rePasswordStr)) || !(_rePasswordStr.equals(_passwordStr))) {
            Toast.makeText(getActivity(), "กรุณากรอก Re-Password ให้ตรงกัน", Toast.LENGTH_LONG).show();
            Log.d("REGISTER_USER", "RE-PASSWORD INCORRECT");
        } else if (!(_passwordStr.length() >= 6)) {
            Toast.makeText(getActivity(), "Password ต้องมีอย่างน้อย 6 ตัวอักษร", Toast.LENGTH_LONG).show();
            Log.d("REGISTER_USER", "PASSWORD NEED LENGTH AT LEAST 6 LONG");
        }
    }

    void initRegisterBtn() {
        Button _registerBtn = getView().findViewById(R.id.register_register_btn);
        _registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkField();
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword("email", "password").addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

//                EditText _userId = getView().findViewById(R.id.register_user_id);
//                EditText _name = getView().findViewById(R.id.register_name);
//                EditText _age = getView().findViewById(R.id.register_age);
//                EditText _password = getView().findViewById(R.id.register_password);
//                String _userIdStr = _userId.getText().toString();
//                String _nameStr = _name.getText().toString();
//                String _ageStr = _age.getText().toString();
//                String _passwordStr = _password.getText().toString();
//
//                if(_userIdStr.isEmpty() || _nameStr.isEmpty() || _ageStr.isEmpty() || _passwordStr.isEmpty()) {
//                    Toast.makeText(getActivity(), "กรุณาระบุข้อมูลให้ครบถ้วน", Toast.LENGTH_LONG).show();
//                    Log.d("REGISTER_USER", "FIELD NAME IS EMPTY");
//                } else if(_userIdStr.equals("admin")) {
//                    Toast.makeText(getActivity(), "user นี้มีอยู่ในระบบแล้ว", Toast.LENGTH_LONG).show();
//                    Log.d("REGISTER_USER", "USER ALREADY EXIST");
//                } else {
//                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new BMIFragment()).commit();
//                    Log.d("REGISTER_USER", "GOTO BMI");
//                }
            }
        });
    }
}
