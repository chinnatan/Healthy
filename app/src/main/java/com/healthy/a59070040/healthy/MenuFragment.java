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

import java.util.ArrayList;

public class MenuFragment extends Fragment {

    ArrayList<String> _menu = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        _menu.clear();
        _menu.add("BMI");
        _menu.add("Weight");
        _menu.add("Setup");

        ListView _menuList = getView().findViewById(R.id.menu_list);
        final ArrayAdapter<String> _menuAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, _menu);

        _menuList.setAdapter(_menuAdapter);

        _menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("MENU", "Select on " + _menu.get(i));
                if(_menu.get(i).equals("BMI")) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new BMIFragment()).addToBackStack(null).commit();
                    Log.d("MENU", "Selected on BMI Menu");
                } else if(_menu.get(i).equals("Weight")){
                    _menu.add("new Value");
                    Log.d("MENU", "Selected on Weight Menu");
                }
                _menuAdapter.notifyDataSetChanged();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }
}
