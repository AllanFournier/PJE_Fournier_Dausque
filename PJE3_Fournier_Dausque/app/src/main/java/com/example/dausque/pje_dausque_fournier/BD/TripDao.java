package com.example.dausque.pje_dausque_fournier.BD;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dausque.pje_dausque_fournier.Entity.Trip;

import java.util.List;

@Dao
public interface TripDao {
    @Insert
    void insert(Trip trip);

    @Update
    void update(Trip... trip);

    @Delete
    void deleteTrip(Trip trip);

    @Query("DELETE FROM trip_table")
    void deleteAll();

    @Query("SELECT * from trip_table ORDER BY id ASC")
    LiveData<List<Trip>> getAllTrips();


}