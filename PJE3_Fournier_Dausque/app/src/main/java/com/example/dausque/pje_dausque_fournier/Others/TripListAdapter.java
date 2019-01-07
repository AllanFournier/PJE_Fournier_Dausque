package com.example.dausque.pje_dausque_fournier.Others;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dausque.pje_dausque_fournier.Activity.Main;
import com.example.dausque.pje_dausque_fournier.Activity.FragDetailActivity;
import com.example.dausque.pje_dausque_fournier.Activity.FragmentDetail;
import com.example.dausque.pje_dausque_fournier.R;
import com.example.dausque.pje_dausque_fournier.Entity.Trip;

import java.util.List;

public class TripListAdapter extends RecyclerView.Adapter<TripListAdapter.TripViewHolder> {

    private List<Trip> mTrips;
    private final LayoutInflater mInflater;
    private Context mContext;
    private boolean mTwoPane;
    private final Main mParentActivity;

    public class TripViewHolder extends RecyclerView.ViewHolder {
        public final TextView tripTitreView;
        private final TextView tripDescView;


        public TripViewHolder(View itemView) {
            super(itemView);
            tripTitreView = itemView.findViewById(R.id.titre);
            tripDescView = itemView.findViewById(R.id.desc);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tripDescView.getText() + "'";
        }
    }

    public void setTrips(List<Trip> trips) {
        mTrips = trips;
        notifyDataSetChanged();
    }

    public TripListAdapter(Context context, Main parent, boolean twoPane) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mParentActivity = parent;
        mTwoPane = twoPane;
    }

    @Override
    public TripViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_trip_item, parent, false);
        return new TripViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final TripViewHolder holder, final int position) {

        if (mTrips != null) {
            Trip current = mTrips.get(position);
            holder.tripTitreView.setText(current.getMTrip());
            holder.tripDescView.setText(current.getDTrip());
        } else {
            // Covers the case of data not being ready yet.
            holder.tripTitreView.setText("No Trip");
            holder.tripDescView.setText("No Desc");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Details for trip " + holder.tripTitreView.getText());
                final Trip t = mTrips.get(position);
                System.out.println(t.getId());
                if (mTwoPane) {
                    System.out.println("two");
                    FragmentDetail frag = FragmentDetail.newInstance(t.getMTrip(), t.getDTrip(), t.getId());
                    mParentActivity.getSupportFragmentManager().beginTransaction().
                            replace(R.id.item_detail_container, frag).commit();
                } else {
                    Intent intent = new Intent(mContext, FragDetailActivity.class);
                    intent.putExtra("image_titre", t.getMTrip());
                    intent.putExtra("image_desc", t.getDTrip());
                    intent.putExtra("idTrip", t.getId());
                    mContext.startActivity(intent);
                }
            }
        });
    }

    public Trip getTripAtPosition(int position) {
        return mTrips.get(position);
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mTrips != null)
            return mTrips.size();
        else return 0;
    }
}
