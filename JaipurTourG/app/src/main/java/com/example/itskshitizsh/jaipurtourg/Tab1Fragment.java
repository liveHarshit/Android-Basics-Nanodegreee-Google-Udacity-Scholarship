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

public class Tab1Fragment extends Fragment {


    RecyclerView recyclerView;
    PlaceAdapter adapter;
    List<Place> placeList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1_fragment, container, false);

        recyclerView = rootView.findViewById(R.id.recycleViewTab1);
        recyclerView.setHasFixedSize(true);     // For the recycler view size, not the elements inside the recycler.

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        placeList = new ArrayList<>();

        int i = 0;

        // Data Set Venues
        placeList.add(new Place(++i, getString(R.string.hawa_mahal), getString(R.string.hawa_mahal_details), 4.4, R.drawable.hawa_mahal));
        placeList.add(new Place(++i, getString(R.string.amber_fort), getString(R.string.amber_fort_details), 4.6, R.drawable.amber_fort));
        placeList.add(new Place(++i, getString(R.string.city_palace), getString(R.string.city_palace_details), 4.4, R.drawable.city_palace));
        placeList.add(new Place(++i, getString(R.string.jantar_mantar), getString(R.string.jantar_mantar_details), 4.5, R.drawable.jantar_mantar));
        placeList.add(new Place(++i, getString(R.string.jal_mahal), getString(R.string.jal_mahal_details), 4.5, R.drawable.jal_mahal));
        placeList.add(new Place(++i, getString(R.string.birla_mandir), getString(R.string.birla_mandir_details), 4.5, R.drawable.birla_mandir));
        placeList.add(new Place(++i, getString(R.string.jaigarh_fort), getString(R.string.jaigarh_fort_details), 4.5, R.drawable.jaigarh_fort));
        placeList.add(new Place(++i, getString(R.string.chokhi_dhani), getString(R.string.chokhi_dhani_details), 4.1, R.drawable.chokhi_dhani));
        placeList.add(new Place(++i, getString(R.string.albert_hall_museum), getString(R.string.albert_hall_museum_details), 4.4, R.drawable.albert_hall_museum));


        adapter = new PlaceAdapter(getContext(), placeList);
        recyclerView.setAdapter(adapter);

        return rootView;
    }


}
