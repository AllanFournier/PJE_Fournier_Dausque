package com.example.dausque.pje_dausque_fournier.Activity;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.dausque.pje_dausque_fournier.R;
import com.example.dausque.pje_dausque_fournier.Entity.Trip;
import com.example.dausque.pje_dausque_fournier.Others.TripListAdapter;

import java.util.List;

//test
public class Main extends AppCompatActivity{
    private TripViewModel mTripViewModel;
    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
            System.out.println(mTwoPane);
        }
        if(!mTwoPane) {
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Main.this, NewTripActivity.class);
                    startActivity(intent);
                }
            });
        }


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        assert recyclerView != null;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final TripListAdapter adapter = new TripListAdapter(this,this,mTwoPane);
        recyclerView.setAdapter(adapter);

        mTripViewModel = ViewModelProviders.of(this).get(TripViewModel.class);
        mTripViewModel.getAllTrips().observe((LifecycleOwner) this, new Observer<List<Trip>>() {
            @Override
            public void onChanged(@Nullable final List<Trip> trips) {
                // Update the cached copy of the words in the adapter.
                adapter.setTrips(trips);
            }
        });

        // Add the functionality to swipe items in the
        // recycler view to delete that item
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Trip myTrip = adapter.getTripAtPosition(position);
                        Toast.makeText(Main.this, "Deleting " +
                                myTrip.getMTrip(), Toast.LENGTH_LONG).show();

                        // Delete the word
                        mTripViewModel.deleteTrip(myTrip);
                    }
                });
        helper.attachToRecyclerView(recyclerView);
    }
}
