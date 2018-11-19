package com.healthy.a59070040.healthy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.healthy.a59070040.healthy.menu.Menu;
import com.healthy.a59070040.healthy.post.PostFragment;
import com.healthy.a59070040.healthy.sleep.SleepFragment;
import com.healthy.a59070040.healthy.weight.WeightFragment;

public class MenuFragment extends Fragment {

    private static final String TAG = "MENU";

    FirebaseAuth _mAuth;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        _mAuth = FirebaseAuth.getInstance();

        initMenuList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    void initMenuList() {
        final Menu _menus = new Menu();
        _menus.addItem("BMI");
        _menus.addItem("Weight");
        _menus.addItem("Setup");
        _menus.addItem("Sleep");
        _menus.addItem("Post");
        _menus.addItem("Sign out");

        ListView _menuList = getView().findViewById(R.id.menu_list);
        final ArrayAdapter<String> _menuAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, _menus.getMenu());

        _menuList.setAdapter(_menuAdapter);

        _menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "Select on " + _menus.getMenu().get(i));
                if(_menus.getMenu().get(i).equals("BMI")) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new BMIFragment()).addToBackStack("to_bmi").commit();
                    Log.d(TAG, "Selected on BMI Menu");
                } else if(_menus.getMenu().get(i).equals("Weight")){
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFragment()).addToBackStack("to_weight").commit();
                    Log.d(TAG, "Selected on Weight Menu");
                } else if(_menus.getMenu().get(i).equals("Sleep")) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new SleepFragment()).commit();
                    Log.d(TAG, "Logout Completed");
                } else if(_menus.getMenu().get(i).equals("Post")) {
                    _mAuth.signOut();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new PostFragment()).addToBackStack(null).commit();
                    Log.d(TAG, "Logout Completed");
                }
                else if(_menus.getMenu().get(i).equals("Sign out")) {
                    _mAuth.signOut();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new LoginFragment()).commit();
                    Log.d(TAG, "Logout Completed");
                }
                _menuAdapter.notifyDataSetChanged();
            }
        });
    }
}
