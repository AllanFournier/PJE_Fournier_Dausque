package com.example.dausque.pje_dausque_fournier.Repository;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.dausque.pje_dausque_fournier.Entity.Trip;
import com.example.dausque.pje_dausque_fournier.BD.TripDao;
import com.example.dausque.pje_dausque_fournier.BD.TripRoomDatabase;

import java.util.List;

public class
TripRepository {
    private TripDao mTripDao;
    private LiveData<List<Trip>> mAllTrips;

    public TripRepository(Application application) {
        TripRoomDatabase db = TripRoomDatabase.getDatabase(application);
        mTripDao = db.tripDao();
        mAllTrips = mTripDao.getAllTrips();
    }

    public LiveData<List<Trip>> getAllTrips() {
        return mAllTrips;
    }

    public void insert (Trip trip) {
        new insertAsyncTask(mTripDao).execute(trip);
    }

    public void deleteTrip(Trip trip) {
        new deleteTripAsyncTask(mTripDao).execute(trip);
    }

    public static class deleteTripAsyncTask extends AsyncTask<Trip, Void, Void> {
        private TripDao mAsyncTaskDao;

        deleteTripAsyncTask(TripDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Trip... params) {
            mAsyncTaskDao.deleteTrip(params[0]);
            return null;
        }
    }

    public static class insertAsyncTask extends AsyncTask<Trip, Void, Void> {

        private TripDao mAsyncTaskDao;

        insertAsyncTask(TripDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Trip... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
