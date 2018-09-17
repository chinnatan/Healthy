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

import com.healthy.a59070040.healthy.menu.Menu;
import com.healthy.a59070040.healthy.weight.WeightFragment;

import java.util.ArrayList;

public class MenuFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
        _menus.addItem("Logout");

        ListView _menuList = getView().findViewById(R.id.menu_list);
        final ArrayAdapter<String> _menuAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, _menus.getMenu());

        _menuList.setAdapter(_menuAdapter);

        _menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("MENU", "Select on " + _menus.getMenu().get(i));
                if(_menus.getMenu().get(i).equals("BMI")) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new BMIFragment()).addToBackStack(null).commit();
                    Log.d("MENU", "Selected on BMI Menu");
                } else if(_menus.getMenu().get(i).equals("Weight")){
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFragment()).addToBackStack(null).commit();
                    Log.d("MENU", "Selected on Weight Menu");
                }
                _menuAdapter.notifyDataSetChanged();
            }
        });
    }
}
