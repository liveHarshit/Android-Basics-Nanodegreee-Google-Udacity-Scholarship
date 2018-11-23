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

public class Tab2Fragment extends Fragment {


    RecyclerView recyclerView;
    PlaceAdapter adapter;
    List<Place> placeList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2_fragment, container, false);

        recyclerView = rootView.findViewById(R.id.recycleViewTab2);
        recyclerView.setHasFixedSize(true);     // For the recycler view size, not the elements inside the recycler.

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        placeList = new ArrayList<>();
        int i = 0;

        // Data Set Restaurant
        placeList.add(new Place(++i, getString(R.string.chokhi_dhani_food), getString(R.string.chokhi_dhani_food_details), 4.7, R.drawable.chokhi_dhani_1));
        placeList.add(new Place(++i, getString(R.string.virasat_restaurant), getString(R.string.virasat_restaurant_details), 3.9, R.drawable.virasat_restaurant));
        placeList.add(new Place(++i, getString(R.string.pride_amber_vilas), getString(R.string.pride_amber_vilas_details), 3.5, R.drawable.pride_amber_vilas));
        placeList.add(new Place(++i, getString(R.string.kabab_and_curry), getString(R.string.kabab_and_curry_details), 4.1, R.drawable.kcco));
        placeList.add(new Place(++i, getString(R.string.sethi_tikka_kabab_curry), getString(R.string.sethi_tikka_kabab_curry_details), 4.2, R.drawable.sethi_tikka_kabab));
        placeList.add(new Place(++i, getString(R.string.sahu_chai_ki_thadi), getString(R.string.sahu_chai_ki_thadi_details), 4.2, R.drawable.sahu_chai_ki_thadi));
        placeList.add(new Place(++i, getString(R.string.rawat_misthan_bhandar), getString(R.string.rawat_misthan_bhandar_details), 4.1, R.drawable.rawat_misthan_bhandar));
        placeList.add(new Place(++i, getString(R.string.bapu_nagar), getString(R.string.bapu_nagar_details), 4.2, R.drawable.sanjay_omelette));
        placeList.add(new Place(++i, getString(R.string.lassiwala_mi_road), getString(R.string.lassiwala_mi_road_details), 4.4, R.drawable.lassiwala));
        placeList.add(new Place(++i, getString(R.string.saras_parlor), getString(R.string.saras_parlor_details), 4.5, R.drawable.saras_parlor));
        placeList.add(new Place(++i, getString(R.string.bapu_bazar), getString(R.string.bapu_bazar_details), 4.6, R.drawable.kulfi_falooda));
        placeList.add(new Place(++i, getString(R.string.near_birla_mandir), "", 3.8, R.drawable.pav_bhaji));
        placeList.add(new Place(++i, getString(R.string.falhaar_saraogi_mansion), getString(R.string.falhaar_saraogi_mansion_details), 3.7, R.drawable.shrikhand_at_falahaar));
        placeList.add(new Place(++i, getString(R.string.laxmi_misthan_bhandar), getString(R.string.laxmi_misthan_bhandar_details), 4.3, R.drawable.rabri_ghevar));
        placeList.add(new Place(++i, getString(R.string.murli_paan_bhandar), getString(R.string.murli_paan_bhandar_details), 4.3, R.drawable.paan_finishing_off));

        adapter = new PlaceAdapter(getContext(), placeList);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

}
