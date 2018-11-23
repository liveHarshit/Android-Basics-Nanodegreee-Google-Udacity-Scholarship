package com.example.itskshitizsh.jaipurtourg;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Tab3Fragment extends Fragment {


    RecyclerView recyclerView;
    PlaceAdapter adapter;
    List<Place> placeList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3_fragment, container, false);

        recyclerView = rootView.findViewById(R.id.recycleViewTab3);
        recyclerView.setHasFixedSize(true);     // For the recycler view size, not the elements inside the recycler.

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        placeList = new ArrayList<>();
        int i = 0;

        // Data Set Hotels
        placeList.add(new Place(++i, getString(R.string.rambhag_palace), getString(R.string.rambhag_palace_details), 4.7, R.drawable.rambhag_palace));
        placeList.add(new Place(++i, getString(R.string.samode_haveli), getString(R.string.samode_haveli_details), 4.5, R.drawable.samode_haveli));
        placeList.add(new Place(++i, getString(R.string.umaid_mahal), getString(R.string.umaid_mahal_details), 4.3, R.drawable.umaid_mahal));
        placeList.add(new Place(++i, getString(R.string.jal_mahal_palce), getString(R.string.jal_mahal_palce_details), 4.2, R.drawable.jal_mahal_palace));
        placeList.add(new Place(++i, getString(R.string.shahpura_house), getString(R.string.shahpura_house_details), 4.6, R.drawable.shahpura_house));
        placeList.add(new Place(++i, getString(R.string.mandawa_haveli), getString(R.string.mandawa_haveli_details), 4.2, R.drawable.mandawa_haveli));
        placeList.add(new Place(++i, getString(R.string.hotel_park_ocean), getString(R.string.hotel_park_ocean_details), 3.7, R.drawable.hotel_park_ocean));
        placeList.add(new Place(++i, getString(R.string.hotel_glitz), getString(R.string.hotel_glitz_details), 3.6, R.drawable.hotel_glitz));

        adapter = new PlaceAdapter(getContext(), placeList);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

}
