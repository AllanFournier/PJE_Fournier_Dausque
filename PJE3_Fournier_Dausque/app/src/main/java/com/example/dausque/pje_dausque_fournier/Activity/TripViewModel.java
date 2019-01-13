package com.example.dausque.pje_dausque_fournier.Activity;

import com.example.dausque.pje_dausque_fournier.Repository.TripRepository;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.dausque.pje_dausque_fournier.Entity.Trip;

import java.util.List;

public class TripViewModel extends AndroidViewModel {

    private TripRepository mRepository;

    private LiveData<List<Trip>> mAllTrips;

    public TripViewModel (Application application) {
        super(application);
        mRepository = new TripRepository(application);
        mAllTrips = mRepository.getAllTrips();
    }

    public LiveData<List<Trip>> getAllTrips() {
        return mAllTrips;
    }

    public void insert(Trip trip) {
        mRepository.insert(trip);
    }

    public void update(Trip trip) {
        mRepository.update(trip);
    }

    public void deleteTrip(Trip trip) {mRepository.deleteTrip(trip);}



}
