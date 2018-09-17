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
import com.google.firebase.auth.FirebaseUser;

public class RegisterFragment extends Fragment {

    private FirebaseAuth mAuth;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    public void onActivityCreated(@NonNull Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);

        mAuth = FirebaseAuth.getInstance();

        initRegisterBtn();
    }

    void registerNewUser() {
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
        } else {
            mAuth.createUserWithEmailAndPassword(_emailStr, _passwordStr).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    sendVerifiedEmail(authResult.getUser());
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new LoginFragment()).commit();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "ERROR - " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void sendVerifiedEmail(FirebaseUser _user) {
        _user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getActivity(), "กรุณาตรวจสอบ email ที่ทำการสมัครใช้งาน", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "ERROR - " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void initRegisterBtn() {
        Button _registerBtn = getView().findViewById(R.id.register_register_btn);
        _registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
            }
        });
    }
}
