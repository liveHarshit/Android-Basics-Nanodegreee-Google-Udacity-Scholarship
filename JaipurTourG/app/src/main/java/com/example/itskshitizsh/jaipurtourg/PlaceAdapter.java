package com.example.itskshitizsh.jaipurtourg;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/*
    RecyclerView.Adapter    // Binds the data to the view.
    RecyclerView.ViewHolder // Holds the view.
 */
public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {

    private Context mContext;
    private List<Place> placesList;

    public PlaceAdapter(Context mContext, List<Place> placesList) {
        this.mContext = mContext;
        this.placesList = placesList;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_layout, viewGroup, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder placeViewHolder, int position) {
        Place place = placesList.get(position);
        placeViewHolder.textViewPlaceName.setText(place.getNamePlace());
        placeViewHolder.textViewPlaceDescription.setText(place.getShortDescription());
        placeViewHolder.textViewRating.setText(String.valueOf(place.getRating()));
        placeViewHolder.imageView.setImageDrawable(mContext.getDrawable(place.getMyImage()));
    }

    @Override
    public int getItemCount() {
        return placesList.size();
    }

    class PlaceViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewPlaceName;
        TextView textViewPlaceDescription;
        TextView textViewRating;

        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewPlaceName = itemView.findViewById(R.id.textViewName);
            textViewPlaceDescription = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);

        }
    }
}
